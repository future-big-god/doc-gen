package com.dev.tools.kit;


import com.dev.tools.kit.config.ConfigConstants;
import com.dev.tools.kit.config.GlobleConfig;
import com.dev.tools.kit.confluence.ConfluenceDocGenerator;
import org.junit.Test;

public class AppTest {
    @Test
    public void test() {
        GlobleConfig.config(ConfigConstants.CONFLUENCE_USERNAME, "zhoujun5");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        GlobleConfig.config(ConfigConstants.CONFLUENCE_PASSWORD, "*******");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        GlobleConfig.config(ConfigConstants.CONFLUENCE_REST_PATH, "https://confluence.xx.com/rest/api/content/");//配置confluence的rest path
        GlobleConfig.config(ConfigConstants.CONFLUENCE_SPACE_KEY, "7fresh");//配置空间key
        DocGenerator docGenerator =
                new ConfluenceDocGenerator("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center" +
                        "/xstore-shelf-space-api");
        docGenerator.batchGenerateAndWrite(
                "com.xstore.shelf.space.api.store.StoreService", new String[]{"getAll"},
                "175925724");
    }
}

