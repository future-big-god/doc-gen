package com.dev.tools.kit.confluence;

import com.dev.tools.kit.domain.GetResult;

public interface ConfluenceClient {
    void createPage(String locationId, String title,String content);

    void updatePage(String pageId,String title,String content);

    GetResult getPageByTitle(String spaceKey,String title);
}
