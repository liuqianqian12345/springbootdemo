package com.longjing.controller;

import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 18746 on 2019/1/18.
 */
@RestController
@RequestMapping("management")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping(value = "login")
    public ResponseInfo login(@RequestBody LoginInfo loginInfo) {
        return applicationService.login(loginInfo);
    }
}
