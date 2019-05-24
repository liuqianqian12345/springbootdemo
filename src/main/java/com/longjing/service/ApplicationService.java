package com.longjing.service;

import com.longjing.entity.UserEntity;
import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.pojo.UserInfo;
import com.longjing.redis.JedisClient;
import com.longjing.repo.UserEntityRepo;
import com.longjing.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 18746 on 2019/5/24.
 */
@Service
public class ApplicationService {

    @Autowired
    private UserEntityRepo userEntityRepo;

    public ResponseInfo login(LoginInfo loginInfo) {
        ResponseInfo responseInfo=new ResponseInfo();
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
    //利用redis双删模式保持修改数据的一致性
    public ResponseInfo updateUser(UserInfo userInfo){
        ResponseInfo responseInfo=new ResponseInfo();
        JedisClient.set("name","liuqian");
        String a=JedisClient.get("name");
        System.out.println("========="+a);
        return null;
    }
}
