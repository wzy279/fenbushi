package com.rkzt.question.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName(value = "exam_record")
public class ExamRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "score")
    private Integer score;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "time")
    private Date time;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SCORE = "score";

    public static final String COL_TIME = "time";
}