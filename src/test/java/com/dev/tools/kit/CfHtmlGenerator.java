package com.dev.tools.kit;

import com.dev.tools.kit.confluence.ConfluenceDocGenerator;
import com.dev.tools.kit.domain.DocInfo;
import org.junit.Assert;
import org.junit.Test;


public class CfHtmlGenerator {
    @Test
    public void gemHtml() {
        //配置项目内的java路径 默认为/src/main/java/
        DocGenerator docGenerator = new ConfluenceDocGenerator("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center/xstore-shelf-space-api/src/main/java/");
        DocInfo docInfo = docGenerator.generate(
                "com.xstore.shelf.space.api.category.CategoryService#getCategoryGeneral");
        Assert.assertNotNull(docInfo);
        System.out.println(docInfo.getContent());
    }
}
