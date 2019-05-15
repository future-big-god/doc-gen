package com.dev.tools.kit.confluence.html;

import com.dev.tools.kit.DocContentBuilder;
import com.dev.tools.kit.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class ConfluenceDocContentBuilder implements DocContentBuilder {

    private H2Builder h2Builder;
    private PBuilder pBuilder;
    private TableBuilder tableBuilder;

    public ConfluenceDocContentBuilder() {
        this.h2Builder = new H2Builder();
        this.pBuilder = new PBuilder();
        this.tableBuilder = new TableBuilder();
    }

    @Override
    public DocInfo build(MethodInfo methodInfo) {


        StringBuilder html = new StringBuilder();
        html.append(h2Builder.build("接口"))
                .append(pBuilder.build(methodInfo.getInterfaceName()))
                .append(h2Builder.build("方法"))
                .append(pBuilder.build(methodInfo.getMethodName()))
                .append(h2Builder.build("参数"));
        //参数构建
        html.append(h2Builder.build("参数"));
        html.append(tableBuilder.build(genTableInfo(methodInfo.getParamInfo().getParamArgus())));
        //参数属性构建
        for (ModelInfo modelInfo : methodInfo.getParamInfo().getParamArgusAttres()) {
            html.append(pBuilder.build(modelInfo.getType()))
                    .append(tableBuilder.build(genTableInfo(modelInfo.getFieldInfoList())));
        }
        html.append(h2Builder.build("返回值"));
        for (ModelInfo returnInfo : methodInfo.getReturnInfoList()) {
            html.append(pBuilder.build(returnInfo.getType()))
                    .append(tableBuilder.build(genTableInfo(returnInfo)));
        }
        DocInfo docInfo = new DocInfo();
        docInfo.setTitle(methodInfo.getDesc());
        docInfo.setContent(html.toString());
        return docInfo;
    }

    private TableInfo genTableInfo(ModelInfo returnInfo) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setHeader(returnInfo.getActureType());
        if (returnInfo.getFieldInfoList() == null || returnInfo.getFieldInfoList().size() == 0) {
            return tableInfo;
        }
        TableRow titleRow = new TableRow();
        List<String> strings = new ArrayList<>();
        strings.add("字段名称");
        strings.add("类型");
        strings.add("备注");
        titleRow.setValues(strings);
        tableInfo.setTitleRow(titleRow);
        tableInfo.setContentRows(convertFieldInfoToRowList(returnInfo.getFieldInfoList()));
        return tableInfo;
    }

    private TableInfo genTableInfo(List<FieldInfo> paramFieldInfoList) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setHeader("参数");
        TableRow titleRow = new TableRow();
        List<String> strings = new ArrayList<>();
        strings.add("参数名称");
        strings.add("类型");
        strings.add("是否必填");
        strings.add("备注");
        titleRow.setValues(strings);
        tableInfo.setTitleRow(titleRow);
        tableInfo.setContentRows(convertParamInfoToRowList(paramFieldInfoList));
        return tableInfo;
    }

    private TableRow convertFieldInfoToTableRow(FieldInfo fieldInfo) {
        TableRow tableRow = new TableRow();
        List<String> values = new ArrayList<>();
        values.add(fieldInfo.getName());
        values.add(fieldInfo.getType());
        values.add(fieldInfo.getDesc());
        tableRow.setValues(values);
        return tableRow;
    }

    private List<TableRow> convertFieldInfoToRowList(List<FieldInfo> fieldInfos) {
        List<TableRow> tableRows = new ArrayList<>();
        if (fieldInfos == null) {
            return tableRows;
        }
        for (FieldInfo fieldInfo : fieldInfos) {
            TableRow tableRow = convertFieldInfoToTableRow(fieldInfo);
            tableRows.add(tableRow);
        }
        return tableRows;
    }

    private TableRow convertParamInfoToTableRow(FieldInfo paramFieldInfo) {
        TableRow tableRow = new TableRow();
        List<String> values = new ArrayList<>();
        values.add(paramFieldInfo.getName());
        values.add(paramFieldInfo.getType());
        values.add(Boolean.TRUE.equals(paramFieldInfo.getNullAble()) ? "否" : "是");
        values.add(paramFieldInfo.getDesc());
        tableRow.setValues(values);
        tableRow.setValues(values);
        return tableRow;
    }

    private List<TableRow> convertParamInfoToRowList(List<FieldInfo> paramFieldInfos) {
        List<TableRow> tableRows = new ArrayList<>();
        for (FieldInfo paramFieldInfo : paramFieldInfos) {
            TableRow tableRow = convertParamInfoToTableRow(paramFieldInfo);
            tableRows.add(tableRow);
        }
        return tableRows;
    }
}
