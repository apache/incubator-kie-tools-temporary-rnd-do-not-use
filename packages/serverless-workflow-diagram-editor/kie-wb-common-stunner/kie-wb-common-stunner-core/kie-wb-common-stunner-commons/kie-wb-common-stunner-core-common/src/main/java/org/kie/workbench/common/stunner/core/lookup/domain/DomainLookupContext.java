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


package org.kie.workbench.common.stunner.core.lookup.domain;

import org.kie.workbench.common.stunner.core.api.DefinitionManager;
import org.kie.workbench.common.stunner.core.registry.impl.DefinitionsCacheRegistry;
import org.kie.workbench.common.stunner.core.rule.RuleManager;

public class DomainLookupContext {

    private final DefinitionManager definitionManager;
    private final DefinitionsCacheRegistry definitionsRegistry;
    private final RuleManager ruleManager;
    private final DomainLookupsCache cache;

    DomainLookupContext(final DefinitionManager definitionManager,
                        final DefinitionsCacheRegistry definitionsRegistry,
                        final RuleManager ruleManager,
                        final DomainLookupsCache cache) {
        this.definitionManager = definitionManager;
        this.definitionsRegistry = definitionsRegistry;
        this.ruleManager = ruleManager;
        this.cache = cache;
    }

    public DefinitionManager getDefinitionManager() {
        return definitionManager;
    }

    public DefinitionsCacheRegistry getDefinitionsRegistry() {
        return definitionsRegistry;
    }

    public RuleManager getRuleManager() {
        return ruleManager;
    }

    public DomainLookupsCache getCache() {
        return cache;
    }
}
