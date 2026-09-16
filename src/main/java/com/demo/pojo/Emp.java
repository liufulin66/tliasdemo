package com.demo.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 对应数据库 tlias 库中的 emp 表（员工）
 */
@Data
public class Emp {
    private Integer id;           // 员工 id，主键自增
    private String username;      // 登录用户名
    private String password;      // 密码
    private String name;          // 姓名
    private Integer gender;       // 性别：1男 2女
    private String phone;         // 手机号
    private String image;         // 头像 URL
    private String job;           // 职位
    private LocalDate entryDate;  // 入职日期
    private Integer deptId;       // 所属部门 id（关联 dept.id）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 非表字段：联表查询时携带的部门名称
    private String deptName;

    // 非表字段：新增员工时随请求一起提交的工作经历，落库到 emp_expr 表
    private List<EmpExpr> exprList;
}