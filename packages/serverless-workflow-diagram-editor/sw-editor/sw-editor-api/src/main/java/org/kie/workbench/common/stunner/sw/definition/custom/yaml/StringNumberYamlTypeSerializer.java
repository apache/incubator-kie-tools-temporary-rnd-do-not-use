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

package org.kie.workbench.common.stunner.sw.definition.custom.yaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.YAMLDeserializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.YAMLSerializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.exception.YAMLDeserializationException;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.deser.StringYAMLDeserializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.deser.YAMLDeserializationContext;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.BaseNumberYAMLSerializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.StringYAMLSerializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.YAMLSerializationContext;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.stream.YAMLSequenceWriter;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.stream.YAMLWriter;

import static com.amihaiemil.eoyaml.Node.SCALAR;

public class StringNumberYamlTypeSerializer implements YAMLDeserializer, YAMLSerializer {

    private static final StringYAMLSerializer stringJsonSerializer = new StringYAMLSerializer();

    private static final StringYAMLDeserializer stringJsonDeserializer = new StringYAMLDeserializer();

    private static final BaseNumberYAMLSerializer.IntegerYAMLSerializer integerJsonSerializer = new BaseNumberYAMLSerializer.IntegerYAMLSerializer();

    @Override
    public Object deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) throws YAMLDeserializationException {
        YamlNode value = yaml.value(key);
        if (value == null) {
            return null;
        }
        return deserialize(value, ctx);
    }

    @Override
    public Object deserialize(YamlNode node, YAMLDeserializationContext ctx) {
        if(node.type() == SCALAR) {
            String value =  stringJsonDeserializer.deserialize(node, ctx);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return value;
            }
        }
        return null;
    }

    @Override
    public void serialize(YAMLWriter writer, String propertyName, Object value, YAMLSerializationContext ctx) {
        if (value instanceof String) {
            stringJsonSerializer.serialize(writer, propertyName, (String) value, ctx);
        } else if (value instanceof Integer) {
            integerJsonSerializer.serialize(writer, propertyName, ((Integer) value), ctx);
        }
    }

    @Override
    public void serialize(YAMLSequenceWriter writer, Object value, YAMLSerializationContext ctx) {
        if (value instanceof String) {
            stringJsonSerializer.serialize(writer, (String) value, ctx);
        } else if (value instanceof Integer) {
            integerJsonSerializer.serialize(writer, ((Integer) value), ctx);
        }
    }
}