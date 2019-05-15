package com.dev.tools.kit.domain;

import java.util.List;

/**
 * @Description:方法信息
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class MethodInfo {

    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 方法描述
     */
    private String desc;

    /**
     * 参数信息
     */
    private ParamInfo paramInfo;
    /**
     * 返回值列表
     */
    private List<ModelInfo> returnInfoList;

    public ParamInfo getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(ParamInfo paramInfo) {
        this.paramInfo = paramInfo;
    }

    public List<ModelInfo> getReturnInfoList() {
        return returnInfoList;
    }

    public void setReturnInfoList(List<ModelInfo> returnInfoList) {
        this.returnInfoList = returnInfoList;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", desc='" + desc + '\'' +
                ", paramInfo=" + paramInfo +
                ", returnInfoList=" + returnInfoList +
                '}';
    }
}
