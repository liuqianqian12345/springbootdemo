package com.longjing.service;

import com.longjing.entity.UserEntity;
import com.longjing.pojo.*;
import com.longjing.redis.JedisClient;
import com.longjing.repo.UserEntityRepo;
import com.longjing.service.copier.UserInfoCopier;
import com.longjing.utils.JwtUtil;
import com.longjing.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by 18746 on 2019/5/24.
 */
@Service
public class UserManagementService {

    @Autowired
    private UserEntityRepo userEntityRepo;
    @Autowired
    private UserInfoCopier userInfoCopier;
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);

    /**
     * @param loginInfo
     * @return
     */
    public ResponseInfo login(LoginInfo loginInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        String password = Utils.md5(loginInfo.getPassword());
        String name = loginInfo.getUserName();
        UserEntity userEntity = userEntityRepo.findByUserNameAndPasswordAndStatus(name, password, "1");
        if (null == userEntity) {
            responseInfo.setCode(0);
            responseInfo.setInfo("用户名或密码错误");
            return responseInfo;
        } else {
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), userEntity.getUserName(), SystemConstant.JWT_TTLMILIS);
            responseInfo.setCode(SystemConstant.SUCCESS);
            responseInfo.setInfo(jwt);
            return responseInfo;
        }
    }
    //利用redis双删模式保持修改数据的一致性

    /**
     * @param userInfo
     * @return ResponseInfo
     */
    public ResponseInfo updateUser(final UserInfo userInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (null != userInfo) {
            final String key = userInfo.getUserName();
            //先看缓存中有没有用户信息，有的话删除缓存中信息
            UserInfo userInfo1 = (UserInfo)JedisClient.getObject(key);
            if (null!=userInfo1) {
                JedisClient.delete(userInfo.getId());
            }
            UserEntity userEntity = userEntityRepo.findOne(key);
            BeanUtils.copyProperties(userInfo, userEntity);
            userEntityRepo.save(userEntity);

            //延时100ms再次删除缓存中用户信息，避免其他线程读取到脏数据
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    JedisClient.delete(key);
                    System.out.println("delete ok");
                }
            }, 100, TimeUnit.MILLISECONDS);
            responseInfo.setCode(SystemConstant.SUCCESS);
            responseInfo.setInfo("成功");
            return responseInfo;
        } else {
            responseInfo.setCode(SystemConstant.DATA_IS_NULL);
            responseInfo.setInfo("数据为空");
            return responseInfo;
        }
    }

    //添加用户
    public ResponseInfo addUser(UserInfo userInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        //先去缓存中查看是否用用户名重复的信息，有的话添加失败
        if (null != userInfo) {
            final String key = userInfo.getUserName();
            //先看缓存中有没有用户信息，有的话删除缓存中信息
            UserInfo userInfo1 = (UserInfo)JedisClient.getObject(key);
            if (null!=userInfo1 && "1".equals(userInfo1.getStatus())) {
                responseInfo.setInfo("用户名已存在");
                responseInfo.setCode(SystemConstant.USERNAME_HAS_EXIST);
                return responseInfo;
            } else {//缓存中没有的话去数据库中进行查询用户名是否合法
                UserEntity userEntity = userEntityRepo.findByUserNameAndStatus(key, "1");
                if (null == userEntity) {
                    UserEntity userEntity1 = new UserEntity();
                    BeanUtils.copyProperties(userInfo, userEntity1);
                    userEntity1.setId(UUID.randomUUID().toString());
                    userEntity1.setStatus("1");
                    userEntity1.setPassword(Utils.md5(userInfo.getPassword()));
                    userEntity1.setCreateTime(Calendar.getInstance());
                    userEntityRepo.save(userEntity1);
                    JedisClient.setObject(userEntity1.getId(),userEntity1);//写入缓存
                    responseInfo.setInfo("成功");
                    responseInfo.setCode(SystemConstant.SUCCESS);
                    return responseInfo;
                } else {
                    responseInfo.setInfo("用户名已存在");
                    responseInfo.setCode(SystemConstant.USERNAME_HAS_EXIST);
                    return responseInfo;
                }
            }
        }
        responseInfo.setInfo("失败");
        responseInfo.setCode(SystemConstant.FAIL);
        return responseInfo;
    }

    public PageResult<UserInfo> findUser(com.longjing.pojo.PageRequest<UserParam> param){
        final UserParam userParam=param.getParamContent();
        Specification spec=new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList=new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(userParam.getUserName())){
                    predicateList.add(criteriaBuilder.equal((root.<String>get("userName")),userParam.getUserName()));
                }
                if (!StringUtils.isEmpty(userParam.getStatus())){
                    predicateList.add(criteriaBuilder.equal((root.<String>get("status")),userParam.getUserName()));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        Sort sort=new Sort(Sort.Direction.DESC,"userName");
        Pageable pageable=new PageRequest(param.getNum(),param.getSize(),sort);
        Page<UserEntity> page=userEntityRepo.findAll(spec,pageable);
        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        List<UserEntity> userEntities=page.getContent();

        for (UserEntity u:userEntities
             ) {
            UserInfo userInfo=userInfoCopier.copy(u,new UserInfo());
            userInfos.add(userInfo);
        }
        PageResult<UserInfo> pageResult=new PageResult<UserInfo>();
        pageResult.setContent(userInfos);
        pageResult.setNum(page.getNumber());
        pageResult.setSize(page.getSize());
        pageResult.setTotal(page.getTotalElements());
        pageResult.setTotalPages(page.getTotalPages());
        return pageResult;
    }
}
