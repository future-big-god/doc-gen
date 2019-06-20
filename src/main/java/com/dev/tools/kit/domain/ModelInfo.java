package com.dev.tools.kit.domain;


import com.dev.tools.kit.parser.ImportLinesParser;
import com.dev.tools.kit.parser.ParseUtils;
import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:模型信息
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ModelInfo {
    /**
     * 类型
     */
    private String type;
    /**
     * 实际类型 比如去除泛型后的类型
     */
    private String actureType;
    /**
     * 字段列表
     */
    private List<FieldInfo> fieldInfoList;

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

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    /**
     * 根据actureType生成fieldInfoList
     *
     * @param srcRootPath
     */
    public void parseFieldInfos(String srcRootPath) {
        this.fieldInfoList = genFieldInfos(this.actureType, srcRootPath);
    }

    private List<FieldInfo> genFieldInfos(String actureType, String srcRootPath) {
        if (actureType == null) {
            return new ArrayList<>();
        }
        SourceReader sourceReader = new LocalProjectSourceReader(srcRootPath);
        String sourceContent = sourceReader.readContent(actureType);
        String content = sourceContent.replaceAll("[/][*][*][\\s\\S]*?public\\s+class", "");
        Matcher matcher = Pattern.compile("([/][*][*][\\s\\S]*?[*][/])([\\s\\S]*?[;])").matcher(content);
        List<FieldInfo> fieldInfos = new ArrayList();
        while (matcher.find()) {
            String commentLine = matcher.group(1);
            String fieldLine = matcher.group(2);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setDesc(ParseUtils.genDesc(Arrays.asList(commentLine.split("\n"))));
            fieldInfo.setName(ParseUtils.extractStr(fieldLine, "\\s+?(\\w+)[;]", 1));
            fieldInfo.setType(ParseUtils.extractStr(fieldLine.trim(), "(private)?\\s*(.+?)\\s", 2));
            ImportLinesParser importLinesParser = new ImportLinesParser();
            importLinesParser.parse(sourceContent);
            fieldInfos.add(fieldInfo);
        }
        return fieldInfos;
    }

    @Override
    public String toString() {
        return "ModelInfo{" +
                "type='" + type + '\'' +
                ", actureType='" + actureType + '\'' +
                ", fieldInfoList=" + fieldInfoList +
                '}';
    }
}
