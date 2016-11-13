package com.zhibo.infra;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jichao on 2016/10/31.
 */
public class RequestData {
    private String filter;

    public List<String> getFields() {
        return Arrays.asList(fields.split(","));
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    private String fields;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
