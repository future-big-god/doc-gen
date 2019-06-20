package com.dev.tools.kit.domain;

/**
 * Created by zhoujun5 on 2018/10/18.
 */
public class Result {
    private String id;
    private String type;
    private String title;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
