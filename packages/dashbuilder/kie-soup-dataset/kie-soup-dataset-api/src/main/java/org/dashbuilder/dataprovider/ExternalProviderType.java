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

package org.dashbuilder.dataprovider;

import org.dashbuilder.dataset.def.ExternalDataSetDef;
import org.dashbuilder.dataset.json.DataSetDefJSONMarshallerExt;
import org.dashbuilder.dataset.json.ExternalDefJSONMarshaller;

/**
 * For accessing data sets that are running outside of DashBuilder processes.
 */
public class ExternalProviderType extends AbstractProviderType<ExternalDataSetDef> {

    @Override
    public String getName() {
        return "External";
    }

    @Override
    public ExternalDataSetDef createDataSetDef() {
        return new ExternalDataSetDef();
    }

    @Override
    public DataSetDefJSONMarshallerExt<ExternalDataSetDef> getJsonMarshaller() {
        return ExternalDefJSONMarshaller.INSTANCE;
    }
}