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

import { Header, PMML } from "@kie-tools/pmml-editor-marshaller";
import { Actions, AllActions, HeaderReducer } from "@kie-tools/pmml-editor/dist/editor/reducers";
import { Reducer } from "react";
import { HistoryService } from "@kie-tools/pmml-editor/dist/editor/history";

const service = new HistoryService([]);
const header: Header = { description: "" };
const pmml: PMML = { version: "1.0", DataDictionary: { DataField: [] }, Header: header };
const reducer: Reducer<Header, AllActions> = HeaderReducer(service);

describe("HeaderReducer::Valid actions", () => {
  test("Actions.SetHeaderDescription", () => {
    reducer(header, {
      type: Actions.SetHeaderDescription,
      payload: {
        description: "description",
      },
    });

    const updated: Header = service.commit(pmml)?.Header as Header;

    expect(updated).not.toEqual(header);
    expect(updated.description).toBe("description");
  });
});

describe("HeaderReducer::Invalid actions", () => {
  test("Actions.SetVersion", () => {
    const updated: Header = reducer(header, {
      type: Actions.SetVersion,
      payload: {
        version: "1.0",
      },
    });
    expect(updated).toEqual(header);
  });
});
