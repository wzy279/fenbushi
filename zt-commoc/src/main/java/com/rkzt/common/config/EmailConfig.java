package com.rkzt.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;
@Component
public class EmailConfig {
    @Autowired
    JavaMailSenderImpl mailSender;

    public String contextLoads(String email) throws MessagingException {
        int count=1;//默认发送一次
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        while(count--!=0){
            String codeNum = "";
            int [] code = new int[3];
            Random random = new Random();
            //自动生成验证码
            for (int i = 0; i < 6; i++) {
                int num = random.nextInt(10) + 48;
                int uppercase = random.nextInt(26) + 65;
                int lowercase = random.nextInt(26) + 97;
                code[0] = num;
                code[1] = uppercase;
                code[2] = lowercase;
                codeNum+=(char)code[random.nextInt(3)];
            }
            System.out.println(codeNum);
            //标题
            helper.setSubject("您的验证码为："+codeNum);
            //内容
            helper.setText("如果你收到了这个邮件，说明Wzy的邮箱验证功能成功实现了，记得告诉他。您的验证码为："+"<h2>"+codeNum+"</h2>"+"千万不能告诉别人哦！",true);
            //邮件接收者
            helper.setTo(email);
            //邮件发送者，必须和配置文件里的一样，不然授权码匹配不上
            helper.setFrom("1976823609@qq.com");
            mailSender.send(mimeMessage);
            System.out.println("邮件发送成功！"+(count+1));
            return codeNum;
        }
        return null;
    }
}
