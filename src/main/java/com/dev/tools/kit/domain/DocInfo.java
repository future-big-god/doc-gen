package com.dev.tools.kit.domain;

/**
 * @Description:文档内容
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class DocInfo {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
