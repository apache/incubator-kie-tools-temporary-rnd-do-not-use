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

import * as React from "react";
import { BoxedExpressionEditor } from "../src/expressions";
import { BeeGwtService, DmnDataType, ExpressionDefinition, PmmlParam } from "../src/api";
import { beeGwtService, pmmlParams, dataTypes } from "./boxedExpressionStoriesWrapper";

export function BoxedExpressionComponentWrapper(props: {
  expressionDefinition: ExpressionDefinition;
  dataTypes?: DmnDataType[];
  pmmlParams?: PmmlParam[];
  beeGwtService?: BeeGwtService;
  isResetSupportedOnRootExpression?: boolean;
}) {
  const emptyRef = React.useRef<HTMLDivElement>(null);

  return (
    <div ref={emptyRef}>
      <BoxedExpressionEditor
        decisionNodeId={"_00000000-0000-0000-0000-000000000000"}
        expressionDefinition={props.expressionDefinition}
        setExpressionDefinition={() => {}}
        dataTypes={props.dataTypes ?? dataTypes}
        scrollableParentRef={emptyRef}
        beeGwtService={props.beeGwtService ?? beeGwtService}
        pmmlParams={props.pmmlParams ?? pmmlParams}
        isResetSupportedOnRootExpression={props.isResetSupportedOnRootExpression}
      />
    </div>
  );
}
