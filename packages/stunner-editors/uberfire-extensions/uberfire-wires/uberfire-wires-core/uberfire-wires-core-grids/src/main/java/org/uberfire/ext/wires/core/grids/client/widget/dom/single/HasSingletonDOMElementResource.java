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

package org.uberfire.ext.wires.core.grids.client.widget.dom.single;

import org.uberfire.ext.wires.core.grids.client.widget.dom.HasDOMElementResources;

/**
 * Extension for Factories and Columns that display a single DOMElement to edit the content of a cell.
 */
public interface HasSingletonDOMElementResource extends HasDOMElementResources {

    /**
     * Flushes the content of a DOMElement to the underlying model. Releasing and removing the DOMElement.
     */
    void flush();
}
