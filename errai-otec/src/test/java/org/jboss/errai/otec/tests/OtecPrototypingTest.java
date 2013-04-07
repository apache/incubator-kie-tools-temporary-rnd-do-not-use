/*
 * Copyright 2013 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.otec.tests;

import static junit.framework.Assert.assertEquals;

import org.jboss.errai.otec.mutation.*;
import org.jboss.errai.otec.mutation.IndexPosition;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mike Brock
 */

public class OtecPrototypingTest {
  OTEngine clientEngine;
  OTEngine serverEngine;
  OTEntity clientOtEntity;

  @Before
  public void setUp() throws Exception {
    clientEngine = new OTEngineImpl();
    serverEngine = new OTEngineImpl();

    clientEngine.registerPeer(new MockPeerImpl(serverEngine));
    serverEngine.registerPeer(new MockPeerImpl(clientEngine));

    final String myEntityOfStringFun = "Hello, World?";
    final StringState state = new StringState(myEntityOfStringFun);
    clientOtEntity = serverEngine.getEntityStateSpace().addEntity(state);

    clientEngine.syncRemoteEntity(serverEngine.getId(), clientOtEntity.getId(), new MockEntitySyncCompletionCallback());
  }

  @Test
  public void testApplyOperationsLocally() {
    final OTOperationsFactory operationsFactory = clientEngine.getOperationsFactory();
    final OTOperationList list = operationsFactory.createOperationsList(clientOtEntity)
        .add(OperationType.Delete, IndexPosition.of(12))
        .add(OperationType.Insert, IndexPosition.of(12), CharacterData.of('!'))
        .build();

    clientEngine.applyOperationsLocally(list);

    assertEquals("Hello, World!", clientOtEntity.getState().get());
  }

  @Test
  public void testNotifyOperations() {
    final OTOperationsFactory operationsFactory = clientEngine.getOperationsFactory();
    final OTOperationList list = operationsFactory.createOperationsList(clientOtEntity)
        .add(OperationType.Delete, IndexPosition.of(12))
        .add(OperationType.Insert, IndexPosition.of(12), CharacterData.of('!'))
        .build();

    clientEngine.notifyOperations(list);

    final OTEntity entity = serverEngine.getEntityStateSpace().getEntity(clientOtEntity.getId());
    assertEquals("Hello, World!", entity.getState().get());
  }
}
