package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.ModelInfo;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:模型（VO DTO Mode等）的递归Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class JavaDocModelInfosRecursiveParser {


    private List<ModelInfo> modelInfos;


    public JavaDocModelInfosRecursiveParser() {
        modelInfos = new ArrayList<>();
    }

    public List<ModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<ModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }

    public void parse(Type type) {
        genModelInfoList(type);
        Collections.reverse(modelInfos);
    }

    private void genModelInfoList(Type type) {
        Type realType=type.isPrimitive()?type:getRealType(type);
        //判断是不是泛型，处理泛型
        if(!realType.equals(type)){
            genModelInfoList(realType);
        }
        //正常参数的处理
         handleArgusFields(type);
         return;
    }
    private void handleArgusFields(Type realType) {
        if(realType.isPrimitive() || ParseUtils.isBaseType(realType.typeName())){
            return ;
        }
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setType(realType.toString());
        modelInfo.setActureType(realType.typeName());
        FieldDoc[] fieldDocs=realType.asClassDoc().fields(false);
        modelInfo.setFieldInfoList(buildFieldInfos(fieldDocs));
        for (FieldDoc fieldDoc:fieldDocs) {
            if(fieldDoc.type().isPrimitive() || ParseUtils.isBaseType(fieldDoc.type().typeName()) || ParseUtils.isBaseLetter(getRealType(fieldDoc.type()).typeName())){
                continue;
            }
            //处理T的那种情况
//            if(ParseUtils.isBaseLetter(realType.typeName())){
//                continue;
//            }
            genModelInfoList(fieldDoc.type());
        }
        modelInfos.add(modelInfo);
    }

    private List<FieldInfo> buildFieldInfos(FieldDoc[] fieldDocs) {
        List<FieldInfo> fieldInfos=new ArrayList<FieldInfo>();
        for(FieldDoc fieldDoc:fieldDocs){
            FieldInfo fieldInfo=new FieldInfo();
            fieldInfo.setNullAble(false);
            fieldInfo.setType(fieldDoc.type().typeName());
            fieldInfo.setName(fieldDoc.name());
            fieldInfo.setDesc(fieldDoc.commentText());
            fieldInfos.add(fieldInfo);
        }
        return  fieldInfos;
    }

    private Type getRealType(Type type){
        List<Type> ret = new ArrayList<Type>();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = type.asParameterizedType();
            Type[] typeArgs = pt.typeArguments();
            for (Type t : typeArgs) {
                ret.add(t);
            }
        }
        return ret.size()<=0?type:ret.get(0);
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
