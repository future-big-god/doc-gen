package com.dev.tools.kit.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:方法代码行Parser
 * @Author: zhangjianfeng
 * @Date: 2018-09-27
 */
public class MethodLinesParser {

    private static final Pattern pattern = Pattern.compile("([/][*][*][\\s\\S]*?[*][/])([\\s\\S]*?[;])");
    /**
     * 方法行
     */
    private String methodLine;
    /**
     * 注释行
     */
    private List<String> commentLines;

    public String getMethodLine() {
        return methodLine;
    }

    public void setMethodLine(String methodLine) {
        this.methodLine = methodLine;
    }

    public List<String> getCommentLines() {
        return commentLines;
    }

    public void setCommentLines(List<String> commentLines) {
        this.commentLines = commentLines;
    }

    public void parse(String sourceContent, String methodName) {
        String content = sourceContent.replaceAll("[/][*][*][\\s\\S]*?public\\s+interface", "");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(2).matches("[\\s\\S]*?" + methodName + "[(][\\s\\S]*?[;]")) {
                    this.setCommentLines(Arrays.asList(matcher.group(1).split("\n")));
                    this.setMethodLine(matcher.group(2));
                    return;
                }
            }
        }
    }

}
