package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.ModelInfo;
import com.sun.javadoc.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:返回值Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-23
 */
public class JavaDocReturnInfoParser {
    private List<ModelInfo> returnModels=new ArrayList<>();

    public void parse(Type returnType) {
          if(returnType.isPrimitive()  || ParseUtils.isBaseType(returnType.typeName())){
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.setType(returnType.typeName());
            this.getReturnModels().add(modelInfo);
        } else {
            JavaDocModelInfosRecursiveParser modelInfosRecursiveParser = new JavaDocModelInfosRecursiveParser();
            modelInfosRecursiveParser.parse(returnType);
            this.returnModels = modelInfosRecursiveParser.getModelInfos();
        }
    }

    public List<ModelInfo> getReturnModels() {
        return returnModels;
    }

    /**
     * 从方法行中提取返回值
     *
     * @param methodLine
     * @return
     */
    private String getReturnType(String methodLine) {
     //   ParseUtils.extractStr(methodLine, "\\s*(.+)\\s.+[(]", 1);
        return ParseUtils.extractStr(methodLine, " (\\S+)\\s+(\\S+)[(]", 1);
    }

}
