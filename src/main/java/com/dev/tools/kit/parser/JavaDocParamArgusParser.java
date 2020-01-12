package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.ModelInfo;
import com.dev.tools.kit.domain.ParamInfo;
import com.sun.javadoc.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:方法参数Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */

public class JavaDocParamArgusParser {

    private ParamInfo paramInfo;

    public JavaDocParamArgusParser() {
        paramInfo = new ParamInfo();
    }

    public ParamInfo getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(ParamInfo paramInfo) {
        this.paramInfo = paramInfo;
    }

    public void parse(Parameter[] parameters, String commentLines) {
        paramInfo.setParamArgus(genPramArgus(parameters, commentLines));
        paramInfo.setParamArgusAttres(genParamArgusAttres(parameters));
    }

    private List<ModelInfo> genParamArgusAttres(Parameter[] parameters) {
        List<ModelInfo> modelInfos = new ArrayList<>();
        for(Parameter parameter:parameters){
            if(parameter.type().isPrimitive() ||  ParseUtils.isBaseType(parameter.type().typeName())){
                continue;
            }
            JavaDocModelInfosRecursiveParser modelInfosRecursiveParser = new JavaDocModelInfosRecursiveParser();
            modelInfosRecursiveParser.parse(parameter.type());
            modelInfos.addAll(modelInfosRecursiveParser.getModelInfos());
        }
        return modelInfos;
    }

    private List<FieldInfo> genPramArgus(Parameter[] parameters, String commentLines) {
        //拿到所有的参数
        List<FieldInfo> paramFieldInfos = new ArrayList<>();
        for(Parameter parameter:parameters){
            FieldInfo paramFieldInfo = new FieldInfo();
            //todo 判断是不是非空 默认非空
            paramFieldInfo.setNullAble(false);
            paramFieldInfo.setName(parameter.name());
            paramFieldInfo.setType(parameter.typeName());
            paramFieldInfos.add(paramFieldInfo);
        }
        if (commentLines == null) {
            return paramFieldInfos;
        }
        commentLines=commentLines.replaceAll("\r\n|\r|\n", "");
        for (FieldInfo paramFieldInfo : paramFieldInfos) {
            paramFieldInfo.setDesc("无");
            String desc = ParseUtils.extractStr(commentLines, "\\s*@param\\s+" + paramFieldInfo.getName() + "\\s+(\\S*)", 1);
            if (desc != null) {
                paramFieldInfo.setDesc(desc);
            }
        }
        return paramFieldInfos;
    }

    public static void main(String[] args) {
        String  commentLines="@param user ddd";

        String desc = ParseUtils.extractStr(commentLines, "\\s*@param\\s+" + "user" + "\\s+(\\S*)", 1);
        System.out.println(desc);
    }
}
