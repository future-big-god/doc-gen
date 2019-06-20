package com.dev.tools.kit.confluence.html;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class H2Builder {
    public String build(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h2>").append(StringEscapeUtils.escapeHtml4(text)).append("</h2>");
        return stringBuilder.toString();
    }
}
