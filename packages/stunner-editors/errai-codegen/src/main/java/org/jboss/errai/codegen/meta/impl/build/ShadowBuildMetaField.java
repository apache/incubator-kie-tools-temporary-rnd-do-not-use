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


package org.jboss.errai.codegen.meta.impl.build;

import java.lang.reflect.Field;

import org.jboss.errai.codegen.Statement;
import org.jboss.errai.codegen.builder.impl.Scope;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.meta.MetaField;

/**
 * @author Mike Brock
 */
public class ShadowBuildMetaField extends BuildMetaField {
  private MetaField shadow;

  public ShadowBuildMetaField(BuildMetaClass declaringClass, Statement statement, Scope scope, MetaClass type, String name, MetaField shadow) {
    super(declaringClass, statement, scope, type, name);
    this.shadow = shadow;
  }


  @Override
  public Field asField() {
    return shadow.asField();
  }

}
