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

package org.dashbuilder.common.client.widgets;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class AlertBoxView implements AlertBox.View, IsElement {

    @Inject
    @DataField
    Div alertDiv;

    @Inject
    @DataField
    Button closeButton;

    @Inject
    @DataField
    Span alertIcon;

    @Inject
    @DataField
    Span alertMessage;

    AlertBox presenter;

    private static final String[] ALERT_CLASS = {"alert-danger", "alert-warning", "alert-success", "alert-info"};
    private static final String[] ALERT_ICON = {"pficon-error-circle-o", "pficon-warning-triangle-o", "pficon-ok", "pficon-info"};

    @Override
    public void init(AlertBox presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMessage(String text) {
        alertMessage.setTextContent(text);
    }

    @Override
    public void setLevel(AlertBox.Level level) {
        String _alertClass = ALERT_CLASS[level.ordinal()];
        String _alertIcon = ALERT_ICON[level.ordinal()];
        alertDiv.setClassName("alert " + _alertClass);
        alertIcon.setClassName("pficon " + _alertIcon);
    }

    @Override
    public void setCloseEnabled(boolean enabled) {
        closeButton.setHidden(!enabled);
    }

    @EventHandler("closeButton")
    private void onClose(ClickEvent event) {
        presenter.close();
    }
}
