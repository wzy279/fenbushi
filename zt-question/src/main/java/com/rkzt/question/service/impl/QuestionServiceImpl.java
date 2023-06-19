package com.rkzt.question.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rkzt.question.domain.ExamRecord;
import com.rkzt.question.domain.Question;
import com.rkzt.question.mapper.ExamRecordMapper;
import com.rkzt.question.mapper.QuestionMapper;
import com.rkzt.question.service.QuestionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ExamRecordMapper examRecordMapper;

    @Override
    public ArrayList<Question> get_random_question() {
        ArrayList<Question> arrayList = new ArrayList<>();
        Random random = new Random();
        int count=100;
        boolean[] vis = new boolean[count+5];
        for(int i=0;i<75;i++){
            int id = random.nextInt(count)+1;
            while (vis[id]){
                id= random.nextInt(count)+1;
            }
            vis[id]=true;
            arrayList.add(questionMapper.selectOneByQuestionId(id));
        }
//        List<Integer> arrayList1 = questionMapper.selectQuestionId();
//        for(Integer element:arrayList1) {
//            System.out.println(element);
//        }

        return arrayList;
    }

    @Override
    public boolean submit(String userid, Object object) {
        Map<String,Object> msg = new HashMap<>();
        //把这条考试记录加到exam_record表中
        ExamRecord examRecord =new ExamRecord();
        examRecord.setUserId(userid);
        examRecord.setTime(Calendar.getInstance().getTime());
        examRecordMapper.insert(examRecord);

        List<Integer> list = examRecordMapper.selectIdByUserId(userid);
        int jiluid = list.get(list.size()-1);
        System.out.println("记录id"+jiluid);
        msg.put("jiluid",jiluid);
        msg.put("question",object);
        //存入队列
        rabbitTemplate.convertAndSend("object.queue",msg);
        return true;
    }





}
