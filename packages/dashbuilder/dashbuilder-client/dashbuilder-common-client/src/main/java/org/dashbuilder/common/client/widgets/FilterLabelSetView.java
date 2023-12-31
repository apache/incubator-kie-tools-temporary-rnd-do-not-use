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
import org.dashbuilder.common.client.resources.i18n.DashbuilderCommonConstants;
import org.jboss.errai.common.client.dom.Anchor;
import org.jboss.errai.common.client.dom.DOMUtil;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class FilterLabelSetView implements FilterLabelSet.View, IsElement {

    @Inject
    @DataField
    Div mainDiv;

    @Inject
    @DataField
    Anchor clearAll;

    FilterLabelSet presenter;

    @Override
    public void init(FilterLabelSet presenter) {
        this.presenter = presenter;
        clearAll.setTextContent(DashbuilderCommonConstants.INSTANCE.clearAll());
    }

    @Override
    public void clearAll() {
        DOMUtil.removeAllChildren(mainDiv);
        mainDiv.appendChild(clearAll);
    }

    @Override
    public void setClearAllEnabled(boolean enabled) {
        clearAll.setHidden(!enabled);
    }

    @Override
    public void addLabel(FilterLabel label) {
        mainDiv.insertBefore(label.getElement(), clearAll);
    }

    @EventHandler("clearAll")
    private void onClearAll(ClickEvent event) {
        presenter.onClearAll();
    }
}
