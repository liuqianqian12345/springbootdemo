package com.longjing.pojo;

import java.io.Serializable;

/**
 * Created by 18746 on 2019/5/24.
 */
public class LoginInfo implements Serializable{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
