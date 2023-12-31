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

/**
 * A string wrapped as an object of type {@link SafeUri}.
 *
 * <p>This class is package-private and intended for internal use by the {@link
 * com.google.gwt.safehtml} package.
 *
 * <p>All implementors must implement .equals and .hashCode so that they operate just like
 * String.equals() and String.hashCode().
 */
class SafeUriString implements SafeUri {

  private String uri;

  /**
   * Constructs a {@link SafeUriString} from a string. Callers are responsible for ensuring that the
   * string passed as the argument to this constructor satisfies the constraints of the contract
   * imposed by the {@link SafeUri} interface.
   *
   * @param uri the string to be wrapped as a {@link SafeUri}
   */
  SafeUriString(String uri) {
    if (uri == null) {
      throw new NullPointerException("uri is null");
    }
    this.uri = uri;
  }

  /** No-arg constructor for compatibility with GWT serialization. */
  @SuppressWarnings("unused")
  private SafeUriString() {}

  /** Returns a hash code for this string. */
  @Override
  public int hashCode() {
    return uri.hashCode();
  }

  /** Compares this string to the specified object. */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SafeUri)) {
      return false;
    }
    return uri.equals(((SafeUri) obj).asString());
  }

  @Override
  public String toString() {
    return "safe: \"" + asString() + "\"";
  }

  /** {@inheritDoc} */
  public String asString() {
    return uri;
  }
}
