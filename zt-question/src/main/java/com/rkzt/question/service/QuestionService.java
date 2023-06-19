package com.rkzt.question.service;

import com.rkzt.question.domain.Question;

import java.util.ArrayList;

public interface QuestionService {
    public ArrayList<Question> get_random_question();
    public boolean submit(String id,Object object);
}
