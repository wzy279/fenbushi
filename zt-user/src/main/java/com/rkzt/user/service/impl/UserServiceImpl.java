package com.rkzt.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rkzt.common.config.EmailConfig;
import com.rkzt.common.config.JwtConfig;
import com.rkzt.common.config.RedisService;
import com.rkzt.user.domain.User;
import com.rkzt.user.mapper.UserMapper;
import com.rkzt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Wrapper;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RedisService redisService;
    @Autowired
    EmailConfig emailConfig;


    //登录
    @Override
    public String Login(User user) {
        String password_true = userMapper.selectOneUserPasswardByUserEmail(user.getUserEmail());

        if(passwordEncoder.matches(user.getUserPassward(),password_true)){
            user.setUserId(userMapper.selectOneUserIdByUserEmail(user.getUserEmail()));
            return JwtConfig.sign(user.getUserId());
        }
        else return null;
    }

    //注册
    @Override
    public boolean Register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.in("user_email",user.getUserEmail());
        if(userMapper.selectCount(wrapper).equals(0L)){
            String password_jiami = passwordEncoder.encode(user.getUserPassward());
            user.setUserId(UUID.randomUUID().toString());
            user.setUserPassward(password_jiami);
            userMapper.insert(user);
            return true;
        }
        else return false;
    }

    //找回密码,第一步
    @Override
    public boolean retrieve_password1(String user_email) throws MessagingException {
        if(redisService.hasKey(user_email)) return false;
        else{
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.in("user_email",user_email);
            if(userMapper.selectCount(wrapper).equals(0L)) return false;
            else{
                String yanzhengma = emailConfig.contextLoads(user_email);
                redisService.add("yanzhengma:"+user_email,yanzhengma,200, TimeUnit.SECONDS);
                return true;
            }
        }
    }
//    找回密码，第二步
    @Override
    public boolean retrieve_password2(String user_email,String yanzhengma,String password) {
        if(redisService.hasKey("yanzhengma:"+user_email)){
            if(redisService.get("yanzhengma:"+user_email).equals("\""+yanzhengma+"\"")){
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.in("user_email",user_email);
                userMapper.delete(wrapper);
                String password_jiami = passwordEncoder.encode(password);
                User user = new User();
                user.setUserEmail(user_email);
                user.setUserPassward(password_jiami);
                user.setUserId(UUID.randomUUID().toString());
                userMapper.insert(user);
                redisService.delete("yanzhengma:"+user_email);
                return true;
            }
        }
        return false;
    }


}
