package com.xstore.partner.doc;

/**
 * 文档生成接口
 */
public interface DocGenerator {
    /**
     * 生成文档 直接同步到cf
     * @param methodId 方法唯一标识
     * @param locationId 文档生成后存放的路径唯一标识
     */
    void generate(String methodId,String locationId);

    /**
     * 批量生成文档 直接同步到cf
     * @param interfaceName
     * @param methodIds
     * @param locationId
     */
    void batchGenerate(String interfaceName,String[] methodIds,String locationId);


    /**
     * 生成文档html 并写入到控制台
     */
    void write2Console(String methodId);
}
