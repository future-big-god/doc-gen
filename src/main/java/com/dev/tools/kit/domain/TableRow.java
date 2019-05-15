package com.dev.tools.kit.domain;


import java.util.List;

/**
 * Created by zhoujun5 on 2018/9/26.
 */
public class TableRow {
    List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "values=" + values +
                '}';
    }
}
