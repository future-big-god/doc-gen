package com.dev.tools.kit.source;

/**
 * 源码读取器
 */
public interface SourceReader {
    /**
     * 读取源码
     *
     * @param className
     * @return
     */
    String readContent(String className);
}
