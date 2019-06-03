package com.longjing.pojo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by 18746 on 2019/5/24.
 */
public class UserInfo implements Serializable{
    private String id;
    private String userName;//用户名
    private String password;//密码 md5加密大写
    private String sex;//性别
    private String address;//住址
    private String status;//状态
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo1 = (UserInfo) o;

        if (id != null ? !id.equals(userInfo1.id) : userInfo1.id != null) return false;
        if (userName != null ? !userName.equals(userInfo1.userName) : userInfo1.userName != null) return false;
        if (password != null ? !password.equals(userInfo1.password) : userInfo1.password != null) return false;
        if (sex != null ? !sex.equals(userInfo1.sex) : userInfo1.sex != null) return false;
        if (address != null ? !address.equals(userInfo1.address) : userInfo1.address != null) return false;
        if (status != null ? !status.equals(userInfo1.status) : userInfo1.status != null) return false;
        return createTime != null ? createTime.equals(userInfo1.createTime) : userInfo1.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
