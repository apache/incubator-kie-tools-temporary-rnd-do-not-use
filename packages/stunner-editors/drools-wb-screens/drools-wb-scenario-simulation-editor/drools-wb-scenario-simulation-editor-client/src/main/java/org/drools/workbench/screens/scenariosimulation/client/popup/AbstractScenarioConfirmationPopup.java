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

import org.jboss.errai.common.client.dom.HTMLElement;
import org.uberfire.mvp.Command;

public interface AbstractScenarioConfirmationPopup {

    interface Presenter {

        /**
         * Makes the <code>ScenarioConfirmationPopup</code> visible with OK/Cancel buttons.
         *
         * @param mainTitleText
         * @param mainQuestionText
         * @param text1Text
         * @param textQuestionText
         * @param okDeleteButtonText
         * @param okDeleteCommand
         */
        void show(final String mainTitleText,
                  final String mainQuestionText,
                  final String text1Text,
                  final String textQuestionText,
                  final String okDeleteButtonText,
                  final Command okDeleteCommand);

        /**
         * Makes this popup container(and the main content along with it) invisible. Has no effect if the popup is not
         * already showing.
         */
        void hide();
    }

    /**
     * Makes the <code>ScenarioConfirmationPopup</code> visible with OK/Cancel buttons.
     *
     * @param mainTitleText
     * @param mainQuestionText
     * @param text1Text
     * @param textQuestionText
     * @param okDeleteButtonText
     * @param okDeleteCommand
     */
    void show(final String mainTitleText,
              final String mainQuestionText,
              final String text1Text,
              final String textQuestionText,
              final String okDeleteButtonText,
              final Command okDeleteCommand);

    HTMLElement getElement();

    /**
     * Makes this popup container(and the main content along with it) invisible. Has no effect if the popup is not
     * already showing.
     */
    void hide();

}
