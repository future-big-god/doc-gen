package com.dev.tools.kit.confluence;

/**
 * @Description: ConfluenceClient
 * @Author:zhangjianfeng5
 * @Date: 2019/5/15
 * @Time: 下午4:16
 */
public interface ConfluenceClient {
    /**
     * 生成文档为Confluence页面
     *
     * @param locationId
     * @param title
     * @param content
     */
    void createPage(String locationId, String title, String content);
}
