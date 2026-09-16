package com.demo.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 员工条件分页查询参数（分页参数 + 查询条件封装为一个对象）
 */
@Data
public class EmpQueryParam {
    private Integer page = 1;        // 页码，默认第 1 页
    private Integer pageSize = 10;   // 每页条数，默认 10

    private String name;             // 姓名，模糊匹配
    private Integer gender;          // 性别：1男 2女

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;         // 入职日期范围-开始

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;           // 入职日期范围-结束
}
