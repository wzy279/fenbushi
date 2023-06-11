package com.rkzt.common.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rkzt.common.config.RedisService;
import com.rkzt.common.domain.UserInformation;
import com.rkzt.common.service.UserInformationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class Testt {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("rabbitmq")
    public String testSendObjectQueue(){
        Map<String, Object> msg = new HashMap<>();
        msg.put("name","周杰伦");
        msg.put("age","21");
        for(int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("object.queue",msg);
        }
        return "发送成功";
    }

    @GetMapping("redis")
    public String tosendredis(){
        redisService.add("test:wzy","666");

        System.out.println(redisService.get("test:wzy"));
        return redisService.get("test:wzy");
    }

    @GetMapping("sql")
    public String getmessage(){
        UserInformation userInformation = userInformationService.getbyid("2");
        return userInformation.toString();
    }
    @GetMapping("sql2")
    public String getmessage2(){
        List<UserInformation> userInformationList = userInformationService.getAll();
        return userInformationList.toString();
    }


    @GetMapping("/jiami/{message}")
    public JSONObject jiami(@PathVariable String message){
        String j = passwordEncoder.encode(message);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("加密后：",j);
        jsonObject.put("解密",passwordEncoder.matches(message,j));
        return jsonObject;
    }

}
