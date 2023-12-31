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

package org.uberfire.ext.wires.core.grids.client.util;

/**
 * Utilities class
 */
public class MathUtilities {

    private static final double EPSILON = 0.0000001;

    /**
     * Convenience method to check if a double is "almost" one.
     * @param value A value to be checked.
     * @return true if the value is "almost" one.
     */
    public static boolean isOne(final double value) {
        return value >= 1.0 - EPSILON && value <= 1.0 + EPSILON;
    }
}
