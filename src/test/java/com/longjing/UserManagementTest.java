package com.longjing;

import com.longjing.pojo.*;
import com.longjing.redis.JedisClient;
import com.longjing.service.UserManagementService;
import com.longjing.utils.SerializeUtil;
import org.apache.catalina.User;
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

    @Test
    public void jedisTest(){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("zangs");
        userInfo.setAddress("dasd");
        JedisClient.setObject("name",userInfo);
        Object o = JedisClient.getObject("name");
        if (o instanceof UserInfo){
            UserInfo userInfo1= (UserInfo) o;
            System.out.println(userInfo1);
        }
    }

    @Test
    public void addUserTest(){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("王五");
        userInfo.setAddress("上海");
        userInfo.setPassword("123456");
        userManagementService.addUser(userInfo);
    }

    @Test
    public void findUser(){
        UserParam userParam=new UserParam();
        PageRequest<UserParam> pageRequest=new PageRequest<UserParam>();
        pageRequest.setNum(0);
        pageRequest.setSize(1);
        PageResult pageResult=userManagementService.findUser(pageRequest);
        System.out.println(pageResult.toString());
    }
}
