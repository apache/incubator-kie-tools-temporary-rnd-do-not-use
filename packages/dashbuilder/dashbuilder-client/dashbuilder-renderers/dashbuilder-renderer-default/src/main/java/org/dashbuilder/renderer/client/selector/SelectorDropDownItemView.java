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

package org.dashbuilder.renderer.client.selector;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Anchor;
import org.jboss.errai.common.client.dom.ListItem;
import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class SelectorDropDownItemView implements SelectorDropDownItem.View, IsElement {

    @Inject
    @DataField
    ListItem item;

    @Inject
    @DataField
    Anchor itemAnchor;

    @Inject
    @DataField
    Span itemText;

    @Inject
    @DataField
    Span itemIcon;

    SelectorDropDownItem presenter;
    boolean iconVisible = true;

    @Override
    public void init(SelectorDropDownItem presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setValue(String value) {
        itemText.setTextContent(value);
    }

    @Override
    public void setDescription(String description) {
        item.setTitle(description);
    }

    @Override
    public void select() {
        item.setClassName("selector-dditem selected");
        if (iconVisible) {
            itemIcon.getStyle().removeProperty("display");
        }
    }

    @Override
    public void reset() {
        item.setClassName("selector-dditem");
        itemIcon.getStyle().setProperty("display", "none");
    }

    @Override
    public void setSelectionIconVisible(boolean visible) {
        iconVisible = visible;
        if (!iconVisible) {
            itemIcon.getStyle().setProperty("display", "none");
        }
    }

    @EventHandler("itemAnchor")
    public void onItemClick(ClickEvent event) {
        presenter.onItemClick();
    }
}