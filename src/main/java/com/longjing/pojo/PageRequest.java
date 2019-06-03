package com.longjing.pojo;

/**
 * Created by 18746 on 2019/6/3.
 */
public class PageRequest<T> {
    private int num=0;
    private int size;
    private T paramContent;

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

    public T getParamContent() {
        return paramContent;
    }

    public void setParamContent(T paramContent) {
        this.paramContent = paramContent;
    }
}
