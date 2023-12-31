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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.annotations.BrowserEvent;

/**
 *
 * @deprecated Use Elemental 2 for new development
 *
 * @author Max Barkley <mbarkley@redhat.com>
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/Event">Web API</a>
 */
@JsType(isNative = true)
@BrowserEvent
@Deprecated
public interface Event {
  @JsOverlay static final short NONE = 0;
  @JsOverlay static final short CAPTURING_PHASE = 1;
  @JsOverlay static final short AT_TARGET = 2;
  @JsOverlay static final short BUBBLING_PHASE = 3;

  @JsProperty(name = "bubbles") boolean isBubbles();
  @JsProperty(name = "cancelable") boolean isCancelable();
  @JsProperty EventTarget getCurrentTarget();
  @JsProperty(name = "defaultPrevented") boolean isDefaultPrevented();
  @JsProperty short getEventPhase();
  @JsProperty EventTarget getTarget();
  @JsProperty double getTimeStamp();
  @JsProperty String getType();
  @JsProperty(name = "isTrusted") boolean isTrusted();

  void initEvent(String type, boolean bubbles, boolean cancelable);
  void preventDefault();
  void stopImmediatePropagation();
  void stopPropagation();
}
