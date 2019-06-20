package com.dev.tools.kit.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Matcher;


/**
 * @Description:通过本地项目获取内容的Reader
 * @Author: zhangjianfeng
 * @Date: 2018-09-27
 */
public class LocalProjectSourceReader implements SourceReader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String srcRootPath;

    public LocalProjectSourceReader(String srcRootPath) {
        this.srcRootPath = srcRootPath;
    }

    @Override
    public String readContent(String className) {
        return getSourceContent(className);
    }

    private String getSourceLocation(String interfaceName) {
        return this.srcRootPath + interfaceName.replaceAll("[.]", Matcher.quoteReplacement(File.separator)) + ".java";
    }

    private String getSourceContent(String interfaceName) {
        return readToBuffer(getSourceLocation(interfaceName));
    }

    private String readToBuffer(String filePath) {
        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                buffer.append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.error("read error", e);
            throw new RuntimeException(e);
        } finally {
            close(reader);
        }
        return buffer.toString();
    }

    private void close(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error("close error", e);
            }
        }
    }

}
