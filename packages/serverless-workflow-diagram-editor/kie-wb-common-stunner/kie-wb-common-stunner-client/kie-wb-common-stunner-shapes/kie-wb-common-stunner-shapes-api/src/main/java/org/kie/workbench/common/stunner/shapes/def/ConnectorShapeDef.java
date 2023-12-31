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


package org.kie.workbench.common.stunner.shapes.def;

import org.kie.workbench.common.stunner.core.client.shape.common.DashArray;
import org.kie.workbench.common.stunner.core.client.shape.view.ShapeView;
import org.kie.workbench.common.stunner.core.definition.shape.Glyph;
import org.kie.workbench.common.stunner.core.definition.shape.ShapeDef;

public interface ConnectorShapeDef<W, V extends ShapeView>
        extends BasicShapeViewDef<W, V> {

    enum Direction {
        NONE,
        ONE,
        BOTH
    }

    String FONT_FAMILY = "Open Sans";
    String FONT_COLOR = "#000000";
    String FONT_STROKE_COLOR = "#393f44";
    double FONT_SIZE = 10d;
    double STROKE_SIZE = 0.5d;

    ConnectorGlyph GLYPH = ConnectorGlyph.create();

    default Direction getDirection(W definition) {
        return Direction.ONE;
    }

    @Override
    default Glyph getGlyph(Class<? extends W> type,
                           final String defId) {
        return GLYPH;
    }

    @Override
    default Class<? extends ShapeDef> getType() {
        return ConnectorShapeDef.class;
    }

    default DashArray getDashArray(final W element) {
        return null;
    }
}
