package com.xstore.partner;


import com.alibaba.fastjson.JSONObject;
import com.dev.tools.kit.domain.GetResult;
import com.dev.tools.kit.domain.Result;
import com.dev.tools.kit.http.Auth;
import com.dev.tools.kit.http.HttpClient;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class HttpPostTest
{
    @Test
    public void testPost()
    {
        String response = new HttpClient().sendJsonPost("https://confluence.jd.com/rest/api/content/",
                "",
                //"<h2>接口</h2><p>com.jd.xstore.partner.center.api.supplierproduct.SupplierProductService</p><h2>方法</h2><p>findSupplierProductsByStoreIdAndProductId</p><h2>参数</h2><table class=\"confluenceTable\"><tbody><tr><th class=\"confluenceTh\" colspan=\"4\">参数</th></tr><tr><td class=\"confluenceTd\">参数名称</td><td class=\"confluenceTd\">类型</td><td class=\"confluenceTd\">是否必填</td><td class=\"confluenceTd\">备注</td></tr><tr><td class=\"confluenceTd\">tenantId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">否</td><td class=\"confluenceTd\">null</td></tr><tr><td class=\"confluenceTd\">storeId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">否</td><td class=\"confluenceTd\">门店ID</td></tr><tr><td class=\"confluenceTd\">productId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">否</td><td class=\"confluenceTd\">产品Id</td></tr></tbody></table><h2>返回值</h2><p>List<SupplierProduct></p><table class=\"confluenceTable\"><tbody><tr><th class=\"confluenceTh\" colspan=\"4\">com.xstore.partner.center.api.supplierproduct.domain.SupplierProduct</th></tr><tr><td class=\"confluenceTd\">字段名称</td><td class=\"confluenceTd\">类型</td><td class=\"confluenceTd\">备注</td></tr><tr><td class=\"confluenceTd\">productId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">产品ID</td></tr><tr><td class=\"confluenceTd\">stroreId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">门店ID</td></tr><tr><td class=\"confluenceTd\">supplierCode</td><td class=\"confluenceTd\">String</td><td class=\"confluenceTd\">供应商编码</td></tr><tr><td class=\"confluenceTd\">supplierName</td><td class=\"confluenceTd\">String</td><td class=\"confluenceTd\">供应商名称</td></tr><tr><td class=\"confluenceTd\">supplierType</td><td class=\"confluenceTd\">Integer</td><td class=\"confluenceTd\">供应商类型</td></tr><tr><td class=\"confluenceTd\">tenantId</td><td class=\"confluenceTd\">Long</td><td class=\"confluenceTd\">租户ID</td></tr></tbody></table>",
                new Auth("zhoujun5","!Sandbar731208"));
        System.out.println(response);
    }
    @Test
    public void testGet() throws UnsupportedEncodingException {
        String response = new HttpClient().sendJsonGet("https://confluence.jd.com/rest/api/content/?spaceKey=7fresh&title=杀杀杀",
                new Auth("zhoujun5","!Sandbar731208"));
        GetResult getResult=(GetResult) JSONObject.parseObject(response,GetResult.class);
     //   List<Result> resultList=(List<Result>)JSONObject.parseArray(getResult.getResults(),Result.class);
        System.out.println(response);
    }
}