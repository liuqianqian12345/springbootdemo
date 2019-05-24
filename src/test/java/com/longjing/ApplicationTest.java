package com.longjing;

import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.service.ApplicationService;
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
public class ApplicationTest {

    @Autowired
    private ApplicationService applicationService;
    @Test
    public void loginTest(){

        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setUserName("张三");
        loginInfo.setPassword("123456");
        ResponseInfo  responseInfo=applicationService.login(loginInfo);
        System.out.println(responseInfo.toString());
    }
}
