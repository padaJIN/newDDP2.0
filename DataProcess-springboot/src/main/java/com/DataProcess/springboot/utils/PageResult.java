package com.DataProcess.springboot.utils;

import java.util.List;

public class PageResult<T> {
    private long total;
    private List<T> data;

    public PageResult(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }
}