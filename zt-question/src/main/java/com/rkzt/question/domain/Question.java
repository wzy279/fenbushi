package com.rkzt.question.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 软考选择题题目列表
    */
@Data
@TableName(value = "question")
public class Question {
    /**
     * 问题id
     */
    @TableId(value = "question_id", type = IdType.INPUT)
    private Integer questionId;

    /**
     * 题目内容

     */
    @TableField(value = "question_context")
    private String questionContext;

    /**
     * 题目答案
     */
    @TableField(value = "question_anwser")
    private String questionAnwser;

    /**
     * A选项
     */
    @TableField(value = "anwser_A")
    private String anwserA;

    /**
     * B选项
     */
    @TableField(value = "anwser_B")
    private String anwserB;

    /**
     * C选项
     */
    @TableField(value = "anwser_C")
    private String anwserC;

    /**
     * D选项
     */
    @TableField(value = "anwser_D")
    private String anwserD;

    /**
     * 解析
     */
    @TableField(value = "analyzee")
    private String analyzee;

    /**
     * 类别
     */
    @TableField(value = "type")
    private Integer type;

    public static final String COL_QUESTION_ID = "question_id";

    public static final String COL_QUESTION_CONTEXT = "question_context";

    public static final String COL_QUESTION_ANWSER = "question_anwser";

    public static final String COL_ANWSER_A = "anwser_A";

    public static final String COL_ANWSER_B = "anwser_B";

    public static final String COL_ANWSER_C = "anwser_C";

    public static final String COL_ANWSER_D = "anwser_D";

    public static final String COL_ANALYZE = "analyze";

    public static final String COL_TYPE = "type";
}