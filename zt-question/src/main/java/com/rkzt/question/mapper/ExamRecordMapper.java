package com.rkzt.question.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rkzt.question.domain.ExamRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.parameters.P;

@Mapper
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {
    List<Integer> selectIdByTime(@Param("time")Date time);

    List<Integer> selectIdByUserId(@Param("userId")String userId);

//    boolean updateScoreById(@Param("questionId")Integer questionId);
    List<ExamRecord> selectAllByUserId(@Param("userId")String userId);










}
