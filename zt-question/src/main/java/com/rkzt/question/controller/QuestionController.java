package com.rkzt.question.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rkzt.common.anno.JwtToken;
import com.rkzt.common.config.JwtConfig;
import com.rkzt.common.core.Result;
import com.rkzt.question.domain.Question;
import com.rkzt.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("GetQuestion")
    @JwtToken
    @CrossOrigin
    public Result getQuestion(){
        return new Result(200,questionService.get_random_question(),"success");
    }



    @PostMapping("CommitQuestion")
    @JwtToken
    @CrossOrigin
    public Result submitQuestion(HttpServletRequest httpServletRequest, @RequestBody JSONObject json){
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Token");
        System.out.println(json.toString());
        Object o= json.get("data");
//        System.out.println(o.toString());
//        System.out.println("arraylist是"+arrayList);
        return new Result(200,questionService.submit(token,o),"success");
    }
}
