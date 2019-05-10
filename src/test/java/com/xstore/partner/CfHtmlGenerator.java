package com.xstore.partner;

import com.xstore.partner.doc.DocGenerator;
import com.xstore.partner.doc.cf.CFDocGenerator;
import org.junit.Test;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2019-04-23
 */

public class CfHtmlGenerator {
    @Test
    public void gemHtml(){
        //配置项目内的java路径 默认为/src/main/java/
        System.setProperty("project.java.path","/src/main/java/");
        DocGenerator docGenerator = new CFDocGenerator("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center/xstore-shelf-space-api");
        docGenerator.write2Console(
                "com.xstore.shelf.space.api.category.CategoryService#getCategoryGeneral");
    }
}