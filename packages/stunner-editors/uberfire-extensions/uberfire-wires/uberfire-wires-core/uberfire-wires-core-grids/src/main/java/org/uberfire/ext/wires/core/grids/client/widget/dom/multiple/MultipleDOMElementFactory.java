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

package org.uberfire.ext.wires.core.grids.client.widget.dom.multiple;

import org.uberfire.ext.wires.core.grids.client.widget.dom.DOMElementFactory;

/**
 * Definition of a Factory that can create DOMElements for GWT Widget based cell content.
 * DOMElements are transient in nature and only exist when required, such as when a column
 * and row is visible or when a cell is being edited.
 * @param <W> The Widget to be wrapped by the DOMElement.
 * @param <E> The DOMElement type that this Factory generates.
 */
public interface MultipleDOMElementFactory<W, E> extends DOMElementFactory<W, E>,
                                                         HasMultipleDOMElementResources {

}
