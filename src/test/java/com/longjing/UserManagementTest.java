package com.longjing;

import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.pojo.UserInfo;
import com.longjing.service.UserManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 18746 on 2019/5/24.
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementTest {

    @Autowired
    private UserManagementService userManagementService;
    @Test
    public void loginTest(){

        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setUserName("张三");
        loginInfo.setPassword("123456");
        ResponseInfo  responseInfo=userManagementService.login(loginInfo);
        System.out.println(responseInfo.toString());
    }

    @Test
    public void updateUser(){
        UserInfo userInfo=new UserInfo();
        userManagementService.updateUser(userInfo);
    }
}
