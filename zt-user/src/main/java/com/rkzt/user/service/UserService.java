package com.rkzt.user.service;

import com.rkzt.user.domain.User;

import javax.mail.MessagingException;

public interface UserService {
    public String Login(User user);
    public boolean Register(User user);

    public boolean retrieve_password1(String user_email) throws MessagingException;
    public boolean retrieve_password2(String user_email,String yanzhengma,String password);
}
