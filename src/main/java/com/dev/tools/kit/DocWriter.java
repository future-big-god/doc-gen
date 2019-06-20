package com.dev.tools.kit;


import com.dev.tools.kit.domain.DocInfo;

/**
 * @Description: 同步文档接口
 * @Author:zhangjianfeng5
 * @Date: 2019/3/23
 * @Time: 下午12:42
 */
public interface DocWriter {
    /**
     * 生成文档到相应位置
     *
     * @param locationId
     * @param content
     */
    void write(String locationId, DocInfo content);

}
