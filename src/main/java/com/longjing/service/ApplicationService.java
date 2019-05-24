package com.longjing.service;

import com.longjing.entity.UserEntity;
import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.repo.UserEntityRepo;
import com.longjing.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 18746 on 2019/5/24.
 */
@Service
@Transactional
public class ApplicationService {

    @Autowired
    private UserEntityRepo userEntityRepo;

    public ResponseInfo login(LoginInfo loginInfo) {
        ResponseInfo responseInfo=new ResponseInfo();
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
        String password= Util.md5(loginInfo.getPassword());
        UserEntity userEntity = userEntityRepo.findByUserNameAndPassword(loginInfo.getUserName(), password);
        if (null==userEntity){
            responseInfo.setCode(0);
            responseInfo.setErrInfo("用户不存在");
            return responseInfo;
        }else {
            responseInfo.setCode(1);
            return responseInfo;
        }
    }
}
