package com.longjing.controller;

import com.longjing.pojo.*;
import com.longjing.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 18746 on 2019/1/18.
 */
@RestController
@RequestMapping("management")
public class UserManagementController {
    @Autowired
    private UserManagementService applicationService;

    @PostMapping(value = "login")
    public ResponseInfo login(@RequestBody LoginInfo loginInfo) {
        return applicationService.login(loginInfo);
    }

    @PostMapping(value = "updateUser")
    public ResponseInfo updateUser(@RequestBody UserInfo userInfo){

        return applicationService.updateUser(userInfo);
    }

    @PostMapping(value = "addUser")
    public ResponseInfo addUser(@RequestBody UserInfo userInfo){
        return applicationService.addUser(userInfo);
    }

    @PostMapping(value="findUser")
    public PageResult<UserInfo> findUser(@RequestBody PageRequest<UserParam> param){return applicationService.findUser(param);}
}
