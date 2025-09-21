package com.yihuobao.yhb.utils;

import lombok.Data;

@Data
public class Result<T> {


    private int code;//状态码

    private String msg;//说明

    private boolean status;//成功或失败的标识

    private T data;//数据

    private Result() {
    }

    private Result(Integer code, Boolean status, String msg, T data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setStatus(true);
        result.setMsg("成功");
        return result;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(200,true,"成功",data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200,true,msg,data);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code,true,msg,data);
    }

    public static Result error(int code, String msg) {
        Result result = new Result<>(code,false,msg,null);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result(400,false,msg,null);
        return result;
    }
    public static Result error() {
        return new Result(ResultCode.FAIL);
    }

    public static <T> Result<T> error( int code, String msg, T data) {
        return new Result<>(code,false,msg,data);
    }
}

