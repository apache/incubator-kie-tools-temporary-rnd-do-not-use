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


package org.kie.workbench.common.stunner.bpmn.forms.serializer.cm;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaseRoleSerializerTest {

    private static final String SERIALIZED_ROLE = "role:1";

    private Map.Entry<String, String> entry;

    private List<Map.Entry> entries;

    private CaseRoleSerializer tested;

    @Before
    public void setUp() throws Exception {
        tested = new CaseRoleSerializer();
        entry = new AbstractMap.SimpleEntry("role", "1");
        entries = Arrays.asList(entry);
    }

    @Test
    public void serialize() {
        final String serialized = tested.serialize(Optional.of(entries), Map.Entry::getKey, Map.Entry::getValue);
        assertEquals(serialized, SERIALIZED_ROLE);
    }

    @Test
    public void deserialize() {
        List<AbstractMap.SimpleEntry> deserialized = tested.deserialize(SERIALIZED_ROLE, (k, v) -> new AbstractMap.SimpleEntry(k, v));
        assertEquals(deserialized, entries);
    }
}