package com.dev.tools.kit.domain;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ParamInfo extends FieldInfo {
    /**
     * 是否可为空
     */
    private Boolean nullAble;

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public ParamInfo(){
        this.nullAble=true;
    }
}
