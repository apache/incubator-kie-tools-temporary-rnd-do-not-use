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


package org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.columns.impl;

import com.ait.lienzo.client.core.shape.BoundingBoxPathClipper;
import com.ait.lienzo.client.core.shape.IPathClipper;
import com.ait.lienzo.client.core.types.BoundingBox;

public class BoundingBoxPathClipperFactory {

    public IPathClipper newClipper(final double minX,
                                   final double minY,
                                   final double maxX,
                                   final double maxY) {
        final BoundingBox boundingBox = BoundingBox.fromDoubles(minX, minY, maxX, maxY);
        return new BoundingBoxPathClipper(boundingBox);
    }
}
