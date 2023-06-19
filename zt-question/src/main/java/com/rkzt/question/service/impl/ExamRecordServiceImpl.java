package com.rkzt.question.service.impl;

import com.rkzt.common.config.JwtConfig;
import com.rkzt.question.domain.ExamRecord;
import com.rkzt.question.mapper.ExamRecordMapper;
import com.rkzt.question.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRecordServiceImpl implements ExamRecordService {

    @Autowired
    ExamRecordMapper examRecordMapper;

    @Override
    public List<ExamRecord> get_history(String token) {
//        String usrId = JwtConfig.getUserId(token);
        List<ExamRecord> list = examRecordMapper.selectAllByUserId("1");
        return list;
    }
}
