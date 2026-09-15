package com.demo.pojo;

import java.util.List;

import lombok.Data;

/*
分页结果封装类 
*/
@Data 
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
