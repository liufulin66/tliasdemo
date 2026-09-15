package com.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 对应数据库 tlias 库中的 dept 表
 * 字段与表结构一一对应，createTime/updateTime 由数据库自动维护
 */
@Data
public class Dept {
    private Integer id;           // 部门 id，主键自增
    private String name;          // 部门名称（非空）
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}