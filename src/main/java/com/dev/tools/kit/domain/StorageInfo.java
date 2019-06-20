package com.dev.tools.kit.domain;

/**
 * Created by zhoujun5 on 2018/9/27.
 */
public class StorageInfo {
    private String value;

    private String representation;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return "StorageInfo{" +
                "value='" + value + '\'' +
                ", representation='" + representation + '\'' +
                '}';
    }
}
