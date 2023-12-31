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


package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;

/**
 * An interface which is implemented by the code generator to allow equality tests on annotations which consider
 * their attributes as part of the equality check.
 *
 * @author Mike Brock
 */
public interface QualifierEqualityFactory {
  public boolean isEqual(Annotation a1, Annotation a2);

  public int hashCodeOf(Annotation a1);
}
