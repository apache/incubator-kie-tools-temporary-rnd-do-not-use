/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.stunner.sw.client.shapes;

import org.appformer.kogito.bridge.client.resource.ResourceContentService;
import org.kie.workbench.common.stunner.core.client.shape.MutationContext;
import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;
import org.kie.workbench.common.stunner.sw.client.shapes.icons.CornerIcon;
import org.kie.workbench.common.stunner.sw.client.shapes.icons.IconPath;
import org.kie.workbench.common.stunner.sw.definition.ForEachState;
import org.kie.workbench.common.stunner.sw.definition.State;

public class ForEachStateShape extends StateShape {

    public static final String ICON_COLOR = "#8F4700";
    public static final String ICON_SVG = "M64,32.22v27.24c0,2.34-1.91,4.49-4.24,4.65-2.68,.19-4.9-1.92-4.9-4.56v-27.43c0-2.64,2.22-4.75,4.9-4.56,2.34,.16,4.24,2.31,4.24,4.65M45.71,59.56c0,2.64-2.22,4.75-4.9,4.56-2.34-.16-4.24-2.31-4.24-4.65v-27.24c0-2.34,1.91-4.49,4.24-4.65,2.68-.19,4.9,1.92,4.9,4.56v27.43M27.43,4.79v27.24c0,2.34-1.91,4.49-4.24,4.65-2.68,.19-4.9-1.92-4.9-4.56V4.7C18.29,2.07,20.51-.04,23.18,.14c2.34,.16,4.24,2.31,4.24,4.65M9.14,32.13c0,2.64-2.22,4.75-4.9,4.56C1.91,36.53,0,34.38,0,32.04V4.79C0,2.45,1.91,.3,4.24,.14c2.68-.19,4.9,1.92,4.9,4.56v27.43Z";

    public ForEachStateShape(State state, ResourceContentService resourceContentService) {
        super(state, resourceContentService);
    }

    @Override
    public void applyProperties(Node<View<State>, Edge> element, MutationContext mutationContext) {
        super.applyProperties(element, mutationContext);
        ForEachState state = (ForEachState) element.getContent().getDefinition();
        if (state.getTimeouts() != null) {
            getView().addChild(new CornerIcon(IconPath.CLOCK,
                                              "StateExecTimeout: " + state.getTimeouts().getStateExecTimeout() + "\r\n"
                                                      + "ActionExecTimeout: " + state.getTimeouts().getActionExecTimeout()));
        }
    }

    @Override
    public String getIconColor() {
        return ICON_COLOR;
    }

    @Override
    public String getIconSvg() {
        return ICON_SVG;
    }
}
