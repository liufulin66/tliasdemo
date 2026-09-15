package com.demo.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 对应数据库 tlias 库中的 emp_expr 表（员工经历）
 */
@Data
public class EmpExpr {
    private Integer id;           // 经历 id，主键自增
    private Integer empId;        // 员工 id（关联 emp.id）
    private String company;       // 公司/单位
    private String position;      // 职位
    private LocalDate startDate;  // 开始日期
    private LocalDate endDate;    // 结束日期
    private String description;   // 经历描述
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}