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


package org.uberfire.ext.wires.core.grids.client.widget.grid.impl;

import com.google.gwt.event.dom.client.KeyCodes;
import org.uberfire.ext.wires.core.grids.client.widget.grid.selections.SelectionExtension;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.ext.wires.core.grids.client.widget.scrollbars.GridLienzoScrollable;

public class KeyboardOperationMoveUp extends KeyboardOperationMove {

    public KeyboardOperationMoveUp(final GridLayer gridLayer) {
        this(gridLayer, null);
    }

    public KeyboardOperationMoveUp(final GridLayer gridLayer,
                                   final GridLienzoScrollable panel) {
        super(gridLayer, panel);
    }

    @Override
    SelectionExtension getSelectionExtension() {
        return SelectionExtension.UP;
    }

    @Override
    public int getKeyCode() {
        return KeyCodes.KEY_UP;
    }
}
