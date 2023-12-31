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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uberfire.ext.wires.core.grids.client.model.Bounds;
import org.uberfire.ext.wires.core.grids.client.model.GridData;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.impl.BaseGridRendererHelper;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.impl.BaseGridRendererHelper.RenderingInformation;

import static org.mockito.Mockito.mock;

public class BaseGridWidgetRenderingTestUtils {

    public static final int HEADER_ROW_COUNT = 2;

    public static final double HEADER_ROW_HEIGHT = 20.0;

    public static final double HEADER_HEIGHT = HEADER_ROW_HEIGHT * HEADER_ROW_COUNT;

    public static RenderingInformation makeRenderingInformation(final GridData model) {
        return makeRenderingInformation(model,
                                        Collections.emptyList(),
                                        0,
                                        0.0,
                                        0.0);
    }

    public static RenderingInformation makeRenderingInformation(final GridData model,
                                                                final List<Double> rowOffsets) {
        return makeRenderingInformation(model,
                                        rowOffsets,
                                        HEADER_HEIGHT);
    }

    public static RenderingInformation makeRenderingInformation(final GridData model,
                                                                final List<Double> rowOffsets,
                                                                final double headerHeight) {
        return makeRenderingInformation(model,
                                        rowOffsets,
                                        HEADER_ROW_COUNT,
                                        HEADER_ROW_HEIGHT,
                                        headerHeight);
    }

    public static RenderingInformation makeRenderingInformation(final GridData model,
                                                                final List<Double> rowOffsets,
                                                                final int headerRowCount,
                                                                final double headerRowHeight,
                                                                final double headerRowsHeight) {
        final List<Double> rowHeights = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowOffsets.size(); rowIndex++) {
            rowHeights.add(20.0);
        }
        return new RenderingInformation(mock(Bounds.class),
                                        model.getColumns(),
                                        new BaseGridRendererHelper.RenderingBlockInformation(model.getColumns(),
                                                                                             0.0,
                                                                                             0.0,
                                                                                             0.0,
                                                                                             100),
                                        new BaseGridRendererHelper.RenderingBlockInformation(Collections.emptyList(),
                                                                                             0.0,
                                                                                             0.0,
                                                                                             0.0,
                                                                                             0.0),
                                        0,
                                        rowOffsets.size() - 1,
                                        rowHeights,
                                        rowOffsets,
                                        false,
                                        false,
                                        headerRowCount,
                                        headerRowHeight,
                                        headerRowsHeight,
                                        headerRowsHeight - (headerRowCount * headerRowHeight));
    }
}
