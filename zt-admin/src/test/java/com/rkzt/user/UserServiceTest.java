package com.rkzt.user;

import com.rkzt.user.domain.User;
import com.rkzt.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userService;


    @Test
    void Register(){
        User user = new User();
        user.setUserEmail("fenxueyong@qit.edu.cn");
        user.setUserPassward("123321");
        System.out.println(userService.Register(user));
    }

    @Test
    void Login(){
        User user = new User();
        user.setUserEmail("fenxueyong@qit.edu.cn");
        user.setUserPassward("12321");
        System.out.println(userService.Login(user));
        user.setUserPassward("123321");
        System.out.println(userService.Login(user));
    }

    @Test
    void retrieve_password1() throws MessagingException {
        System.out.println(userService.retrieve_password1("fengxueyong@qit.edu.cn"));
    }

    @Test//CxC14B
    void retrieve_password2(){
        System.out.println(userService.retrieve_password2("fengxueyong@qit.edu.cn","o153DU","123456"));
    }
}
