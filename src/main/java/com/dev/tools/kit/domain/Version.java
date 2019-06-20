package com.dev.tools.kit.domain;

/**
 * Created by zhoujun5 on 2018/10/19.
 */
public class Version {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Version{" +
                "number=" + number +
                '}';
    }
}
