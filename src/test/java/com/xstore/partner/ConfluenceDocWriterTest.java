package com.xstore.partner;

import com.dev.tools.kit.confluence.ConfluenceDocWriter;
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
public class ConfluenceDocWriterTest {
    @Test
    public void test(){
        System.setProperty("confluence.username","zhoujun5");
        System.setProperty("confluence.password","!Sandbar731208");
        MethodInfo methodInfo=new MethodInfo();
        methodInfo.setDesc("根据Id获取商户信息3");
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
      //  methodInfo.setReturnInfo(returnInfo);
        List<ParamInfo> paramInfos=new ArrayList<>();
        ParamInfo paramInfo=new ParamInfo();
        paramInfo.setNullAble(false);
        paramInfo.setDesc("商户id");
        paramInfo.setName("venderId");
        paramInfo.setType("Long");
        paramInfos.add(paramInfo);
    //    methodInfo.setParamInfoList(paramInfos);
        ConfluenceDocWriter confluenceDocWriter =new ConfluenceDocWriter();
        confluenceDocWriter.write2Cf("138331784",methodInfo);
    }
}

