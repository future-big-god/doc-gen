package com.xstore.partner;


import com.dev.tools.kit.DocGenerator;
import com.dev.tools.kit.confluence.ConfluenceDocGenerator;
import org.junit.Test;

public class AppTest
{
    @Test
    public void test()
    {
        //System.setProperty("confluence.username","zhangjianfeng5");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        //System.setProperty("confluence.password","Zjf@@876240");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        System.setProperty("confluence.username","zhoujun5");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        System.setProperty("confluence.password","!Sandbar731202");//设置cf的账号密码 也可以配置在系统环境变量 或者jvm启动参数中
        System.setProperty("project.java.path","/src/main/java/");//配置项目内的java路径 默认为/src/main/java/
        DocGenerator docGenerator =
                new ConfluenceDocGenerator("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center" +
                        "/xstore-shelf-space-api");
        docGenerator.batchGenerate(
                "com.xstore.shelf.space.api.store.StoreService",new String[]{"getAll"},
                "175925724");
    }
}

