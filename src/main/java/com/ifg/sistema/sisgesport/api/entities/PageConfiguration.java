package com.ifg.sistema.sisgesport.api.entities;

public class PageConfiguration {
    public int page;
    public String order;
    public int size;
    public String sort;

    public PageConfiguration() {
        this.page = 0;
        this.order = "id";
        this.size = 10;
        this.sort = "DESC";
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
