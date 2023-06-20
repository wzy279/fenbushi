package com.rkzt.question.controller;

import com.rkzt.common.anno.JwtToken;
import com.rkzt.common.core.Result;
import com.rkzt.question.domain.ExamRecord;
import com.rkzt.question.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/question")
public class ExamRecordController {

    @Autowired
    ExamRecordService examRecordService;

    @PostMapping("LookHistory")
    @JwtToken
    @CrossOrigin
    public Result getHistory(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        return new Result(200,examRecordService.get_history(token),"success");
    }
}
