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
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioNotificationEventHandler;
import org.uberfire.workbench.events.NotificationEvent;

/**
 * <code>GwtEvent</code> to show a notification
 */
public class ScenarioNotificationEvent extends GwtEvent<ScenarioNotificationEventHandler> {

    public static final Type<ScenarioNotificationEventHandler> TYPE = new Type<>();

    private final String message;
    private final boolean autoHide;
    private final org.uberfire.workbench.events.NotificationEvent.NotificationType notificationType;

    public ScenarioNotificationEvent(String message, NotificationEvent.NotificationType type) {
        this.message = message;
        this.notificationType = type;
        this.autoHide = true;
    }

    public ScenarioNotificationEvent(String message, NotificationEvent.NotificationType type, boolean autoHide) {
        this.message = message;
        this.notificationType = type;
        this.autoHide = autoHide;
    }

    @Override
    public Type<ScenarioNotificationEventHandler> getAssociatedType() {
        return TYPE;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAutoHide() {
        return autoHide;
    }

    public NotificationEvent.NotificationType getNotificationType() {
        return notificationType;
    }

    @Override
    protected void dispatch(ScenarioNotificationEventHandler handler) {
        handler.onEvent(this);
    }
}
