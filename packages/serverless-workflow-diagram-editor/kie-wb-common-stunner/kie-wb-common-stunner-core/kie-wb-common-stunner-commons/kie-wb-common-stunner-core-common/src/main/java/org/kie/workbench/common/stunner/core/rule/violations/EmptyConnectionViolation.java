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


package org.kie.workbench.common.stunner.core.rule.violations;

import java.util.Optional;

import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Element;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;

public class EmptyConnectionViolation extends AbstractRuleViolation {

    private final String sourceUUID;
    private final String targetUUID;

    public EmptyConnectionViolation(final String sourceUUID,
                                    final String targetUUID) {
        this.sourceUUID = sourceUUID;
        this.targetUUID = targetUUID;
    }

    @Override
    public Optional<Object[]> getArguments() {
        return of(getUUID(), sourceUUID, targetUUID);
    }

    @Override
    public String getMessage() {
        return "Empty Connection "
                + "[connector=" + getUUID()
                + "[source=" + sourceUUID
                + "[target=" + targetUUID
                + "]";
    }

    public static class Builder {

        public static EmptyConnectionViolation build(final Edge<? extends View<?>, ? extends Node> connector,
                                                     final Optional<Node<? extends View<?>, ? extends Edge>> sourceNode,
                                                     final Optional<Node<? extends View<?>, ? extends Edge>> targetNode) {
            // Violation objects are portable so avoid use of optionals, just null or not null.
            final String sourceUUID = sourceNode.map(Element::getUUID).orElse(null);
            final String targetUUID = targetNode.map(Element::getUUID).orElse(null);
            return (EmptyConnectionViolation) new EmptyConnectionViolation(sourceUUID,
                                                                           targetUUID)
                    .setUUID(connector.getUUID());
        }
    }
}
