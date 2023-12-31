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


package org.drools.workbench.screens.scenariosimulation.client.handlers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class SourceTypeSelectorTest {

    @Mock
    private TitledAttachmentFileWidget uploadWidgetMock;

    private SourceTypeSelector sourceTypeSelector;

    @Before
    public void setUp() throws Exception {
        sourceTypeSelector = spy(new SourceTypeSelector(uploadWidgetMock));
    }

    @Test
    public void onValueChange() {
        reset(uploadWidgetMock);
        ValueChangeEvent eventMock = mock(ValueChangeEvent.class);
        doReturn(false).when(sourceTypeSelector).isDMNSelected();
        sourceTypeSelector.onValueChange(eventMock);
        verify(uploadWidgetMock, never()).updateAssetList();
        doReturn(true).when(sourceTypeSelector).isDMNSelected();
        sourceTypeSelector.onValueChange(eventMock);
        verify(uploadWidgetMock, times(1)).updateAssetList();
    }

    @Test
    public void validateDMO() {
        commonValidate(false, false, true);
    }

    @Test
    public void validateInvalidDMN() {
        commonValidate(true, false, false);
    }

    @Test
    public void validateValidDMN() {
        commonValidate(true, true, true);
    }

    @Test
    public void addRadioButtons() {
        reset(uploadWidgetMock);
        sourceTypeSelector.addRadioButtons();
        assertEquals(2, sourceTypeSelector.radioButtonList.size());
        verify(uploadWidgetMock, times(1)).setVisible(false);

    }

    private void commonValidate(boolean isDMNSelected, boolean validate, boolean expected) {
        doReturn(isDMNSelected).when(sourceTypeSelector).isDMNSelected();
        doReturn(validate).when(uploadWidgetMock).validate();
        boolean retrieved = sourceTypeSelector.validate();
        if (isDMNSelected) {
            verify(uploadWidgetMock, times(1)).validate();
        }
        if (expected) {
            assertTrue(retrieved);
        } else {
            assertFalse(retrieved);
        }
    }
}