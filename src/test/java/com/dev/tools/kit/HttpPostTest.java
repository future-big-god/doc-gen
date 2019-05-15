package com.dev.tools.kit;


import com.dev.tools.kit.http.Auth;
import com.dev.tools.kit.http.HttpClient;
import org.junit.Test;


public class HttpPostTest {
    @Test
    public void testPost() {
        String response = new HttpClient().sendJsonPost("https://confluence.jd.com/rest/api/content/",
                "",
                new Auth("zhoujun5", "*********"));
        System.out.println(response);
    }

}