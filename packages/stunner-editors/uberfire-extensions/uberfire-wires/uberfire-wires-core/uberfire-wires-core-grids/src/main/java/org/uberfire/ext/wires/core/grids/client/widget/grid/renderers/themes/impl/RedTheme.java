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

package org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.themes.impl;

import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.ait.lienzo.shared.core.types.TextBaseLine;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.dnd.IsRowDragHandle;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.themes.GridRendererTheme;

/**
 * A renderer that draws a predominantly red GridWidget.
 */
public class RedTheme implements GridRendererTheme {

    @Override
    public String getName() {
        return "Red";
    }

    @Override
    public MultiPath getSelector() {
        final MultiPath selector = new MultiPath()
                .setStrokeWidth(2.0)
                .setStrokeColor(ColorName.RED)
                .setShadow(new Shadow(ColorName.DARKRED,
                                      4,
                                      0.0,
                                      0.0));
        return selector;
    }

    @Override
    public Rectangle getCellSelectorBorder() {
        final Rectangle selector = new Rectangle(0, 0)
                .setStrokeColor(ColorName.RED)
                .setStrokeWidth(1.0);
        return selector;
    }

    @Override
    public Rectangle getCellSelectorBackground() {
        final Rectangle background = new Rectangle(0, 0)
                .setFillColor(ColorName.RED)
                .setAlpha(0.25);
        return background;
    }

    @Override
    public Rectangle getHeaderBackground(final GridColumn<?> column) {
        final Rectangle header = new Rectangle(0,
                                               0)
                .setFillColor(ColorName.PALEVIOLETRED);
        return header;
    }

    @Override
    public MultiPath getHeaderGridLine() {
        final MultiPath headerGrid = new MultiPath()
                .setStrokeColor(ColorName.SLATEGRAY)
                .setStrokeWidth(0.5)
                .setListening(false);
        return headerGrid;
    }

    @Override
    public Text getHeaderText() {
        final Text t = new Text("")
                .setFillColor(ColorName.DARKRED)
                .setFontSize(12)
                .setFontStyle("bold")
                .setFontFamily("serif")
                .setListening(false)
                .setTextBaseLine(TextBaseLine.MIDDLE)
                .setTextAlign(TextAlign.CENTER);
        return t;
    }

    @Override
    @SuppressWarnings("unused")
    public Rectangle getBodyBackground(final GridColumn<?> column) {
        if (column instanceof IsRowDragHandle) {
            return getHeaderBackground(column);
        }
        final Rectangle body = new Rectangle(0,
                                             0)
                .setFillColor(ColorName.PINK);
        return body;
    }

    @Override
    public MultiPath getBodyGridLine() {
        final MultiPath bodyGrid = new MultiPath()
                .setStrokeColor(ColorName.SLATEGRAY)
                .setStrokeWidth(0.5);
        return bodyGrid;
    }

    @Override
    public Text getBodyText() {
        final Text t = new Text("")
                .setFillColor(ColorName.RED)
                .setFontSize(12)
                .setFontFamily("serif")
                .setListening(false)
                .setTextBaseLine(TextBaseLine.MIDDLE)
                .setTextAlign(TextAlign.CENTER);
        return t;
    }

    @Override
    public Rectangle getGridBoundary() {
        final Rectangle boundary = new Rectangle(0,
                                                 0)
                .setStrokeColor(ColorName.SLATEGRAY)
                .setStrokeWidth(0.5)
                .setListening(false);
        return boundary;
    }

    @Override
    public Line getGridHeaderBodyDivider() {
        final Line divider = new Line()
                .setStrokeColor(ColorName.SLATEGRAY)
                .setStrokeWidth(0.5);
        return divider;
    }
}
