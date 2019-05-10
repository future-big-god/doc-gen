package com.dev.tools.kit.domain;

import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class TableInfo {
    private String header;
    private TableRow titleRow;
    private List<TableRow> contentRows;
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public TableRow getTitleRow() {
        return titleRow;
    }

    public void setTitleRow(TableRow titleRow) {
        this.titleRow = titleRow;
    }

    public List<TableRow> getContentRows() {
        return contentRows;
    }

    public void setContentRows(List<TableRow> contentRows) {
        this.contentRows = contentRows;
    }

}
