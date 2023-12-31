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
import org.drools.workbench.screens.scenariosimulation.client.handlers.RunSingleScenarioEventHandler;

/**
 * <code>GwtEvent</code> to <b>run</b> a scenario
 */
public class RunSingleScenarioEvent extends GwtEvent<RunSingleScenarioEventHandler> {

    public static final Type<RunSingleScenarioEventHandler> TYPE = new Type<>();

    private int rowIndex;

    public RunSingleScenarioEvent(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public Type<RunSingleScenarioEventHandler> getAssociatedType() {
        return TYPE;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    protected void dispatch(RunSingleScenarioEventHandler handler) {
        handler.onEvent(this);
    }
}
