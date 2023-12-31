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


package org.kie.workbench.common.stunner.core.validation;

import java.util.Collection;
import java.util.function.Consumer;

import org.kie.workbench.common.stunner.core.graph.Graph;
import org.kie.workbench.common.stunner.core.rule.RuleSet;

/**
 * Base validator type for the a graph structure.
 * @param <G> The graph type.
 * @param <V> The graph element violation type.
 */
public interface GraphValidator<G extends Graph, V extends ElementViolation>
        extends Validator<G, V> {

    /**
     * Validates the <code>graph</code> instance by using a
     * concrete set of rules given by the argument <code>ruleSet</code>.
     * The <code>resultConsumer</code> is guaranteed to be called once validation finished
     * and provides the different violations, if any.
     */
    void validate(Graph graph,
                  RuleSet ruleSet,
                  Consumer<Collection<V>> resultConsumer);
}
