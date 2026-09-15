package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.DeptMapper;
import com.demo.pojo.Dept;
import com.demo.service.DeptService;
import com.demo.service.EmpService;


@Service 
public class DeptServiceImpl implements DeptService{
    
    @Autowired 
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;

    @Override 
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        // 删除前校验：部门下仍有员工则禁止删除
        if (empService.countByDeptId(id) > 0) {
            return false;
        }
        deptMapper.deleteById(id);
        return true;
    }

    @Override
    public void add(Dept dept) {
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper接口方法
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //补全基本属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    
}
