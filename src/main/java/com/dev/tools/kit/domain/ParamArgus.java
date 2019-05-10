package com.dev.tools.kit.domain;

import java.util.List;

/**
 * Created by zhoujun5 on 2018/10/16.
 */
public class ParamArgus {
    /**
     * 类型
     */
    private String type;
    /**
     * 实际类型
     */
    private String actureType;

    /**
     * 参数list
     */
    private List<ParamInfo> paramInfoList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActureType() {
        return actureType;
    }

    public void setActureType(String actureType) {
        this.actureType = actureType;
    }

    public List<ParamInfo> getParamInfoList() {
        return paramInfoList;
    }

    public void setParamInfoList(List<ParamInfo> paramInfoList) {
        this.paramInfoList = paramInfoList;
    }
}
