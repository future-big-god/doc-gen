package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.MethodInfo;

/**
 * @Description: 方法解析
 * @Author:zhangjianfeng5
 * @Date: 2019/5/15
 * @Time: 下午4:21
 */
public interface MethodParser {

    /**
     * 解析方法
     *
     * @param methodId
     * @return
     */
    MethodInfo parse(String methodId);
}
