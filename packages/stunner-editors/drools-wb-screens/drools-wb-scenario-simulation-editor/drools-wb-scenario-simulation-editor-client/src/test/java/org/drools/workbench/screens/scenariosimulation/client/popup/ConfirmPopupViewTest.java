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


package org.drools.workbench.screens.scenariosimulation.client.popup;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.MAIN_TEXT;
import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.MAIN_TITLE_TEXT;
import static org.mockito.Mockito.spy;

@RunWith(LienzoMockitoTestRunner.class)
public class ConfirmPopupViewTest extends AbstractScenarioConfirmationPopupViewTest {

    @Before
    public void setup() {
        super.commonSetup();
        popupView = spy(new ConfirmPopupView() {
            {
                this.mainTitle = mainTitleMock;
                this.mainQuestion = mainQuestionMock;
                this.text1 = text1Mock;
                this.textQuestion = textQuestionMock;
                this.cancelButton = cancelButtonMock;
                this.okButton = okDeleteButtonMock;
                this.modal = modalMock;
                this.translationService = translationServiceMock;
            }
        });
    }

    @Test
    public void show() {
        ((ConfirmPopupView) popupView).show(MAIN_TITLE_TEXT,
                                            MAIN_TEXT);
        verifyShow(MAIN_TITLE_TEXT,
                   null, MAIN_TEXT, null);
    }

}