package com.TokenDemo.hugh.service.impl;

import com.TokenDemo.hugh.entity.User;
import com.TokenDemo.hugh.entity.vo.LoginUser;
import com.TokenDemo.hugh.service.LoginService;
import com.TokenDemo.hugh.utils.JwtUtil;
import com.TokenDemo.hugh.utils.RedisCache;
import com.TokenDemo.hugh.utils.ResponseResult;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


/**
 * @author hughs
 * @date 2023-01-08
 */
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {


    private AuthenticationManager authenticationManager;

    private RedisCache redisCache;

    /**
     * @param user 登录的用户
     *             进行数据的登录逻辑校验
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        // 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证不通过 抛出异常
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("验证不通过 登录失败");
        }

        // 如果认证通过 使用userId + passWord 生成Jwt
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        User userData = principal.getUser();
        String userId = String.valueOf(userData.getId());
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token",jwt);

        // 存放到Redis
        redisCache.setCacheObject("login"+userId,principal);

        return new ResponseResult(200,"登录成功",hashMap);
    }

    @Override
    public ResponseResult loginOut() {

        // 从上下文中获取登录信息
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // 拿去到对应的数据
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();

        Long id = loginUser.getUser().getId();

        // 删除Redis 中的数据
        redisCache.deleteObject("login"+id);
        return new ResponseResult(200,"注销成功");
    }
}
