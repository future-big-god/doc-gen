package com.dev.tools.kit.confluence.html;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class PBuilder {
    public String build(String text) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<p>").append(StringEscapeUtils.escapeHtml4(text)).append("</p>");
        return stringBuilder.toString();
    }
}
