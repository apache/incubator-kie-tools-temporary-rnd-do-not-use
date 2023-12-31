/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License. 
 */


package org.jboss.errai.common.client.dom;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @deprecated Use Elemental 2 for new development
 *
 * @author Max Barkley <mbarkley@redhat.com>
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/Element">Web API</a>
 */
@JsType(isNative = true)
@Deprecated
public interface Element extends Node {
  @JsProperty String getTagName();

  String getAttribute(String name);
  void setAttribute(String name, String value);
  void removeAttribute(String name);
  Attr getAttributeNode(String name);
  Attr setAttributeNode(Attr newAttr);
  Attr removeAttributeNode(Attr oldAttr);
  NodeList getElementsByTagName(String name);
  NodeList getElementsByClassName(String classNames);
  Element querySelector(String selector);
  NodeList querySelectorAll(String selector);
  void normalize();
}
