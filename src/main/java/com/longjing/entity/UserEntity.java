package com.longjing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by 18746 on 2019/5/24.
 */
@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    private String id= UUID.randomUUID().toString();
    private String userName;//用户名
    private String password;//密码 md5加密大写
    private String sex;//性别
    private String adress;//住址

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
