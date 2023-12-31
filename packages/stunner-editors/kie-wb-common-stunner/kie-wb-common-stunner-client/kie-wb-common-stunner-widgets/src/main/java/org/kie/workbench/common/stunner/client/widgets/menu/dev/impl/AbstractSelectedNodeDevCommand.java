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

import java.util.Collection;

import org.kie.workbench.common.stunner.core.client.api.SessionManager;
import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Element;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;

public abstract class AbstractSelectedNodeDevCommand
        extends AbstractSelectionDevCommand {

    protected AbstractSelectedNodeDevCommand(final SessionManager sessionManager) {
        super(sessionManager);
    }

    protected abstract void execute(Node<? extends View<?>, Edge> node);

    @Override
    protected void execute(final Collection<Element<? extends View<?>>> items) {
        if (1 == items.size()) {
            final Element<? extends View<?>> e = items.iterator().next();
            if (null != e.asNode()) {
                execute(e.asNode());
            }
        }
    }
}
