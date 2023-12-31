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

package org.uberfire.client.workbench.panels.support;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uberfire.workbench.model.PartDefinition;

import static org.jgroups.util.Util.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(GwtMockitoTestRunner.class)
public class PartManagerTest {

    private PartManager partManager;

    @Test
    public void getPartsShouldReturnCurrentWidgets() {
        partManager = new PartManager();

        partManager.registerPart(mock(PartDefinition.class),
                                 mock(Widget.class));
        partManager.registerPart(mock(PartDefinition.class),
                                 mock(Widget.class));

        assertEquals(2,
                     partManager.getParts().size());
    }
}