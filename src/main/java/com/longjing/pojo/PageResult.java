package com.longjing.pojo;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by 18746 on 2019/6/3.
 */
public class PageResult<T>{
    private int num;
    private int size;
    private Long total;
    private int totalPages;
    private List<T> content;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
