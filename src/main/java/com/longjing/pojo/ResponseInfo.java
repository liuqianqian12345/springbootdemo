package com.longjing.pojo;

/**
 * Created by 18746 on 2019/5/24.
 */
public class ResponseInfo {
    private int code;
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}
