package com.dev.tools.kit.source;


import java.io.*;
import java.util.regex.Matcher;

import static com.dev.tools.kit.ConfigConstants.PROJECT_JAVA_PATH;

/**
 * @Description:通过本地项目获取内容的Reader
 * @Author: zhangjianfeng
 * @Date: 2018-09-27
 */
public class LocalProjectSourceReader implements SourceReader {
    private String projectRootPath;

    public LocalProjectSourceReader(String projectRootPath) {
        this.projectRootPath = projectRootPath;
    }

    @Override
    public String readContent(String className) {
        return getSourceContent(className);
    }

    private String getSourceLocation(String interfaceName) {
        return this.projectRootPath + getProjectJavaPath() + interfaceName.replaceAll("[.]", Matcher.quoteReplacement(File.separator)) + ".java";
    }

    private String getSourceContent(String interfaceName) {
        return readToBuffer(getSourceLocation(interfaceName));
    }

    private String getProjectJavaPath()
    {
        if( System.getProperty(PROJECT_JAVA_PATH) != null)
        {
            return System.getProperty(PROJECT_JAVA_PATH);
        }
        if(System.getenv().get(PROJECT_JAVA_PATH) != null)
        {
            return System.getenv().get(PROJECT_JAVA_PATH);
        }
        return File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
    }

    private String readToBuffer(String filePath){
        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                buffer.append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

}
