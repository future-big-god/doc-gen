package com.dev.tools.kit.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:解析相关工具类
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class ParseUtils {

    /**
     * 使用正则提取字符串
     *
     * @param source 源字符串
     * @param reg    正则
     * @param group  组
     * @return
     */
    public static String extractStr(String source, String reg, int group) {
        Matcher matcher = Pattern.compile(reg).matcher(source);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }

    /**
     * 生成实际类型（去除泛型）
     * DataDto 将返回DataDto
     * List<DataDto>  返回DataDto
     * Map<String,DataDto>  返回DataDto
     *
     * @param type
     * @return
     */
    public static String genRealTypeShort(String type) {
        if (!type.contains("<")) {
            return type;
        }
        String tempStr = ParseUtils.extractStr(type, "<(.+)>", 1);
        if (tempStr.contains(",")) {
            return tempStr.split("[,]")[1].trim();
        }
        return tempStr;
    }

    /**
     * 是否基本类型
     *
     * @param type
     * @return
     */
    public static boolean isBaseType(String type) {
        //基础类型
        if (type.equals("int") || type.equalsIgnoreCase("float") || type.equalsIgnoreCase("double")
                || type.equalsIgnoreCase("long") || type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("char")
                || type.equalsIgnoreCase("byte") || type.equalsIgnoreCase("short") || type.equals("BigDecimal")
                || type.equals("Integer") || type.equals("String") || type.equals("Date")) {
            return true;
        }
        return false;
    }


    public static boolean isBaseLetter(String type){
        Matcher matcher = Pattern.compile("[A-Z]").matcher(type);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
    /**
     * 从注释行中获取描述
     *
     * @param commentLines
     * @return
     */
    public static String genDesc(List<String> commentLines) {
        if (commentLines == null || commentLines.size() < 2) {
            return null;
        }
        return commentLines.get(1).replaceAll("\\s*[*]\\s*", "");
    }


    /**
     * 获取对象的全路径 如 com.test.ADto
     *
     * @param type        类型 如ADto
     * @param className   所在的类，需要它是因为，如果在同一个包下，可能在import中找不到
     * @param importLines import
     * @return 全路径
     */
    public static String genFullType(String type, String className, List<String> importLines) {

        for (String importStr : importLines) {
            if (importStr.trim().endsWith("." + type + ";")) {
                return extractStr(importStr, "\\s*import\\s*(.+?)[;]", 1);
            }
        }
        return className.substring(0, className.lastIndexOf('.')) + "." + type;
    }
}
