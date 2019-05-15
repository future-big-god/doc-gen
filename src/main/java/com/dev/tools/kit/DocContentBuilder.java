package com.dev.tools.kit;

import com.dev.tools.kit.domain.DocInfo;
import com.dev.tools.kit.domain.MethodInfo;

/**
 * @Description: 构建文档内容
 * @Author:zhangjianfeng5
 * @Date: 2019/5/15
 * @Time: 下午4:19
 */
public interface DocContentBuilder {

    /**
     * 根据方法信息构建文档内容
     *
     * @param methodInfo
     * @return
     */
    DocInfo build(MethodInfo methodInfo);
}
