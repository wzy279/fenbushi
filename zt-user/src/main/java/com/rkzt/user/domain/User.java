package com.rkzt.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
    * 用户信息表
    */
@Data
@TableName(value = "`user`")
public class User {
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_passward")
    private String userPassward;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_EMAIL = "user_email";

    public static final String COL_USER_PASSWARD = "user_passward";
}