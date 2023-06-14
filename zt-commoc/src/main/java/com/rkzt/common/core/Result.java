package com.rkzt.common.core;

import lombok.Data;

@Data
public class Result {
    private int code=200;
    private String msg="成功";
    private Object date;


    public Result(int code, String msg, Object date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public Result(String msg) {
        this.msg = msg;
    }
}
