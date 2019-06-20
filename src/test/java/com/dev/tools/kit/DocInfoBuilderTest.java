package com.dev.tools.kit;

import com.dev.tools.kit.confluence.html.ConfluenceDocContentBuilder;
import com.dev.tools.kit.domain.DocInfo;
import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.MethodInfo;
import com.dev.tools.kit.domain.ModelInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujun5 on 2018/9/27.
 */
public class DocInfoBuilderTest {
    @Test
    public void test() {
        DocContentBuilder docContentBuilder = new ConfluenceDocContentBuilder();
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setDesc("根据Id获取商户信息");
        methodInfo.setInterfaceName("com.xstore.partner.center.api.supplierproduct.SupplierProductService");
        methodInfo.setMethodName("getvenderBaseById");
        ModelInfo returnInfo = new ModelInfo();
        returnInfo.setActureType("com.jd.venderBase");
        returnInfo.setType("VenderBase");
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setDesc("商户地址");
        fieldInfo.setName("venderAddress");
        fieldInfo.setType("String");
        fieldInfoList.add(fieldInfo);
        returnInfo.setFieldInfoList(fieldInfoList);
        //   methodInfo.setReturnInfo(returnInfo);
        List<FieldInfo> paramFieldInfos = new ArrayList<>();
        FieldInfo paramFieldInfo = new FieldInfo();
        paramFieldInfo.setNullAble(false);
        paramFieldInfo.setDesc("商户id");
        paramFieldInfo.setName("venderId");
        paramFieldInfo.setType("Long");
        paramFieldInfos.add(paramFieldInfo);
        //   methodInfo.setFieldInfoList(paramFieldInfos);
        DocInfo docInfo = docContentBuilder.build(methodInfo);
        System.out.println(docInfo);
    }
}
