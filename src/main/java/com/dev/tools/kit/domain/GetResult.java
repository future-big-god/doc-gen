package com.dev.tools.kit.domain;

import java.util.List;

/**
 * Created by zhoujun5 on 2018/10/18.
 */
public class GetResult {
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    private List<Result> results;



    private Integer start;
    private Integer limit;
    private Integer size;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


}
