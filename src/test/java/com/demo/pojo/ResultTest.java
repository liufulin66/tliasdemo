package com.demo.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Result 类基础单元测试
 * 只测静态工厂方法的行为，不依赖 Spring、不连数据库
 */
class ResultTest {

    @Test
    void success_无数据_返回成功码和默认提示() {
        Result<Void> result = Result.success();

        assertEquals(Result.SUCCESS, result.getCode(), "success() 的 code 应为 1");
        assertEquals("操作成功", result.getMsg());
        assertNull(result.getData(), "success() 不带数据，data 应为 null");
    }

    @Test
    void success_带数据_返回成功码和传入数据() {
        String data = "hello";
        Result<String> result = Result.success(data);

        assertEquals(Result.SUCCESS, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertEquals(data, result.getData(), "data 应等于传入的参数");
    }

    @Test
    void error_无参数_返回失败码和默认提示() {
        Result<Void> result = Result.error();

        assertEquals(Result.FAIL, result.getCode(), "error() 的 code 应为 0");
        assertEquals("操作失败", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void error_带提示信息_使用自定义消息() {
        String msg = "请求参数不合法";
        Result<Void> result = Result.error(msg);

        assertEquals(Result.FAIL, result.getCode());
        assertEquals(msg, result.getMsg(), "应使用传入的自定义提示信息");
        assertNull(result.getData());
    }
}