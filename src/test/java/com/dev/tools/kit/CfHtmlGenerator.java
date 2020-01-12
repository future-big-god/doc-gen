package com.dev.tools.kit;

import com.dev.tools.kit.confluence.ConfluenceDocGenerator;
import com.dev.tools.kit.domain.DocInfo;
import com.dev.tools.kit.parser.JavaDocMethodParser;
import com.dev.tools.kit.parser.MethodParser;
import com.sun.javadoc.Doclet;
import org.junit.Assert;
import org.junit.Test;


public class CfHtmlGenerator {
    @Test
    public void gemHtml() {
        //配置项目内的java路径 默认为/src/main/java/
        DocGenerator docGenerator = new ConfluenceDocGenerator("D:/project/xstore-agreement-center/xstore-agreement-center-api/src/main/java/");
        DocInfo docInfo = docGenerator.generate(
                "com.xx.xstore.agreement.center.api.service.AgreementSettleService#getDistributorAgrtSettleByAgrtId");
        Assert.assertNotNull(docInfo);
        System.out.println(docInfo.getContent());
    }

    @Test
    public void testJavaDoc(){
        //配置项目内的java路径 默认为/src/main/java/
        String srcRootPath="/Users/zhoujun/Documents/jdproject/commission-center/xstore-asset-commission-center-api/src/main/java/";
        MethodParser methodParser=new JavaDocMethodParser(srcRootPath);
        DocGenerator docGenerator = new ConfluenceDocGenerator(methodParser);
        DocInfo docInfo = docGenerator.generate(
                "com.xstore.asset.commission.center.api.service.CommissionManagementService#getFlowCode");
        Assert.assertNotNull(docInfo);
        System.out.println(docInfo.getContent());

    }
}
