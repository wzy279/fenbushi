package com.rkzt.question.service;

import com.rkzt.question.domain.Question;
import com.rkzt.question.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    void get_random_question(){
        ArrayList<Question> arrayList=questionService.get_random_question();
        for(Question element:arrayList){
            System.out.println(element);
        }
    }
}
