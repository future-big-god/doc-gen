package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.ModelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:返回值Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-23
 */
public class ReturnInfoParser {
    private List<ModelInfo> returnModels=new ArrayList<>();

    public void parse(String interfaceName, String methodLine, List<String> importLines, String srcRootPath) {
        String returnType = getReturnType(methodLine);
        if (returnType == null || "void".equals(returnType) || ParseUtils.isBaseType(returnType) || ParseUtils.isBaseType(ParseUtils.genRealTypeShort(returnType))) {
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.setType(returnType);
            this.getReturnModels().add(modelInfo);
        } else {
            ModelInfosRecursiveParser modelInfosRecursiveParser = new ModelInfosRecursiveParser();
            modelInfosRecursiveParser.parse(returnType, interfaceName, importLines, srcRootPath);
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
