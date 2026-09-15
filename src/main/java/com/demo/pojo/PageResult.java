package com.demo.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
分页结果封装类 
*/
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
