/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.perftool.trace.util;

import java.util.Objects;

public class IDUtil {
//
    private static String ipNumber = StringTool.formatIp(
            Objects.requireNonNull(Ipv4Util.getIp(EnvUtil.getString("ENV_ETH_NAME", "eth0"))));
    private static String TRACE_PREFIX = EnvUtil.getString("TRACE_REPORT_SCENE", "dummy");

    public static String generateTargetId(int maxNumber) {
        long createTime = System.currentTimeMillis();
        InboundCounter inboundCounter = new InboundCounter(maxNumber);
        String traceId = String.format("%s-%s-%s-%d",
                TRACE_PREFIX,
                createTime,
                ipNumber,
                inboundCounter.get());
        return traceId;
    }
}
