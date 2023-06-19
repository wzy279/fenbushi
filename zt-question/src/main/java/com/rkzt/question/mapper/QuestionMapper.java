package com.rkzt.question.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rkzt.question.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    Question selectOneByQuestionId(@Param("questionId")Integer questionId);

    List<Integer> selectQuestionId();

    String selectQuestionAnwserByQuestionId(@Param("questionId")Integer questionId);

}
