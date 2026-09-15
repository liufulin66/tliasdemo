package com.demo.pojo;

import lombok.Data;

/**
 * 统一响应结果封装类
 * 所有接口返回该结构，code 表示业务状态码，msg 表示提示信息，data 表示业务数据
 */
@Data
public class Result<T> {

    /** 成功状态码 */
    public static final Integer SUCCESS = 1;
    /** 失败状态码 */
    public static final Integer FAIL = 0;

    private Integer code;   // 状态码：1 成功，0 失败
    private String msg;     // 提示信息
    private T data;         // 响应数据

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /** 成功，无数据 */
    public static <T> Result<T> success() {
        return new Result<>(SUCCESS, "操作成功", null);
    }

    /** 成功，带数据 */
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, "操作成功", data);
    }

    /** 失败，无数据 */
    public static <T> Result<T> error() {
        return new Result<>(FAIL, "操作失败", null);
    }

    /** 失败，自定义提示信息 */
    public static <T> Result<T> error(String msg) {
        return new Result<>(FAIL, msg, null);
    }
}