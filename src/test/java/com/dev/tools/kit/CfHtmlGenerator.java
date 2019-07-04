package com.dev.tools.kit;

import com.dev.tools.kit.confluence.ConfluenceDocGenerator;
import com.dev.tools.kit.domain.DocInfo;
import org.junit.Assert;
import org.junit.Test;


public class CfHtmlGenerator {
    @Test
    public void gemHtml() {
        //配置项目内的java路径 默认为/src/main/java/
        DocGenerator docGenerator = new ConfluenceDocGenerator("D:/project/xstore-agreement-center/xstore-agreement-center-api/src/main/java/");
        DocInfo docInfo = docGenerator.generate(
                "com.jd.xstore.agreement.center.api.service.AgreementSettleService#getDistributorAgrtSettleByAgrtId");
        Assert.assertNotNull(docInfo);
        System.out.println(docInfo.getContent());
    }
}
