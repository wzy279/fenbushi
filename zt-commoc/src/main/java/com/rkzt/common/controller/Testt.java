package com.rkzt.common.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rkzt.common.config.EmailConfig;
import com.rkzt.common.config.JwtConfig;
import com.rkzt.common.config.MinioProperties;
import com.rkzt.common.config.RedisService;
import com.rkzt.common.domain.User;
import com.rkzt.common.domain.UserInformation;
import com.rkzt.common.domain.UserMessage;
import com.rkzt.common.service.UserInformationService;
import com.rkzt.common.service.UserMessageService;
import com.rkzt.common.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
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
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private MinioProperties minioProperties;


    @Autowired
    private UserMessageService userMessageService;


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

    //测试生成token是否成功
    @GetMapping("/jwt/{message}")
    public JSONObject jwt(@PathVariable String message){
        User user = new User();
        user.setId("1");
        user.setName(message);
        JSONObject jsonObject = new JSONObject();
        String token = JwtConfig.getoken(user);
        jsonObject.put("token",token);
        user = JwtConfig.jiemi(token);
        jsonObject.put("User",user);
        return jsonObject;
    }


    /*
    * 邮箱发送验证码
    * */
    @GetMapping("/email")
    public JSONObject email() throws MessagingException {
        String email = "wangzhaoyi@qit.edu.cn";
        String yanzhengma = emailConfig.contextLoads(email);
        JSONObject object = new JSONObject();
        object.put("验证码",yanzhengma);
        return object;
    }


    @PostMapping("upload/minio")
    public String uploadminio(MultipartFile file){
        try {
            long l = System.currentTimeMillis();
            String fileName = l+file.getOriginalFilename();
            MinioUtil.createBucket(minioProperties.getBucket());
            MinioUtil.uploadFile(minioProperties.getBucket(),file,fileName);
            String url = MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(),fileName);

            String[]  strs=url.split("\\?");
            url = strs[0];
            System.out.println(url);
            url = java.net.URLDecoder.decode(url,"UTF-8");
            return url;
        }
        catch (Exception e){
            return "error";
        }
    }


    @GetMapping("tt")
    public UserMessage tttt(){
        return userMessageService.getById(1);
    }




}
