package com.xstore.partner;

import com.xstore.partner.doc.cf.CFDocWriter;
import com.xstore.partner.doc.domain.FieldInfo;
import com.xstore.partner.doc.domain.MethodInfo;
import com.xstore.partner.doc.domain.ParamInfo;
import com.xstore.partner.doc.domain.ReturnInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujun5 on 2018/9/27.
 */
public class CFDocWriterTest {
    @Test
    public void test(){
        System.setProperty("cf.username","zhoujun5");
        System.setProperty("cf.password","!Sandbar731208");
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
        CFDocWriter cfDocWriter=new CFDocWriter();
        cfDocWriter.write2Cf("138331784",methodInfo);
    }
}

