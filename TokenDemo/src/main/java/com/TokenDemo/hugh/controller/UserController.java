package com.TokenDemo.hugh.controller;


import com.TokenDemo.hugh.entity.User;
import com.TokenDemo.hugh.service.LoginService;
import com.TokenDemo.hugh.utils.ResponseResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author hughs
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult userLogin(@RequestBody User user){
       return loginService.login(user);
    }

     @GetMapping("/loginOut")
     public  ResponseResult loginOut(){
        return loginService.loginOut();
     }
    @GetMapping("/hello")
    public String hello(){
        return "测试";
    }
}
