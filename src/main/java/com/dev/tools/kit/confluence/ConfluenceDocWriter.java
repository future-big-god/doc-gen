package com.dev.tools.kit.confluence;

import com.dev.tools.kit.DocWriter;
import com.dev.tools.kit.domain.DocInfo;

/**
 * @Description:ConfluenceDocWriter
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ConfluenceDocWriter implements DocWriter {
    private ConfluenceClient confluenceClient;

    public ConfluenceDocWriter() {
        this.confluenceClient = new DefaultConfluenceClient();
    }

    @Override
    public void write(String locationId, DocInfo docInfo) {
        confluenceClient.createPage(locationId, docInfo.getTitle(), docInfo.getContent());
    }
}
