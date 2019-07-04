package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.ModelInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:模型（VO DTO Mode等）的递归Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class ModelInfosRecursiveParser {


    private List<ModelInfo> modelInfos;


    public ModelInfosRecursiveParser() {
        modelInfos = new ArrayList<>();
    }

    public List<ModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<ModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }

    public void parse(String type, String interfaceName, List<String> importLines, String srcRootPath) {
        genModelInfoList(type, interfaceName, importLines, srcRootPath);
        Collections.reverse(modelInfos);
    }

    private void genModelInfoList(String type, String className, List<String> importLines, String srcRootPath) {
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setType(type);
        modelInfo.setActureType(ParseUtils.isBaseType(ParseUtils.genRealTypeShort(type))?ParseUtils.genRealTypeShort(type):ParseUtils.genFullType(ParseUtils.genRealTypeShort(type), className, importLines));
        if(ParseUtils.isBaseType(modelInfo.getActureType())){
            return;
        }
        modelInfo.parseFieldInfos(srcRootPath);
        for (FieldInfo fieldInfo : modelInfo.getFieldInfoList()) {
                if (!ParseUtils.isBaseType(fieldInfo.getType()) && !isParentType(modelInfo.getActureType(), fieldInfo.getType())) {
                    genModelInfoList(fieldInfo.getType(), modelInfo.getActureType(), importLines, srcRootPath);
                }
            }
        modelInfos.add(modelInfo);
    }

    /**
     * 是否父类型 如Data类中包含一个List<Data> 避免死循环
     *
     * @param className
     * @param type
     * @return
     */
    private boolean isParentType(String className, String type) {
        className = className.substring(className.lastIndexOf(".") + 1);
        return className.equals(type) || type.contains("<" + className + ">");
    }
}
