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


package org.jboss.errai.common.client.logging.handlers;

import java.util.logging.Formatter;
import java.util.logging.Level;

import com.google.gwt.logging.client.DevelopmentModeLogHandler;
import org.jboss.errai.common.client.logging.formatters.ErraiSimpleFormatter;

/**
 * An extension of {@link DevelopmentModeLogHandler} that uses a given {@link Formatter}.
 * 
 * @author Max Barkley <mbarkley@redhat.com>
 */
public class ErraiDevelopmentModeLogHandler extends DevelopmentModeLogHandler implements ErraiLogHandler {
  
  /*
   * Workaround to so that superlcass does not overwrite log level
   */
  private boolean init = false;
  
  public ErraiDevelopmentModeLogHandler(final Formatter formatter) {
    setFormatter(formatter);
    init = true;
  }
  
  public ErraiDevelopmentModeLogHandler() {
    this(new ErraiSimpleFormatter());
  }
  
  @Override
  public boolean isEnabled() {
    return !getLevel().equals(Level.OFF);
  }
  
  @Override
  public void setLevel(Level newLevel) {
    if (init)
      staticSetLevel(newLevel.getName());
  }
  
  @Override
  public Level getLevel() {
    return Level.parse(staticGetLevel());
  }
  
  public static native void staticSetLevel(String newLevel) /*-{
    $wnd.erraiDevelopmentModeLogHandlerLevel = newLevel;
  }-*/;
  
  public static native String staticGetLevel() /*-{
    if ($wnd.erraiDevelopmentModeLogHandlerLevel === undefined) {
      return "ALL";
    } else {
      return $wnd.erraiDevelopmentModeLogHandlerLevel;
    }
  }-*/;
}
