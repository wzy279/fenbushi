package com.rkzt.user.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rkzt.common.core.Result;
import com.rkzt.user.domain.User;
import com.rkzt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("Login")
    public Result Login(@RequestBody User user){
        String token = userService.Login(user);
        if(token==null) return new Result(500,null,"账号或密码错误");
        return new Result(200,token,"登录成功");
    }

    @PostMapping("regist")
    public Result Register(@RequestBody JSONObject json){
        String userEmail = json.getString("userEmail");
        String userPassword = json.getString("userPassward");
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserPassward(userPassword);
        System.out.println(user);
        if(userService.Register(user)) return new Result(200,null,"注册成功");
        return new Result(500,null,"注册失败，此邮箱已注册过其他账户");
    }

    @PostMapping("retrievePassword1")
    public Result retrievePassword1(@RequestBody JSONObject json) throws MessagingException {
        String userEmail = json.getString("userEmail");
        if(userService.retrieve_password1(userEmail))
            return new Result(200,null,"已向您的邮箱发送验证码");
        return new Result(500,null,"请输入正确账户 或 操作过于频繁");
    }

    @PostMapping("retrievePassword2")
    public Result retrievePassword2(@RequestBody JSONObject json){
        String userEmail = json.getString("userEmail");
        String yanzhengma = json.getString("yanzhengma");
        String password = json.getString("password");
        if(userService.retrieve_password2(userEmail,yanzhengma,password))
            return new Result(200,null,"已设置新密码");
        return new Result(500,null,"验证码错误 或 验证码已过期");
    }

}
