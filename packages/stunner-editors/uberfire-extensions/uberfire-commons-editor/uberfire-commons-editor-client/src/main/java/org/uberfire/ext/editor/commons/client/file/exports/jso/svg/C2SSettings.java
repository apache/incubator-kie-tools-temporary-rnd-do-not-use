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


package org.uberfire.ext.editor.commons.client.file.exports.jso.svg;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents the constructor parameter to be used on the {@link C2S}.
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
class C2SSettings {

    @JsOverlay
    protected static C2SSettings create(double width, double height, Object ctx) {
        final C2SSettings instance = new C2SSettings();
        instance.setWidth(width);
        instance.setHeight(height);
        instance.setCtx(ctx);
        instance.setEnableMirroring(true);
        return instance;
    }

    @JsProperty
    public native void setWidth(double width);

    @JsProperty
    public native void setHeight(double height);

    @JsProperty
    public native void setCtx(Object ctx);

    @JsProperty
    public native void setEnableMirroring(boolean enableMirroring);
}
