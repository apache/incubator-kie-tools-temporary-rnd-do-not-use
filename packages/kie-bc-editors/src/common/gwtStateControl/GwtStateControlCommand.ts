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

/**
 * Represents a Command that has been registered into the {@link StateControlCommandRegistry}. It has a id (string) to identify
 * the command on the registry and a executable ({@template T}) that represents the real executable command pushed to
 * the registry.
 */
export class GwtStateControlCommand<T> {
  private readonly id: string;
  private readonly executable: T;

  constructor(id: string, executable: T) {
    this.id = id;
    this.executable = executable;
  }

  public getId(): string {
    return this.id;
  }

  public get(): T {
    return this.executable;
  }
}
