package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.mapper.EmpMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.PageResult;

public interface EmpService {
    

    List<Emp> findAll();

    Emp getById(Integer id);

    void add(Emp emp);

    void update(Emp emp);

    void deleteById(Integer id);

    /** 统计某部门下的员工数量（用于删除部门前的校验） */
    int countByDeptId(Integer deptId);

    PageResult<Emp> page(Integer page, Integer pageSize);
}