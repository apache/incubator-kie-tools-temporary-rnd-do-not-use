/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.safehtml.shared;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import elemental2.core.Global;

/** Utility class containing static methods for validating and sanitizing URIs. */
public final class UriUtils {

  /**
   * Characters that don't need %-escaping (minus letters and digits), according to ECMAScript 5th
   * edition for the {@code encodeURI} function.
   */
  static final String DONT_NEED_ENCODING =
      ";/?:@&=+$," // uriReserved
          + "-_.!~*'()" // uriMark
          + "#"
          + "[]"; // could be used in IPv6 addresses

  private static final JvmImpl impl = new JvmImpl();

  // prevent instantiation
  private UriUtils() {}

  /**
   * Returns a {@link SafeUri} constructed from a value that is fully under the control of the
   * program, e.g., a constant.
   *
   * <p>The string is not sanitized and no checks are performed. The assumption that the resulting
   * value adheres to the {@link SafeUri} type contract is entirely based on the argument being
   * fully under program control and not being derived from a program input.
   *
   * <p><strong>Convention of use:</strong> This method must only be invoked on values that are
   * fully under the program's control, such as string literals.
   *
   * @param s the input String
   * @return a SafeUri instance
   */
  public static SafeUri fromSafeConstant(String s) {
    SafeUriHostedModeUtils.maybeCheckValidUri(s);
    return new SafeUriString(s);
  }

  /**
   * Returns a {@link SafeUri} obtained by sanitizing the provided string.
   *
   * <p>The input string is sanitized using {@link #sanitizeUri(String)}.
   *
   * @param s the input String
   * @return a SafeUri instance
   */
  public static SafeUri fromString(String s) {
    return new SafeUriString(sanitizeUri(s));
  }

  /**
   * Sanitizes a URI.
   *
   * <p>This method returns the URI provided if it is safe to use as the value of a URI-valued HTML
   * attribute according to {@link #isSafeUri}, or the URI "{@code #}" otherwise.
   *
   * @param uri the URI to sanitize
   * @return a sanitized String
   */
  public static String sanitizeUri(String uri) {
    if (isSafeUri(uri)) {
      return encodeAllowEscapes(uri);
    } else {
      return "#";
    }
  }

  /**
   * Determines if a {@link String} is safe to use as the value of a URI-valued HTML attribute such
   * as {@code src} or {@code href}.
   *
   * <p>In this context, a URI is safe if it can be established that using it as the value of a
   * URI-valued HTML attribute such as {@code src} or {@code href} cannot result in script
   * execution. Specifically, this method deems a URI safe if it either does not have a scheme, or
   * its scheme is one of {@code http, https, ftp, mailto}.
   *
   * @param uri the URI to validate
   * @return {@code true} if {@code uri} is safe in the above sense; {@code false} otherwise
   */
  public static boolean isSafeUri(String uri) {
    String scheme = extractScheme(uri);
    if (scheme == null) {
      return true;
    }
    /*
     * Special care is be taken with case-insensitive 'i' in the Turkish locale.
     * i -> to upper in Turkish locale -> İ
     * I -> to lower in Turkish locale -> ı
     * For this reason there are two checks for mailto: "mailto" and "MAILTO"
     * For details, see: http://www.i18nguy.com/unicode/turkish-i18n.html
     */
    String schemeLc = scheme.toLowerCase(Locale.ROOT);
    return ("http".equals(schemeLc)
        || "https".equals(schemeLc)
        || "ftp".equals(schemeLc)
        || "mailto".equals(schemeLc)
        || "MAILTO".equals(scheme.toUpperCase(Locale.ROOT)));
  }

  /**
   * Encodes the URL, preserving existing %-escapes.
   *
   * @param uri the URL to encode
   * @return the %-escaped URL
   */
  public static String encodeAllowEscapes(String uri) {
    StringBuilder escaped = new StringBuilder();

    boolean firstSegment = true;
    for (String segment : uri.split("%", -1)) {
      if (firstSegment) {
        /*
         * The first segment is never part of a percent-escape, so we always
         * escape it. Note that if the input starts with a percent, we will get
         * an empty segment before that.
         */
        firstSegment = false;
        escaped.append(encode(segment));
        continue;
      }

      if (segment.length() >= 2 && segment.substring(0, 2).matches("[0-9a-fA-F]{2}")) {
        // Append the escape without encoding.
        escaped.append("%").append(segment.substring(0, 2));

        // Append the rest of the segment, escaped.
        escaped.append(encode(segment.substring(2)));
      } else {
        // The segment did not start with an escape, so encode the whole
        // segment.
        escaped.append("%25").append(encode(segment));
      }
    }
    return escaped.toString();
  }

  /**
   * Extracts the scheme of a URI.
   *
   * @param uri the URI to extract the scheme from
   * @return the URI's scheme, or {@code null} if the URI does not have one
   */
  public static String extractScheme(String uri) {
    int colonPos = uri.indexOf(':');
    if (colonPos < 0) {
      return null;
    }
    String scheme = uri.substring(0, colonPos);
    if (scheme.indexOf('/') >= 0 || scheme.indexOf('#') >= 0) {
      /*
       *  The URI's prefix up to the first ':' contains other URI special
       *  chars, and won't be interpreted as a scheme.
       *
       *  TODO(xtof): Consider basing this on URL#isValidProtocol or similar;
       *  however I'm worried that being too strict here will effectively
       *   allow dangerous schemes accepted in loosely parsing browsers.
       */
      return null;
    }
    return scheme;
  }

  /**
   * Encodes the URL.
   *
   * <p>In client code, this method delegates to the browser's {@code encodeURI} function and then
   * unescapes brackets, as they might be used for IPv6 addresses.
   *
   * @param uri the URL to encode
   * @return the %-escaped URL
   */
  public static String encode(String uri) {
    return impl.encode(uri);
  }

  /**
   * Returns a {@link SafeUri} constructed from a trusted string, i.e., without sanitizing the
   * string. No checks are performed. The calling code should be carefully reviewed to ensure the
   * argument meets the SafeUri contract.
   *
   * @param s the input String
   * @return a SafeUri instance
   */
  public static SafeUri fromTrustedString(String s) {
    SafeUriHostedModeUtils.maybeCheckValidUri(s);
    return new SafeUriString(s);
  }

  /**
   * Returns a {@link SafeUri} constructed from an untrusted string but without sanitizing it.
   *
   * <p><strong>Despite this method creating a SafeUri instance, no checks are performed, so the
   * returned SafeUri is absolutely NOT guaranteed to be safe!</strong>
   *
   * @param s the input String
   * @return a SafeUri instance
   * @deprecated This method is intended only for use in APIs that use {@link SafeUri} to represent
   *     URIs, but for backwards compatibility have methods that accept URI parameters as plain
   *     strings.
   */
  @Deprecated
  public static SafeUri unsafeCastFromUntrustedString(String s) {
    return new SafeUriString(s);
  }

  private static class JsImpl {

    String encode(String uri) {
      uri = Global.encodeURI(uri);
      // Follow the same approach as SafeHtmlUtils.htmlEscape
      if (uri.indexOf("%5B") != -1) {
        uri = uri.replaceAll("%5B", "[");
      }
      if (uri.indexOf("%5D") != -1) {
        uri = uri.replaceAll("%5D", "]");
      }
      return uri;
    }
  }

  private static class JvmImpl extends JsImpl {

    @GwtIncompatible
    @Override
    String encode(String uri) {
      StringBuilder sb = new StringBuilder();
      byte[] utf8bytes;
      try {
        utf8bytes = uri.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
        // UTF-8 is guaranteed to be implemented, this code won't ever run.
        return null;
      }
      for (byte b : utf8bytes) {
        int c = b & 0xFF;
        // This works because characters that don't need encoding are all
        // expressed as a single UTF-8 byte
        if (('a' <= c && c <= 'z')
            || ('A' <= c && c <= 'Z')
            || ('0' <= c && c <= '9')
            || DONT_NEED_ENCODING.indexOf(c) != -1) {
          sb.append((char) c);
        } else {
          String hexByte = Integer.toHexString(c).toUpperCase(Locale.ROOT);
          if (hexByte.length() == 1) {
            hexByte = "0" + hexByte;
          }
          sb.append('%').append(hexByte);
        }
      }
      return sb.toString();
    }
  }
}
