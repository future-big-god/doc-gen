package com.xstore.partner.doc.cf;

import com.xstore.partner.doc.domain.GetResult;

public interface CFClient {
    void createPage(String locationId, String title,String content);

    void updatePage(String pageId,String title,String content);

    GetResult getPageByTitle(String spaceKey,String title);
}
