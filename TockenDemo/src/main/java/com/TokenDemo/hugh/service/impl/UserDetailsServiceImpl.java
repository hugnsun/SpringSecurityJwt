package com.TokenDemo.hugh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.TokenDemo.hugh.entity.User;
import com.TokenDemo.hugh.entity.vo.LoginUser;
import com.TokenDemo.hugh.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author hughs
 * 重写默认的登录验证方法
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Lazy
    private UserMapper userMapper;

    /**
     * @param username 用户名
     * @return 返沪用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据 用户名进行用户对应的权限信息进行查询
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        if (StrUtil.isNotEmpty(username)) {
            userLambdaQueryWrapper.eq(User::getUserName,username);
        }
        User user = userMapper.selectOne(userLambdaQueryWrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO 把对应的权限信息封装成 UserDetail 对象 返回

        // 存在用户我们就封装成UserDetail 对象
        return new LoginUser(user);
    }
}
