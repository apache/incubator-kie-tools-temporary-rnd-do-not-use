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


package org.kie.workbench.common.stunner.forms.client.notifications;

import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.kie.workbench.common.stunner.core.client.i18n.ClientTranslationService;
import org.kie.workbench.common.stunner.forms.client.resources.i18n.FormsClientConstants;

@ApplicationScoped
public class FormGenerationNotifier {

    private static final String INFO = "INFO";
    private static final String ERROR = "ERROR";

    private ClientTranslationService translationService;

    private Consumer<String> messageNotifier;
    private Consumer<String> errorNotifier;

    protected FormGenerationNotifier() {
        this(null);
    }

    @Inject
    public FormGenerationNotifier(ClientTranslationService translationService) {
        this.translationService = translationService;
    }

    @PostConstruct
    private void init() {
        messageNotifier = this::doShowNotification;
        errorNotifier = this::doShowError;
    }

    FormGenerationNotifier(ClientTranslationService translationService, Consumer<String> messageNotifier, Consumer<String> errorNotifier) {
        this(translationService);

        this.messageNotifier = messageNotifier;
        this.errorNotifier = errorNotifier;
    }

    public void showNotification(final String message) {
        messageNotifier.accept(message);
    }

    public void showError(final String message) {
        errorNotifier.accept(message);
    }

    private void doShowNotification(String message) {
        showNotification(translationService.getValue(FormsClientConstants.FormsNotificationTitle, INFO),
                         message,
                         IconType.CHECK);
    }

    private void doShowError(final String message) {
        showNotification(translationService.getValue(FormsClientConstants.FormsNotificationTitle, ERROR),
                         message,
                         IconType.EXCLAMATION);
    }

    private void showNotification(final String title,
                                  final String message,
                                  final IconType icon) {
        Notify.notify(title,
                      buildHtmlEscapedText(message),
                      icon);
    }

    private static String buildHtmlEscapedText(final String message) {
        return new SafeHtmlBuilder().appendEscapedLines(message).toSafeHtml().asString();
    }
}
