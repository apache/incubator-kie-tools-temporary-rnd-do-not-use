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

import java.util.Optional;

import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.google.gwt.user.client.Command;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.layer.pinning.GridPinnedModeManager;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class DefaultGridWidgetPinnedModeMouseEventHandlerTest extends BaseGridWidgetMouseDoubleClickHandlerTest {

    @Mock
    private GridPinnedModeManager pinnedModeManager;

    private DefaultGridWidgetPinnedModeMouseEventHandler handler;

    @Before
    public void setup() {
        super.setup();

        final DefaultGridWidgetPinnedModeMouseEventHandler wrapped = new DefaultGridWidgetPinnedModeMouseEventHandler(pinnedModeManager,
                                                                                                                      renderer);
        handler = spy(wrapped);
    }

    @Test
    public void enterPinnedMode() {
        when(gridWidget.isVisible()).thenReturn(true);

        doubleClickEvent.x = 100;
        doubleClickEvent.y = 100;

        final Point2D computedLocation = new Point2D(100.0, 100.0);
        when(gridWidget.getComputedLocation()).thenReturn(computedLocation);

        handler.onNodeMouseEvent(gridWidget,
                                 relativeLocation,
                                 Optional.of(0),
                                 Optional.of(0),
                                 Optional.empty(),
                                 Optional.empty(),
                                 event);

        verify(handler,
               times(1)).handleHeaderCell(eq(gridWidget),
                                          eq(relativeLocation),
                                          eq(0),
                                          eq(0),
                                          eq(event));
        verify(pinnedModeManager,
               times(1)).enterPinnedMode(eq(gridWidget),
                                         any(Command.class));
        verify(pinnedModeManager,
               never()).exitPinnedMode(any(Command.class));
    }

    @Test
    public void exitPinnedMode() {
        when(gridWidget.isVisible()).thenReturn(true);
        when(pinnedModeManager.isGridPinned()).thenReturn(true);

        doubleClickEvent.x = 100;
        doubleClickEvent.y = 100;

        final Point2D computedLocation = new Point2D(100.0, 100.0);
        when(gridWidget.getComputedLocation()).thenReturn(computedLocation);

        handler.onNodeMouseEvent(gridWidget,
                                 relativeLocation,
                                 Optional.of(0),
                                 Optional.of(0),
                                 Optional.empty(),
                                 Optional.empty(),
                                 event);

        verify(handler,
               times(1)).handleHeaderCell(eq(gridWidget),
                                          eq(relativeLocation),
                                          eq(0),
                                          eq(0),
                                          eq(event));
        verify(pinnedModeManager,
               never()).enterPinnedMode(any(GridWidget.class),
                                        any(Command.class));
        verify(pinnedModeManager,
               times(1)).exitPinnedMode(any(Command.class));
    }

    @Test
    public void checkOnNodeMouseEventDuringDragOperation() {
        doReturn(true).when(handler).isDNDOperationInProgress(eq(gridWidget));

        assertFalse(handler.onNodeMouseEvent(gridWidget,
                                             relativeLocation,
                                             Optional.empty(),
                                             Optional.empty(),
                                             Optional.of(0),
                                             Optional.of(1),
                                             event));
    }
}
