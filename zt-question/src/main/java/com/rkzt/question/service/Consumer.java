package com.rkzt.question.service;

import com.rkzt.question.domain.Question;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Consumer {

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, Object> msg) {
        System.out.println("消费者接收到了object.queue的消息：【" + msg + "】");
        int jiluid = (int)msg.get("jiluid");
        List<Question> list = (List<Question>) msg.get("question");

    }
}
