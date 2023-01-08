package com.TokenDemo.hugh.service;


import com.TokenDemo.hugh.entity.User;
import com.TokenDemo.hugh.utils.ResponseResult;

/**
 * 登录业务层接口类
 * @author hughs
 */
public interface LoginService {


    /**
     * @param user 登录的用户‘
     *            进行数据的登录逻辑校验
     * @return  返回用户的信息
     */
    ResponseResult login(User user);

    /**
     * @return 进行数据的注销操作
     */
    ResponseResult loginOut();

}
