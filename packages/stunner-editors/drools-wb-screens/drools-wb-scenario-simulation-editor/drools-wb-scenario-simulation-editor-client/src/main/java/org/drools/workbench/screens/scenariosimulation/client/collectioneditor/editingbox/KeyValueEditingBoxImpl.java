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

package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class KeyValueEditingBoxImpl extends EditingBoxImpl<KeyValueEditingBox.Presenter> implements KeyValueEditingBox {

    @DataField("keyValueContainer")
    protected LIElement keyValueContainer = Document.get().createLIElement();

    @DataField("keyContainer")
    protected UListElement keyContainer = Document.get().createULElement();

    @DataField("valueContainer")
    protected UListElement valueContainer = Document.get().createULElement();

    @Override
    public LIElement getKeyValueContainer() {
        return keyValueContainer;
    }

    @Override
    public UListElement getKeyContainer() {
        return keyContainer;
    }

    @Override
    public UListElement getValueContainer() {
        return valueContainer;
    }

}
