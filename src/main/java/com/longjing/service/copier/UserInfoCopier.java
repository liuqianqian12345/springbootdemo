package com.longjing.service.copier;

import com.longjing.entity.UserEntity;
import com.longjing.pojo.UserInfo;
import com.longjing.service.copier.AbstractCopier;
import com.longjing.utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 18746 on 2019/6/3.
 */
@Component
public class UserInfoCopier extends AbstractCopier<UserEntity, UserInfo> {
    @Override
    public UserInfo copy(UserEntity userEntity, UserInfo userInfo) {
        userInfo.setId(userEntity.getId());
        userInfo.setUserName(userEntity.getUserName());
        userInfo.setPassword(userEntity.getPassword());
        userInfo.setSex(userEntity.getSex());
        userInfo.setStatus(userEntity.getStatus());
        userInfo.setAddress(userEntity.getAddress());
        userInfo.setCreateTime(Utils.calender2Str(userEntity.getCreateTime()));
        return userInfo;
    }
}
