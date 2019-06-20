package com.dev.tools.kit;

import com.dev.tools.kit.domain.DocInfo;

/**
 * @Description: 文档生成接口
 * @Author:zhangjianfeng5
 * @Date: 2019/3/23
 * @Time: 下午12:42
 */
public interface DocGenerator {
    /**
     * 生成文档 直接同步到相应位置
     *
     * @param methodId   方法唯一标识
     * @param locationId 文档生成后存放的路径唯一标识
     */
    void generateAndWrite(String methodId, String locationId);

    /**
     * 批量生成文档 直接同步到相应位置
     *
     * @param interfaceName
     * @param methodIds
     * @param locationId
     */
    void batchGenerateAndWrite(String interfaceName, String[] methodIds, String locationId);

    /**
     * 生成文档内容并返回
     */
    DocInfo generate(String methodId);
}
