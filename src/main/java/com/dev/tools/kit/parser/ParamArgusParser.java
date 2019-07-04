package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.ModelInfo;
import com.dev.tools.kit.domain.ParamInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:方法参数Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */

public class ParamArgusParser {

    private ParamInfo paramInfo;

    public ParamArgusParser() {
        paramInfo = new ParamInfo();
    }

    public ParamInfo getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(ParamInfo paramInfo) {
        this.paramInfo = paramInfo;
    }

    public void parse(String interfaceName, String methodLine, List<String> commentLines, List<String> importLines, String srcRootPath) {
        paramInfo.setParamArgus(genPramArgus(methodLine, commentLines));
        paramInfo.setParamArgusAttres(genParamArgusAttres(interfaceName, importLines, srcRootPath));
    }

    private List<ModelInfo> genParamArgusAttres(String interfaceName, List<String> importLines, String srcRootPath) {
        List<ModelInfo> modelInfos = new ArrayList<>();
        for (FieldInfo fieldInfo : paramInfo.getParamArgus()) {
            //跳过基础类型
            if (ParseUtils.isBaseType(ParseUtils.genRealTypeShort(fieldInfo.getType()))) {
                continue;
            }
            ModelInfosRecursiveParser modelInfosRecursiveParser = new ModelInfosRecursiveParser();
            modelInfosRecursiveParser.parse(fieldInfo.getType(), interfaceName, importLines, srcRootPath);
            modelInfos.addAll(modelInfosRecursiveParser.getModelInfos());
        }
        return modelInfos;
    }

    private List<FieldInfo> genPramArgus(String methodLine, List<String> commentLines) {
        String arguString = ParseUtils.extractStr(methodLine, ".+[(](.+)[)]", 1);
        if (arguString == null) {
            return new ArrayList<>();
        }
        String[] argus = arguString.split(",\\s+|,|\\s+,");
        if (argus == null || argus.length <= 0) {
            return new ArrayList<>();
        }
        List<FieldInfo> paramFieldInfos = new ArrayList<>();
        for (String s : argus) {
            FieldInfo paramFieldInfo = new FieldInfo();
            String[] params = s.split("\\s+");
            for (int i = 0; i < params.length; i++) {
                if (params[i].contains("@NonNull") || params[i].contains("@NotNull")) {
                    paramFieldInfo.setNullAble(false);
                    paramFieldInfo.setType(params[i + 1]);
                    paramFieldInfo.setName(params[i + 2]);
                    break;
                }
                if (!params[i].contains("@")) {
                    paramFieldInfo.setNullAble(true);
                    paramFieldInfo.setType(params[i]);
                    paramFieldInfo.setName(params[i + 1]);
                    break;
                }
            }
            paramFieldInfos.add(paramFieldInfo);
        }
        if (commentLines == null || commentLines.size() <= 0) {

            return paramFieldInfos;
        }
        String commentLineString = convertList2String(commentLines);
        for (FieldInfo paramFieldInfo : paramFieldInfos) {
            paramFieldInfo.setDesc("无");
            String desc = ParseUtils.extractStr(commentLineString, "[*]\\s*@param\\s+" + paramFieldInfo.getName() + "\\s+(.+?)\\s", 1);
            if (desc != null) {
                paramFieldInfo.setDesc(desc);
            }
        }
        return paramFieldInfos;
    }
    private String convertList2String(List<String> strings){
        if(strings==null || strings.size()<=0){
            return "";
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(String s:strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
