package com.dev.tools.kit.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:引用行Parser
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class ImportLinesParser {
    private static final Pattern pattern = Pattern.compile("import\\s+.+?[;]");
    /**
     * 引用行
     */
    private List<String> lines;

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void parse(String sourceContent) {
        Matcher matcher = pattern.matcher(sourceContent);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group(0));
        }
        this.setLines(result);
    }
}
