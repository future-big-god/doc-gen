package com.xstore.partner.doc.cf;

import com.alibaba.fastjson.JSONObject;
import com.xstore.partner.doc.domain.GetResult;
import com.xstore.partner.doc.domain.StorageInfo;
import com.xstore.partner.doc.http.Auth;
import com.xstore.partner.doc.http.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xstore.partner.doc.ConfigConstants.CF_PASSWORD;
import static com.xstore.partner.doc.ConfigConstants.CF_USERNAME;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class DefaultCFClient implements CFClient {

    private static final String url = "https://cf.jd.com/rest/api/content/";


    private HttpClient httpClient;

    public DefaultCFClient() {
        this.httpClient = new HttpClient();
    }

    @Override
    public void createPage(String locationId, String title, String content) {
        this.httpClient.sendJsonPost(url,buildCreateJsonBody(locationId,title,content),genAuth());
    }

    @Override
    public void updatePage(String pageId, String title, String content) {

        this.httpClient.sendJsonPost(url,buildUpdateJsonBody(pageId,title,content),genAuth());
    }

    @Override
    public GetResult getPageByTitle(String spaceKey, String title) {
        String getUrl=url+"?spaceKey="+spaceKey+"&title="+title;
        String response=this.httpClient.sendJsonGet(getUrl,genAuth());
        GetResult getResult=(GetResult) JSONObject.parseObject(response,GetResult.class);
        return getResult;
    }

    private Auth genAuth() {
        return new Auth(System.getProperty(CF_USERNAME) != null ? System.getProperty(CF_USERNAME) : System.getenv().get(CF_USERNAME),
                System.getProperty(CF_PASSWORD) != null ? System.getProperty(CF_PASSWORD) : System.getenv().get(CF_PASSWORD));
    }


    private String buildCreateJsonBody(String locationId, String title, String content) {
        return new PageBuilder().buildCommonAttributes().buildPageTitle(title)
                         .buildAncestorId(locationId).buildSpaceObject()
                         .buildStorageObject(content).build();

    }
    private String buildUpdateJsonBody(String pageId, String title, String content) {
        return new PageBuilder().buildCommonAttributes().buildPageTitle(title)
                                .buildPageId(pageId).buildSpaceObject().buildVersion(2)
                                .buildStorageObject(content).build();

    }

    class PageBuilder{

         private  Map<String,Object> dataMap=new HashMap<>();
         private PageBuilder buildCommonAttributes(){
             dataMap.put("type","page");
            return this;
        }
         private PageBuilder buildPageTitle(String title){
             dataMap.put("title",title);
            return this;
        }
        private PageBuilder buildPageId(String id){
            dataMap.put("id",id);
            return this;
        }
        private PageBuilder buildVersion(Integer number){
            com.xstore.partner.doc.domain.Version version=new com.xstore.partner.doc.domain.Version();
            version.setNumber(number);
            dataMap.put("version",version);
            return this;
        }
        private PageBuilder buildAncestorId(String locationId){
            List<Map<String,String>> ancestors = new ArrayList<>();
            Map<String,String> ancestorMap = new HashMap<>();
            ancestorMap.put("id",locationId);
            ancestors.add(ancestorMap);
            dataMap.put("ancestors",ancestors);
            return this;
        }
        private PageBuilder buildSpaceObject(){
            Map<String,Object> spaceMap=new HashMap<>();
            spaceMap.put("key","7fresh");
            dataMap.put("space",spaceMap);
            return this;
        }
        private PageBuilder buildStorageObject(String content){
            StorageInfo storage=new StorageInfo();
            storage.setValue(content);
            storage.setRepresentation("storage");
            Map<String,Object> storageMap=new HashMap<>();
            storageMap.put("storage",storage);
            dataMap.put("body",storageMap);
            return this;
        }

        public String build() {
            return JSONObject.toJSONString(this.dataMap);
        }
    }

}
