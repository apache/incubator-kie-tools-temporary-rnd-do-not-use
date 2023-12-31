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

package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.dom.client.InputElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class PropertyViewImplTest extends AbstractCollectionEditorTest {

    private PropertyViewImpl propertyViewImpl;

    @Mock
    InputElement propertyValueInputMock;

    @Before
    public void setup() {
        propertyViewImpl = spy(new PropertyViewImpl() {
            {
                this.propertyValueInput = propertyValueInputMock;
            }
        });
    }

    @Test
    public void onPropertyValueInputClickEvent() {
        propertyViewImpl.onPropertyValueInputClickEvent(clickEventMock);
        verify(propertyValueInputMock, times(1)).focus();
        verify(clickEventMock, times(1)).stopPropagation();
    }
}
