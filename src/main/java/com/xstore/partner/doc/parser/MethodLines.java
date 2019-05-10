package com.xstore.partner.doc.parser;

import java.util.List;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-27
 */
public class MethodLines {
    private String methodLine;
    private List<String> commentLines;
    private List<String> importLines;

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

    public List<String> getImportLines() {
        return importLines;
    }

    public void setImportLines(List<String> importLines) {
        this.importLines = importLines;
    }
}
