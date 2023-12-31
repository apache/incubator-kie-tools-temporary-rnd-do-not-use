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


package org.uberfire.workbench.model;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.uberfire.annotations.FallbackImplementation;

/**
 * Default implementation for {@link AppFormerActivities}. To override it, just provide a default
 * CDI bean that implements {@link AppFormerActivities}.
 */
@ApplicationScoped
@FallbackImplementation
public class DefaultAppFormerActivities implements AppFormerActivities {

    protected DefaultAppFormerActivities() {
    }

    @Override
    public List<String> getAllEditorIds() {
        return Arrays.asList();
    }

    @Override
    public List<String> getAllPerpectivesIds() {
        return Arrays.asList();
    }

}
