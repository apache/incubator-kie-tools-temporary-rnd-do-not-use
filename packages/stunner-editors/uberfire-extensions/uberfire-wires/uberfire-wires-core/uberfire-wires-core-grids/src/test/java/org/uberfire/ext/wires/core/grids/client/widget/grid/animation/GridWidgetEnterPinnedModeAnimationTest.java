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


package org.uberfire.ext.wires.core.grids.client.widget.grid.animation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ait.lienzo.client.core.shape.Layer;
import com.google.gwt.user.client.Command;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GridWidgetEnterPinnedModeAnimationTest {

    @Mock
    private Layer layer;

    @Mock
    private GridWidget gridWidget;

    @Mock
    private Command onStartCommand;

    @Mock
    private GridWidget widget;

    @Mock
    private Command command;

    @Test
    public void testOnClose() {

        doReturn(layer).when(gridWidget).getLayer();

        final Set<GridWidget> gridWidgets = new HashSet<GridWidget>() {{
            add(widget);
        }};
        final List<Command> onEnterPinnedModeCommands = new ArrayList<Command>() {{
            add(command);
        }};
        final GridWidgetEnterPinnedModeAnimation animation = new GridWidgetEnterPinnedModeAnimation(gridWidget,
                                                                                                    gridWidgets,
                                                                                                    onStartCommand,
                                                                                                    onEnterPinnedModeCommands);

        animation.doClose();

        verify(widget).setVisible(false);
        verify(layer).setListening(true);
        verify(layer).batch();
        verify(command).execute();
    }
}
