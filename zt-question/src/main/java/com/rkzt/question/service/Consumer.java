package com.rkzt.question.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rkzt.question.domain.ExamRecord;
import com.rkzt.question.domain.Question;
import com.rkzt.question.mapper.ExamRecordMapper;
import com.rkzt.question.mapper.QuestionMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Consumer {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    ExamRecordMapper examRecordMapper;


    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, Object> msg) {
        System.out.println("消费者接收到了object.queue的消息：【" + msg + "】");
        int jiluid = (int)msg.get("jiluid");
        List<Question> list = (List<Question>) msg.get("question");

        //1、遍历根据题目id查询正确答案与用户提交的答案进行比对，如果一样就加分，不一样就减分
        int score=0;
        for(Question element:list) {
            if (element.getQuestionAnwser().equals(questionMapper.selectQuestionAnwserByQuestionId(element.getQuestionId()))) {
                score++;
            }
        }

        //2、将用户最后的分数根据记录id提交到exam_record表中
        ExamRecord examRecord = new ExamRecord();
        examRecord.setScore(score);
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",jiluid);
        examRecordMapper.update(examRecord,queryWrapper);
    }
}
