package com.demo.service;

import java.util.List;

import com.demo.pojo.Emp;

public interface EmpService {

    List<Emp> findAll();

    Emp getById(Integer id);

    void add(Emp emp);

    void update(Emp emp);

    void deleteById(Integer id);

    /** 统计某部门下的员工数量（用于删除部门前的校验） */
    int countByDeptId(Integer deptId);
}