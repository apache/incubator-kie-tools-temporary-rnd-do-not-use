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


package org.kie.workbench.common.stunner.core.registry.exception;

public class RegistrySizeExceededException extends RuntimeException {

    private final int maxSize;

    public RegistrySizeExceededException(final int maxSize) {
        this.maxSize = maxSize;
    }

    public RegistrySizeExceededException(final String message,
                                         final int maxSize) {
        super(message);
        this.maxSize = maxSize;
    }

    public RegistrySizeExceededException(final String message,
                                         final Throwable cause,
                                         final int maxSize) {
        super(message,
              cause);
        this.maxSize = maxSize;
    }

    public RegistrySizeExceededException(final Throwable cause,
                                         final int maxSize) {
        super(cause);
        this.maxSize = maxSize;
    }

    public RegistrySizeExceededException(final String message,
                                         final Throwable cause,
                                         final boolean enableSuppression,
                                         final boolean writableStackTrace,
                                         final int maxSize) {
        super(message,
              cause,
              enableSuppression,
              writableStackTrace);
        this.maxSize = maxSize;
    }

    @Override
    public String getMessage() {
        return "Registry size exceeded [max=" + maxSize + "]. " + super.getMessage();
    }
}
