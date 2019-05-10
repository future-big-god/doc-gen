package com.dev.tools.kit.domain;

import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ReturnInfo {
    private String returnType;
    private String actureType;
    private List<FieldInfo> fieldInfoList;

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getActureType() {
        return actureType;
    }

    public void setActureType(String actureType) {
        this.actureType = actureType;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }
    /*public  ReturnInfo(){
        this.actureType="返回值";
    }*/
}
