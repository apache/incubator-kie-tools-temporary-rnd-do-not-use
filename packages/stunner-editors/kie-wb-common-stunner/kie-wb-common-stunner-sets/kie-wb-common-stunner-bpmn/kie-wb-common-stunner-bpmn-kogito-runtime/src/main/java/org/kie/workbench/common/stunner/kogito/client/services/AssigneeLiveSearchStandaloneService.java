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


package org.kie.workbench.common.stunner.kogito.client.services;

import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.kie.workbench.common.stunner.bpmn.client.forms.fields.assigneeEditor.AssigneeLiveSearchService;
import org.kie.workbench.common.stunner.bpmn.client.forms.fields.assigneeEditor.AssigneeLocalSearchService;
import org.kie.workbench.common.stunner.bpmn.client.forms.fields.assigneeEditor.widget.AssigneeLiveSearchEntryCreationEditor;
import org.kie.workbench.common.stunner.bpmn.forms.model.AssigneeType;
import org.uberfire.ext.widgets.common.client.dropdown.LiveSearchCallback;

@Dependent
public class AssigneeLiveSearchStandaloneService implements AssigneeLiveSearchService {

    private final AssigneeLiveSearchEntryCreationEditor editor;
    private AssigneeLocalSearchService localSearchService;

    @Inject
    public AssigneeLiveSearchStandaloneService(final AssigneeLiveSearchEntryCreationEditor editor) {
        this.editor = editor;
    }

    @PostConstruct
    public void postConstruct() {
        this.localSearchService = AssigneeLocalSearchService.build(editor);
    }

    @Override
    public void init(final AssigneeType type) {
    }

    @Override
    public void addCustomEntry(final String customEntry) {
        localSearchService.addCustomEntry(customEntry);
    }

    @Override
    public void setSearchErrorHandler(final Consumer<Throwable> searchErrorHandler) {
    }

    @Override
    public void search(final String pattern,
                       final int maxResults,
                       final LiveSearchCallback<String> callback) {
        localSearchService.search(pattern, maxResults, callback);
    }

    @Override
    public void searchEntry(final String key,
                            final LiveSearchCallback<String> callback) {
        localSearchService.searchEntry(key, callback);
    }

    @Override
    public AssigneeLiveSearchEntryCreationEditor getEditor() {
        return editor;
    }

    @PreDestroy
    public void destroy() {
        localSearchService.destroy();
        localSearchService = null;
    }
}
