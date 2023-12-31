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


package org.kie.workbench.common.stunner.client.widgets.menu.dev.impl;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.kie.workbench.common.stunner.core.client.api.SessionManager;
import org.kie.workbench.common.stunner.core.client.util.StunnerClientLogger;
import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;

@Dependent
public class LogBoundsDevCommand extends AbstractSelectedNodeDevCommand {

    private static Logger LOGGER = Logger.getLogger(LogBoundsDevCommand.class.getName());

    protected LogBoundsDevCommand() {
        this(null);
    }

    @Inject
    public LogBoundsDevCommand(final SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public String getText() {
        return "Log Bounds";
    }

    @Override
    protected void execute(final Node<? extends View<?>, Edge> node) {
        logTask(() -> StunnerClientLogger.logBounds(node));
    }
}
