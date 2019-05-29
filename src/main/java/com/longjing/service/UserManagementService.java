package com.longjing.service;

import com.longjing.entity.UserEntity;
import com.longjing.pojo.LoginInfo;
import com.longjing.pojo.ResponseInfo;
import com.longjing.pojo.SystemConstant;
import com.longjing.pojo.UserInfo;
import com.longjing.redis.JedisClient;
import com.longjing.repo.UserEntityRepo;
import com.longjing.utils.JwtUtil;
import com.longjing.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by 18746 on 2019/5/24.
 */
@Service
public class UserManagementService {

    @Autowired
    private UserEntityRepo userEntityRepo;
    private static final ScheduledExecutorService executorService=new ScheduledThreadPoolExecutor(2);

    /**
     *
     * @param loginInfo
     * @return
     */
    public ResponseInfo login(LoginInfo loginInfo) {
        ResponseInfo responseInfo=new ResponseInfo();
        String password= Utils.md5(loginInfo.getPassword());
        String name=loginInfo.getUserName();
        UserEntity userEntity = userEntityRepo.findByUserNameAndPassword(loginInfo.getUserName(), password);
        if (null==userEntity){
            responseInfo.setCode(0);
            responseInfo.setInfo("用户名或密码错误");
            return responseInfo;
        }else {
            String jwt= JwtUtil.createJWT(UUID.randomUUID().toString(),userEntity.getUserName(), SystemConstant.JWT_TTLMILIS);
            responseInfo.setCode(1);
            responseInfo.setInfo(jwt);
            return responseInfo;
        }
    }
    //利用redis双删模式保持修改数据的一致性

    /**
     *
     * @param userInfo
     * @return
     */
    public ResponseInfo updateUser(final UserInfo userInfo){
        ResponseInfo responseInfo=new ResponseInfo();
        if (null!=userInfo){
            final String key=userInfo.getId();
            boolean flag=JedisClient.hasHash(key,"userName");
            if (flag){
                JedisClient.delete(userInfo.getId());
            }
            UserEntity userEntity=userEntityRepo.findOne(key);
            BeanUtils.copyProperties(userInfo,userEntity);
            userEntityRepo.save(userEntity);

            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    JedisClient.delete(key);
                    System.out.println("delete ok");
                }
            },100, TimeUnit.MILLISECONDS);
            responseInfo.setCode(SystemConstant.SUCCESS);
            responseInfo.setInfo("成功");
            return responseInfo;
        }else {
            responseInfo.setCode(SystemConstant.DATA_IS_NULL);
            responseInfo.setInfo("数据为空");
            return responseInfo;
        }
    }

    //
    public ResponseInfo addUser(final UserInfo userInfo){

        return null;
    }
}
