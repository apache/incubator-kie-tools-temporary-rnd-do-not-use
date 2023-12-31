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


package org.jboss.errai.databinding.client.api.handler.list;

import java.util.List;

/**
 *
 * @author Max Barkley <mbarkley@redhat.com>
 */
@FunctionalInterface
public interface ItemChangedHandler<M> {

  /**
   * Called when a single item has been changed.
   *
   * @param source
   *          a list representing the state before the item was changed (equal to the old value of
   *          the list). Never null.
   * @param index
   *          the index of the item that has changed.
   * @param item
   *          the new value of the item at the provided index.
   */
  public void onItemChanged(List<M> source, int index, M item);

}
