package com.dev.tools.kit.domain;

import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class MethodInfo {

    private String interfaceName;
    private String methodName;
    private String desc;
    private List<ParamArgus> paramArgusList;
    private List<ReturnInfo> returnInfoList;

    public List<ParamArgus> getParamArgusList() {
        return paramArgusList;
    }

    public void setParamArgusList(List<ParamArgus> paramArgusList) {
        this.paramArgusList = paramArgusList;
    }

    public List<ReturnInfo> getReturnInfoList() {
        return returnInfoList;
    }

    public void setReturnInfoList(List<ReturnInfo> returnInfoList) {
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

}
