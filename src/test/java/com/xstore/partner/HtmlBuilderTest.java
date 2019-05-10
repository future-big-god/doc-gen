package com.xstore.partner;

import com.dev.tools.kit.confluence.html.ConfluenceHtmlBuilder;
import com.dev.tools.kit.confluence.html.HtmlBuilder;
import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.MethodInfo;
import com.dev.tools.kit.domain.ParamInfo;
import com.dev.tools.kit.domain.ReturnInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujun5 on 2018/9/27.
 */
public class HtmlBuilderTest {
    @Test
    public void test(){
        HtmlBuilder htmlBuilder=new ConfluenceHtmlBuilder();
        MethodInfo methodInfo=new MethodInfo();
        methodInfo.setDesc("根据Id获取商户信息");
        methodInfo.setInterfaceName("com.xstore.partner.center.api.supplierproduct.SupplierProductService");
        methodInfo.setMethodName("getvenderBaseById");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setActureType("com.jd.venderBase");
        returnInfo.setReturnType("VenderBase");
        List<FieldInfo> fieldInfoList=new ArrayList<>();
        FieldInfo fieldInfo=new FieldInfo();
        fieldInfo.setDesc("商户地址");
        fieldInfo.setName("venderAddress");
        fieldInfo.setType("String");
        fieldInfoList.add(fieldInfo);
        returnInfo.setFieldInfoList(fieldInfoList);
     //   methodInfo.setReturnInfo(returnInfo);
        List<ParamInfo> paramInfos=new ArrayList<>();
        ParamInfo paramInfo=new ParamInfo();
        paramInfo.setNullAble(false);
        paramInfo.setDesc("商户id");
        paramInfo.setName("venderId");
        paramInfo.setType("Long");
        paramInfos.add(paramInfo);
     //   methodInfo.setParamInfoList(paramInfos);
        String html=htmlBuilder.buildHtml(methodInfo);
        System.out.println(html);
    }
}
