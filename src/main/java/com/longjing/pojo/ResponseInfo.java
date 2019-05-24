package com.longjing.pojo;

/**
 * Created by 18746 on 2019/5/24.
 */
public class ResponseInfo {
    private int code;
    private String errInfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", errInfo='" + errInfo + '\'' +
                '}';
    }
}
