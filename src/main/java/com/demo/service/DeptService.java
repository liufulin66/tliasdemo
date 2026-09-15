package com.demo.service;

import java.util.List;

import com.demo.pojo.Dept;

public interface DeptService {

    /** 查询所有部门 */
    List<Dept> findAll();

    boolean deleteById(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);

}
