package com.dev.tools.kit;

import com.dev.tools.kit.domain.MethodInfo;

public interface DocWriter {
    /**
     * 生成到CF
     * @param locationId
     * @param methodInfo
     */
    void write2Cf(String locationId, MethodInfo methodInfo);

    /**
     * 生成html到控制台
     * @param parse
     */
    void write2Console(MethodInfo parse);
}
