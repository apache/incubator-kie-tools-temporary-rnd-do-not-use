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


package org.uberfire.client.views.pfly.widgets;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.CSSStyleDeclaration;
import org.jboss.errai.common.client.dom.Document;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.common.client.dom.Label;

import static org.jboss.errai.common.client.dom.DOMUtil.addCSSClass;

@Dependent
public class FormLabel implements IsElement {

    @Inject
    private Document document;

    @Inject
    private Label label;

    @Override
    public HTMLElement getElement() {
        return label;
    }

    public void addRequiredIndicator() {
        final HTMLElement e = document.createElement("i");
        addCSSClass(e,
                    "fa");
        addCSSClass(e,
                    "fa-star");
        final CSSStyleDeclaration css = e.getStyle();
        css.setProperty("font-size",
                        "6px");
        css.setProperty("padding-right",
                        "5px");
        css.setProperty("padding-left",
                        "2px");
        css.setProperty("color",
                        "#b94a48");
        final HTMLElement sup = document.createElement("sup");
        sup.appendChild(e);
        label.appendChild(sup);
    }
}
