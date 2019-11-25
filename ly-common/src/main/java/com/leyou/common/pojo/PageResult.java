package com.leyou.common.pojo;

import java.util.List;

public class PageResult<T> {
    private Long total; // 总条数
    private Long totalPage; // 总页数
    private List<T> items; // 当前页数据
    private int code; // 状态码
    private int perPage; // 每页条数
    private int currentPage; // 当前页数

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageResult() {

    }

    public PageResult(Long total, List<T> items, int code, int perPage, int currentPage) {
        this.total = total;
        this.items = items;
        this.code = code;
        this.perPage = perPage;
        this.currentPage = currentPage;
    }

    public PageResult(Long total, Long totalPage, List<T> items, int code, int perPage, int currentPage) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
        this.code = code;
        this.perPage = perPage;
        this.currentPage = currentPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
