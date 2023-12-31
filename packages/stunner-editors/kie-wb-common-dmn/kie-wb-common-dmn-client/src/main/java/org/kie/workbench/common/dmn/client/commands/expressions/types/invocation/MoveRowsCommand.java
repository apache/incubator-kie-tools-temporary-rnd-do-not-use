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

package org.kie.workbench.common.dmn.client.commands.expressions.types.invocation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.kie.workbench.common.dmn.api.definition.model.Binding;
import org.kie.workbench.common.dmn.api.definition.model.Invocation;
import org.kie.workbench.common.dmn.client.commands.VetoExecutionCommand;
import org.kie.workbench.common.dmn.client.commands.VetoUndoCommand;
import org.kie.workbench.common.dmn.client.commands.util.CommandUtils;
import org.kie.workbench.common.dmn.client.widgets.grid.model.DMNGridData;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.command.AbstractCanvasCommand;
import org.kie.workbench.common.stunner.core.client.canvas.command.AbstractCanvasGraphCommand;
import org.kie.workbench.common.stunner.core.client.command.CanvasCommandResultBuilder;
import org.kie.workbench.common.stunner.core.client.command.CanvasViolation;
import org.kie.workbench.common.stunner.core.command.Command;
import org.kie.workbench.common.stunner.core.command.CommandResult;
import org.kie.workbench.common.stunner.core.graph.command.GraphCommandExecutionContext;
import org.kie.workbench.common.stunner.core.graph.command.GraphCommandResultBuilder;
import org.kie.workbench.common.stunner.core.graph.command.impl.AbstractGraphCommand;
import org.kie.workbench.common.stunner.core.rule.RuleViolation;
import org.uberfire.ext.wires.core.grids.client.model.GridRow;

public class MoveRowsCommand extends AbstractCanvasGraphCommand implements VetoExecutionCommand,
                                                                           VetoUndoCommand {

    private final Invocation invocation;
    private final DMNGridData uiModel;
    private final int index;
    private final List<GridRow> uiRows;
    private final org.uberfire.mvp.Command canvasOperation;

    private final int oldIndex;

    public MoveRowsCommand(final Invocation invocation,
                           final DMNGridData uiModel,
                           final int index,
                           final List<GridRow> uiRows,
                           final org.uberfire.mvp.Command canvasOperation) {
        this.invocation = invocation;
        this.uiModel = uiModel;
        this.index = index;
        this.uiRows = new ArrayList<>(uiRows);
        this.canvasOperation = canvasOperation;

        this.oldIndex = uiModel.getRows().indexOf(uiRows.get(0));
    }

    @Override
    protected Command<GraphCommandExecutionContext, RuleViolation> newGraphCommand(final AbstractCanvasHandler ach) {
        return new AbstractGraphCommand() {
            @Override
            protected CommandResult<RuleViolation> check(final GraphCommandExecutionContext gcec) {
                if (index == uiModel.getRowCount() - 1) {
                    return GraphCommandResultBuilder.failed();
                }
                return GraphCommandResultBuilder.SUCCESS;
            }

            @Override
            public CommandResult<RuleViolation> execute(final GraphCommandExecutionContext gcec) {
                moveRows(index);

                return GraphCommandResultBuilder.SUCCESS;
            }

            @Override
            public CommandResult<RuleViolation> undo(final GraphCommandExecutionContext gcec) {
                moveRows(oldIndex);

                return GraphCommandResultBuilder.SUCCESS;
            }

            private void moveRows(final int index) {
                final List<Binding> rowsToMove = uiRows
                        .stream()
                        .map(r -> uiModel.getRows().indexOf(r))
                        .map(i -> invocation.getBinding().get(i))
                        .collect(Collectors.toList());

                final List<Binding> rows = invocation.getBinding();

                CommandUtils.moveRows(rows,
                                      rowsToMove,
                                      index);
            }
        };
    }

    @Override
    protected Command<AbstractCanvasHandler, CanvasViolation> newCanvasCommand(final AbstractCanvasHandler ach) {
        return new AbstractCanvasCommand() {

            @Override
            public CommandResult<CanvasViolation> allow(AbstractCanvasHandler context) {
                if (index == uiModel.getRowCount() - 1) {
                    return CanvasCommandResultBuilder.failed();
                }
                return CanvasCommandResultBuilder.SUCCESS;
            }

            @Override
            public CommandResult<CanvasViolation> execute(final AbstractCanvasHandler ach) {
                uiModel.moveRowsTo(index,
                                   uiRows);

                updateRowNumbers();
                updateParentInformation();

                canvasOperation.execute();

                return CanvasCommandResultBuilder.SUCCESS;
            }

            @Override
            public CommandResult<CanvasViolation> undo(final AbstractCanvasHandler ach) {
                uiModel.moveRowsTo(oldIndex,
                                   uiRows);

                updateRowNumbers();
                updateParentInformation();

                canvasOperation.execute();

                return CanvasCommandResultBuilder.SUCCESS;
            }
        };
    }

    public void updateRowNumbers() {
        CommandUtils.updateRowNumbers(uiModel,
                                      IntStream.range(0,
                                                      uiModel.getRowCount()));
    }

    public void updateParentInformation() {
        CommandUtils.updateParentInformation(uiModel);
    }
}
