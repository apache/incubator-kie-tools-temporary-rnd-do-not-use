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


package org.uberfire.ext.widgets.common.client.dropdown.footer;

import org.jboss.errai.common.client.dom.HTMLElement;
import org.uberfire.client.mvp.UberElement;

public interface LiveSearchFooterView extends UberElement<LiveSearchFooterView.Presenter> {

    void showReset(boolean show);

    void showAddNewEntry(boolean show);

    void show(HTMLElement element);

    void restore();

    void setResetLabel(String resetLabel);

    void setNewEntryLabel(String newEntryLabel);

    interface Presenter {

        void onNewEntryPressed();

        void onResetPressed();
    }

}
