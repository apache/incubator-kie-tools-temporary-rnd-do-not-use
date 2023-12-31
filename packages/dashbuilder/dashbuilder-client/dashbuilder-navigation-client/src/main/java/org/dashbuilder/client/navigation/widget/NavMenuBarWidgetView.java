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

package org.dashbuilder.client.navigation.widget;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.dashbuilder.common.client.widgets.AlertBox;
import org.jboss.errai.common.client.dom.DOMUtil;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated
public class NavMenuBarWidgetView extends TargetDivNavWidgetView<NavMenuBarWidget>
        implements NavMenuBarWidget.View {

    @Inject
    @DataField
    Div mainDiv;

    @Inject
    @DataField
    UnorderedList navBar;

    @Inject
    @DataField
    @Named("nav")
    HTMLElement nav;

    NavMenuBarWidget presenter;

    @Inject
    public NavMenuBarWidgetView(AlertBox alertBox) {
        super(alertBox);
    }

    @Override
    public void init(NavMenuBarWidget presenter) {
        this.presenter = presenter;
        super.navWidget = navBar;
        setNavHeaderVisible(true);
    }

    @Override
    public void addDivider() {
        // Useless in a menu bar
    }

    @Override
    public void clearItems() {
        super.clearItems();
        DOMUtil.removeAllChildren(mainDiv);
        mainDiv.appendChild(navBar.getParentElement().getParentElement());
    }

    @Override
    public void error(String message) {
        DOMUtil.removeAllChildren(mainDiv);
        alertBox.setMessage(message);
        mainDiv.appendChild(alertBox.getElement());
    }

    @Override
    public void setNavHeaderVisible(boolean visible) {
        nav.setClassName(visible ? "navbar navbar-default navbar-pf" : "");
    }
}
