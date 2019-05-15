package com.dev.tools.kit.confluence;

import com.alibaba.fastjson.JSONObject;
import com.dev.tools.kit.config.ConfigConstants;
import com.dev.tools.kit.config.GlobleConfig;
import com.dev.tools.kit.domain.StorageInfo;
import com.dev.tools.kit.http.Auth;
import com.dev.tools.kit.http.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dev.tools.kit.config.ConfigConstants.CONFLUENCE_PASSWORD;
import static com.dev.tools.kit.config.ConfigConstants.CONFLUENCE_USERNAME;


/**
 * @Description:Confluence客户端
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class DefaultConfluenceClient implements ConfluenceClient {

    private HttpClient httpClient;

    public DefaultConfluenceClient() {
        this.httpClient = new HttpClient();
    }

    @Override
    public void createPage(String locationId, String title, String content) {
        this.httpClient.sendJsonPost(GlobleConfig.get(ConfigConstants.CONFLUENCE_SPACE_KEY), buildCreateJsonBody(locationId, title, content), genAuth());
    }


    private Auth genAuth() {
        return new Auth(GlobleConfig.get(CONFLUENCE_USERNAME), GlobleConfig.get(CONFLUENCE_PASSWORD));
    }


    private String buildCreateJsonBody(String locationId, String title, String content) {
        return new PageBuilder().buildCommonAttributes().buildPageTitle(title)
                .buildAncestorId(locationId).buildSpaceObject()
                .buildStorageObject(content).build();

    }

    class PageBuilder {
        private Map<String, Object> dataMap = new HashMap<>();

        public String build() {
            return JSONObject.toJSONString(this.dataMap);
        }

        private PageBuilder buildCommonAttributes() {
            dataMap.put("type", "page");
            return this;
        }

        private PageBuilder buildPageTitle(String title) {
            dataMap.put("title", title);
            return this;
        }

        private PageBuilder buildPageId(String id) {
            dataMap.put("id", id);
            return this;
        }

        private PageBuilder buildVersion(Integer number) {
            com.dev.tools.kit.domain.Version version = new com.dev.tools.kit.domain.Version();
            version.setNumber(number);
            dataMap.put("version", version);
            return this;
        }

        private PageBuilder buildAncestorId(String locationId) {
            List<Map<String, String>> ancestors = new ArrayList<>();
            Map<String, String> ancestorMap = new HashMap<>();
            ancestorMap.put("id", locationId);
            ancestors.add(ancestorMap);
            dataMap.put("ancestors", ancestors);
            return this;
        }

        private PageBuilder buildSpaceObject() {
            Map<String, Object> spaceMap = new HashMap<>();
            spaceMap.put("key", GlobleConfig.get(ConfigConstants.CONFLUENCE_SPACE_KEY));
            dataMap.put("space", spaceMap);
            return this;
        }

        private PageBuilder buildStorageObject(String content) {
            StorageInfo storage = new StorageInfo();
            storage.setValue(content);
            storage.setRepresentation("storage");
            Map<String, Object> storageMap = new HashMap<>();
            storageMap.put("storage", storage);
            dataMap.put("body", storageMap);
            return this;
        }
    }

}
