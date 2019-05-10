package com.dev.tools.kit.confluence.html;

import com.dev.tools.kit.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class ConfluenceHtmlBuilder implements HtmlBuilder {

    private H2Builder h2Builder;
    private PBuilder pBuilder;
    private TableBuilder tableBuilder;

    public ConfluenceHtmlBuilder() {
        this.h2Builder = new H2Builder();
        this.pBuilder = new PBuilder();
        this.tableBuilder = new TableBuilder();
    }

    @Override
    public String buildHtml(MethodInfo methodInfo) {

        StringBuilder html = new StringBuilder();
        html.append(h2Builder.build("接口"))
            .append(pBuilder.build(methodInfo.getInterfaceName()))
            .append(h2Builder.build("方法"))
            .append(pBuilder.build(methodInfo.getMethodName()))
            .append(h2Builder.build("参数"));
        for(ParamArgus paramArgus:methodInfo.getParamArgusList()){
            html.append(pBuilder.build(paramArgus.getType()))
                .append(tableBuilder.build(genTableInfo(paramArgus.getParamInfoList())));
        }
        html.append(h2Builder.build("返回值"));
        for(ReturnInfo returnInfo:methodInfo.getReturnInfoList()) {
            html.append(pBuilder.build(returnInfo.getReturnType()))
                .append(tableBuilder.build(genTableInfo(returnInfo)));
        }
        return html.toString();
    }

    private TableInfo genTableInfo(ReturnInfo returnInfo) {
        TableInfo tableInfo=new TableInfo();
        tableInfo.setHeader(returnInfo.getActureType());
        if(returnInfo.getFieldInfoList() == null || returnInfo.getFieldInfoList().size() == 0)
        {
            return tableInfo;
        }
        TableRow titleRow=new TableRow();
        List<String> strings=new ArrayList<>();
        strings.add("字段名称");
        strings.add("类型");
        strings.add("备注");
        titleRow.setValues(strings);
        tableInfo.setTitleRow(titleRow);
        tableInfo.setContentRows(convertFieldInfoToRowList(returnInfo.getFieldInfoList()));
        return tableInfo;
    }

    private TableInfo genTableInfo(List<ParamInfo> paramInfoList) {
        TableInfo tableInfo=new TableInfo();
        tableInfo.setHeader("参数");
        TableRow titleRow=new TableRow();
        List<String> strings=new ArrayList<>();
        strings.add("参数名称");
        strings.add("类型");
        strings.add("是否必填");
        strings.add("备注");
        titleRow.setValues(strings);
        tableInfo.setTitleRow(titleRow);
        tableInfo.setContentRows(convertParamInfoToRowList(paramInfoList));
        return tableInfo;
    }

    private TableRow convertFieldInfoToTableRow(FieldInfo fieldInfo){
        TableRow tableRow=new TableRow();
        List<String> values=new ArrayList<>();
        values.add(fieldInfo.getName());
        values.add(fieldInfo.getType());
        values.add(fieldInfo.getDesc());
        tableRow.setValues(values);
        return tableRow;
    }
    private List<TableRow> convertFieldInfoToRowList(List<FieldInfo> fieldInfos){
        List<TableRow> tableRows=new ArrayList<>();
        if (fieldInfos == null)
        {
            return tableRows;
        }
        for(FieldInfo fieldInfo:fieldInfos){
            TableRow tableRow=convertFieldInfoToTableRow(fieldInfo);
            tableRows.add(tableRow);
        }
        return tableRows;
    }

    private TableRow convertParamInfoToTableRow(ParamInfo paramInfo){
        TableRow tableRow=new TableRow();
        List<String> values=new ArrayList<>();
        values.add(paramInfo.getName());
        values.add(paramInfo.getType());
        values.add(Boolean.TRUE.equals(paramInfo.getNullAble())?"否":"是");
        values.add(paramInfo.getDesc());
        tableRow.setValues(values);
        tableRow.setValues(values);
        return tableRow;
    }
    private List<TableRow> convertParamInfoToRowList(List<ParamInfo> paramInfos){
        List<TableRow> tableRows=new ArrayList<>();
        for(ParamInfo paramInfo:paramInfos){
            TableRow tableRow=convertParamInfoToTableRow(paramInfo);
            tableRows.add(tableRow);
        }
        return tableRows;
    }
}
