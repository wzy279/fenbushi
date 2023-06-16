package com.rkzt.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rkzt.question.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}