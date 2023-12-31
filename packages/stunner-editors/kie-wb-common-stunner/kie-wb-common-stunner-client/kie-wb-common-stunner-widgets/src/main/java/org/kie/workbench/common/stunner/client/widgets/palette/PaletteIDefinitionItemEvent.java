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


package org.kie.workbench.common.stunner.client.widgets.palette;

import org.kie.workbench.common.stunner.core.client.components.palette.PaletteItemEvent;
import org.kie.workbench.common.stunner.core.client.shape.Shape;
import org.kie.workbench.common.stunner.core.client.shape.factory.ShapeFactory;

public class PaletteIDefinitionItemEvent extends PaletteItemEvent {

    private final Object definition;
    private final ShapeFactory<?, ? extends Shape> factory;
    private final double x;
    private final double y;

    public PaletteIDefinitionItemEvent(final String id,
                                       final Object definition,
                                       final ShapeFactory<?, ? extends Shape> factory,
                                       final double x,
                                       final double y) {
        super(id);
        this.definition = definition;
        this.factory = factory;
        this.x = x;
        this.y = y;
    }

    public Object getDefinition() {
        return definition;
    }

    public ShapeFactory<?, ? extends Shape> getFactory() {
        return factory;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
