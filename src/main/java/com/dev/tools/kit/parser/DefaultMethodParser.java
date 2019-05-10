package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.*;
import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:解析器 需要重构
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class DefaultMethodParser implements MethodParser {

    private SourceReader sourceReader;

    public DefaultMethodParser(String projectRootPath) {
        this.sourceReader = new LocalProjectSourceReader(projectRootPath);
    }

    @Override
    public MethodInfo parse(String methodId) {
        String interfaceName = methodId.split("#")[0];
        String methodName = methodId.split("#")[1];
        String sourceContent = this.sourceReader.readContent(interfaceName);
        MethodLines methodLines = getMethodLines(sourceContent, methodName);
        if (methodLines == null) {
            throw new RuntimeException("can`t extract method in interface,please check the method format");
        }
        return extractMethodInfo(interfaceName, methodName, methodLines);
    }


    private MethodInfo extractMethodInfo(String interfaceName, String methodName, MethodLines methodLines) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setInterfaceName(interfaceName);
        methodInfo.setMethodName(methodName);
        methodInfo.setDesc(genDesc(methodLines.getCommentLines()));
        List<ParamArgus> paramArgusList=initParamArgusList(methodLines.getMethodLine(),methodLines.getCommentLines());
        List<ParamArgus> paramArguses=new ArrayList<>();
            for(ParamInfo paramInfo:paramArgusList.get(0).getParamInfoList()){
                if(!isBaseType(paramInfo.getType(),methodLines.getImportLines())){
                    genParamArgus(paramInfo.getType(),methodLines.getImportLines(),paramArgusList);
                }

            }
        paramArguses.add(paramArgusList.get(0));
        paramArgusList.remove(0);
        Collections.reverse(paramArgusList);
        paramArguses.addAll(paramArgusList);
        methodInfo.setParamArgusList(paramArguses);
        String returnType = extractStr(methodLines.getMethodLine(), "\\s*(.+?)\\s", 1);
        List<ReturnInfo> returnInfos=new ArrayList<>();
        genReturnInfoList(returnType, interfaceName, methodLines.getImportLines(),returnInfos);
        Collections.reverse(returnInfos);
        methodInfo.setReturnInfoList(returnInfos);
        return methodInfo;
    }
    private List<ParamArgus> initParamArgusList(String methodLine,List<String> commentLines){
        List<ParamArgus> paramArguses=new ArrayList<>();
        ParamArgus paramArgus=new ParamArgus();
        paramArgus.setType("");
        paramArgus.setActureType("");
        String arguString=extractStr(methodLine,".+[(](.+)[)]",1);
        if(arguString==null){
            paramArgus.setType("无");
            paramArgus.setParamInfoList(new ArrayList<ParamInfo>());
            paramArguses.add(paramArgus);
            return paramArguses;
        }
        String[] argus=arguString.split(",\\s+|,|\\s+,");
        if(argus==null || argus.length<=0){
            return new ArrayList<>();
        }
        List<ParamInfo> paramInfos=new ArrayList<>();
        for(String  s:argus){
            ParamInfo paramInfo=new ParamInfo();
            String[] params=s.split("\\s+");
            for (int i=0;i<params.length;i++){
                if(params[i].contains("@NonNull")|| params[i].contains("@NotNull")){
                    paramInfo.setNullAble(false);
                    paramInfo.setType(params[i+1]);
                    paramInfo.setName(params[i+2]);
                    break;
                }
                if(!params[i].contains("@")){
                    paramInfo.setNullAble(true);
                    paramInfo.setType(params[i]);
                    paramInfo.setName(params[i+1]);
                    break;
                }
            }
            paramInfos.add(paramInfo);
        }
        if(commentLines==null || commentLines.size()<=0){
            paramArgus.setParamInfoList(paramInfos);
            paramArguses.add(paramArgus);
            return paramArguses;
        }
        String commentLineString =commentLines.toString();
        for(ParamInfo paramInfo:paramInfos){
            paramInfo.setDesc("无");
            String desc = extractStr(commentLineString, "[*]\\s*@param\\s+" + paramInfo.getName() + "\\s+(.+?)\\s", 1);
            if(desc!=null){
                paramInfo.setDesc(desc);
            }
        }
        paramArgus.setParamInfoList(paramInfos);
        paramArguses.add(paramArgus);
        return paramArguses;
    }
    private void genReturnInfoList(String returnType, String className, List<String> importLines, List<ReturnInfo> returnInfos){
        if (returnType == null || "void".equals(returnType)) {
            return ;
        }
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setReturnType(returnType);
        if (isBaseType(returnType,importLines))
        {
            returnInfos.add(returnInfo);
            return ;
        }
        String realTypeShort = genRealTypeShort(returnType);
        for (String importStr : importLines) {
            if (importStr.trim().endsWith("." + realTypeShort + ";")) {
                returnInfo.setActureType(extractStr(importStr,"\\s*import\\s*(.+?)[;]",1));
                break;
            }
        }
        //如果在import中找不到 那么证明是在同一个包下
        if(returnInfo.getActureType() == null)
        {
            returnInfo.setActureType(className.substring(0,className.lastIndexOf('.')) + "." + realTypeShort);
        }
        returnInfo.setFieldInfoList(genFieldInfos(returnInfo.getActureType(),returnInfos));
        returnInfos.add(returnInfo);
    }

    /**
     * 生成实际类型（去除泛型）
     *  DataDto 将返回DataDto
     *  List<DataDto>  返回DataDto
     *  Map<String,DataDto>  返回DataDto
     * @param returnType
     * @return
     */
    private String genRealTypeShort(String returnType) {
        if (!returnType.contains("<"))
        {
            return returnType;
        }
        String tempStr = extractStr(returnType, "<(.+)>", 1);
        if (tempStr.contains(","))
        {
            return tempStr.split("[,]")[1];
        }
        return tempStr;
    }

    private void genParamArgus(String type, List<String> importLines,List<ParamArgus> arguses) {
        if (type == null) {
            return ;
        }
        ParamArgus paramArgus = new ParamArgus();
        paramArgus.setType(type);
        String realTypeShort = genRealTypeShort(type);
        for (String importStr : importLines) {
            if (importStr.trim().endsWith("." + realTypeShort + ";")) {
                paramArgus.setActureType(extractStr(importStr,"\\s*import\\s*(.+?)[;]",1));
                break;
            }
        }
        paramArgus.setParamInfoList(genParamInfos(paramArgus.getActureType(),importLines,arguses));
        arguses.add(paramArgus);
        return;
    }
    private boolean  isBaseType(String type,List<String> importLines){
        //基础类型
        if(type.equals("int") || type.equalsIgnoreCase("float") || type.equalsIgnoreCase("double")
                || type.equalsIgnoreCase("long") || type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("char")
                || type.equalsIgnoreCase("byte")|| type.equalsIgnoreCase("short") || type.equals("BigDecimal")
                || type.equals("Integer") || type.equals("String") || type.equals("Date") )
        {
            return true;
        }
        String realTypeShort = genRealTypeShort(type);
        String className=null;
        for (String importStr : importLines) {
            if (importStr.trim().endsWith("." + realTypeShort + ";")) {
                className=extractStr(importStr,"\\s*import\\s*(.+?)[;]",1);
                break;
            }
        }
        //找不到import 代表是同一个包下的类 非基础类型
        if(className==null){
            return false;
        }
        //其他基础类型 java.包下的
        if(className == null || className.startsWith("java.")){
            return true;
        }
        return false;
    }
    private List<FieldInfo> genFieldInfos(String className, List<ReturnInfo> returnInfos) {
        //todo 无法拿到继承的字段
        if(className == null || className.startsWith("java."))
        {
            return new ArrayList<>();
        }
        String sourceContent =  this.sourceReader.readContent(className);
        String content = sourceContent.replaceAll("[/][*][*][\\s\\S]*?public\\s+class", "");
        Matcher matcher = Pattern.compile("([/][*][*][\\s\\S]*?[*][/])([\\s\\S]*?[;])").matcher(content);
        List<FieldInfo> fieldInfos = new ArrayList();
        while (matcher.find()) {
            String commentLine = matcher.group(1);
            String fieldLine = matcher.group(2);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setDesc(genDesc(Arrays.asList(commentLine.split("\n"))));
            //TODO ZJF 需支持对象内部泛型
            fieldInfo.setName(this.extractStr(fieldLine,"\\s+?(\\w+)[;]",1));
            fieldInfo.setType(this.extractStr(fieldLine.trim(),"(private)?\\s*(.+?)\\s",2));
            List<String> importLines = this.genImportLines(sourceContent);
            if(!isBaseType(fieldInfo.getType(),importLines) && !isParentType(className,fieldInfo.getType())){
                genReturnInfoList(fieldInfo.getType(),className,importLines,returnInfos);
            }
            fieldInfos.add(fieldInfo);
        }
        return fieldInfos;
    }

    /**
     * 是否父类型 如Data类中包含一个List<Data> 避免死循环
     * @param className
     * @param type
     * @return
     */
    private boolean isParentType(String className, String type) {
        className = className.substring(className.lastIndexOf(".") + 1);
        return  className.equals(type) || type.contains("<" + className + ">");
    }

    private List<ParamInfo> genParamInfos(String className,List<String> importLines,List<ParamArgus> arguses) {
        if(className == null || className.startsWith("java."))
        {
            return new ArrayList<>();
        }
        String sourceContent =  this.sourceReader.readContent(className);
        String content = sourceContent.replaceAll("[/][*][*][\\s\\S]*?public\\s+class", "");
        Matcher matcher = Pattern.compile("([/][*][*][\\s\\S]*?[*][/])([\\s\\S]*?[;])").matcher(content);
        List<ParamInfo> paramInfos = new ArrayList();
        while (matcher.find()) {
            String commentLine = matcher.group(1);
            String fieldLine = matcher.group(2);
            ParamInfo paramInfo = new ParamInfo();
            paramInfo.setDesc(genDesc(Arrays.asList(commentLine.split("\n"))));
            paramInfo.setName(this.extractStr(fieldLine,"\\s+?(\\w+)[;]",1));
            paramInfo.setType(this.extractStr(fieldLine,"private\\s*(.+?)\\s",1));
            if(!isBaseType(paramInfo.getType(),importLines)){
                genParamArgus(paramInfo.getType(),importLines,arguses);
            }
                paramInfos.add(paramInfo);
        }
        return paramInfos;
    }
    private String genDesc(List<String> commentLines) {
        if (commentLines == null || commentLines.size() < 2) {
            return null;
        }
        return commentLines.get(1).replaceAll("\\s*[*]\\s*", "");
    }
    //todo 是所有的methodLins 然后从里面找method？
    private MethodLines getMethodLines(String sourceContent, String methodName) {
        String content = sourceContent.replaceAll("[/][*][*][\\s\\S]*?public\\s+interface", "");
        Matcher matcher = Pattern.compile("([/][*][*][\\s\\S]*?[*][/])([\\s\\S]*?[;])").matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(2).matches("[\\s\\S]*?" + methodName + "[(][\\s\\S]*?[;]")) {
                    MethodLines methodLines = new MethodLines();
                    methodLines.setCommentLines(Arrays.asList(matcher.group(1).split("\n")));
                    methodLines.setMethodLine(matcher.group(2));
                    methodLines.setImportLines(genImportLines(content));
                    return methodLines;
                }
            }

        }
        return null;
    }

    private List<String> genImportLines(String sourceContent) {
        Matcher matcher = Pattern.compile("import\\s+.+?[;]").matcher(sourceContent);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group(0));
        }
        return result;
    }

    private  String extractStr(String source, String reg, int group) {
        Matcher matcher = Pattern.compile(reg).matcher(source);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }

    public static void main(String[] args) {
    //    System.out.println(extractStr("@NUll @JJj Long jjj,@jjj@sss int sss","(@.+\\s+?)",1));
        System.out.println("com.jd.xstore.agreement.center.api.domain.VenderRate".substring(0,"com.jd.xstore.agreement.center.api.domain.VenderRate".lastIndexOf('.')) + "." + "RateEffectiveRange");
    }

}
