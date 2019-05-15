package com.dev.tools.kit.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Description:HTTP客户端
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class HttpClient {

    private static final int successCode = 200;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String sendJsonPost(String url, String content, Auth auth) {

        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Authorization", getAuthHeader(auth)); //认证token
            httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
            httppost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httppost.setEntity(new StringEntity(content, "UTF-8"));
            response = httpclient.execute(httppost);
            int code = response.getStatusLine().getStatusCode();
            if (successCode == code) {
                return EntityUtils.toString(response.getEntity());
            }
            throw new RuntimeException("http code:" + code + ",response:" + EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            this.close(httpclient, response);
        }
    }

    private void close(CloseableHttpClient httpclient, CloseableHttpResponse response) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                logger.error("close error", e);
            }
        }
        if (httpclient != null) {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("close error", e);
            }
        }
    }

    private String getAuthHeader(Auth auth) {
        return "Basic " + new String(Base64.encodeBase64(
                (auth.getUsername() + ":" + auth.getPassword()).getBytes()));
    }

}
