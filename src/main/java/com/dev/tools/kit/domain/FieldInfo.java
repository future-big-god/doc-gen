package com.dev.tools.kit.domain;


/**
 * @Description:字段信息
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class FieldInfo {
    /**
     * 是否可为空
     */
    private Boolean nullAble;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String desc;

    public FieldInfo() {
        this.nullAble = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "nullAble=" + nullAble +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

}
