package com.rkzt.question.service;

import com.rkzt.question.domain.ExamRecord;

import java.util.List;

public interface ExamRecordService {
    public List<ExamRecord> get_history(String usrId);
}
