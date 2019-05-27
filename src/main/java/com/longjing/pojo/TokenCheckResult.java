package com.longjing.pojo;

import io.jsonwebtoken.Claims;

/**
 * Created by 18746 on 2019/5/27.
 */
public class TokenCheckResult {
    private boolean success;
    private int errorCode;
    private Claims claims;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "TokenCheckResult{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                '}';
    }
}
