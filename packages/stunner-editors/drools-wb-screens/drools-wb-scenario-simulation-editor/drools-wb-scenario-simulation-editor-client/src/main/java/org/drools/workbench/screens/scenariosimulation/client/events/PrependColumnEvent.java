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

package org.drools.workbench.screens.scenariosimulation.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.handlers.PrependColumnEventHandler;

/**
 * <code>GwtEvent</code> to <b>prepend</b> (i.e. put in the first position) a column to a given <i>group</i>
 */
public class PrependColumnEvent extends GwtEvent<PrependColumnEventHandler> {

    public static final Type<PrependColumnEventHandler> TYPE = new Type<>();

    private final GridWidget gridWidget;
    private final String columnGroup;

    /**
     *
     * @param gridWidget
     * @param columnGroup
     */
    public PrependColumnEvent(GridWidget gridWidget, String columnGroup) {
        this.gridWidget = gridWidget;
        this.columnGroup = columnGroup;
    }

    @Override
    public Type<PrependColumnEventHandler> getAssociatedType() {
        return TYPE;
    }

    public GridWidget getGridWidget() {
        return gridWidget;
    }

    public String getColumnGroup() {
        return columnGroup;
    }

    @Override
    protected void dispatch(PrependColumnEventHandler handler) {
        handler.onEvent(this);
    }
}
