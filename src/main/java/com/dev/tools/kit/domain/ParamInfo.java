package com.dev.tools.kit.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujun5
 * @date 2019/5/20
 */
public class ParamInfo {
    /**
     * 参数列表
     */
    List<FieldInfo> paramArgus;

    /**
     * 参数属性列表
     */
    List<ModelInfo> paramArgusAttres;

    public ParamInfo() {
        paramArgus = new ArrayList<>();
        paramArgusAttres = new ArrayList<>();
    }

    public List<FieldInfo> getParamArgus() {
        return paramArgus;
    }

    public void setParamArgus(List<FieldInfo> paramArgus) {
        this.paramArgus = paramArgus;
    }

    public List<ModelInfo> getParamArgusAttres() {
        return paramArgusAttres;
    }

    public void setParamArgusAttres(List<ModelInfo> paramArgusAttres) {
        this.paramArgusAttres = paramArgusAttres;
    }

    @Override
    public String toString() {
        return "ParamInfo{" +
                "paramArgus=" + paramArgus +
                ", paramArgusAttres=" + paramArgusAttres +
                '}';
    }
}
