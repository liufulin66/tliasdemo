package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.EmpMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.PageResult;
import com.demo.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private  EmpMapper empMapper;

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public void deleteById(Integer id) {
        empMapper.deleteById(id);
    }

    @Override
    public int countByDeptId(Integer deptId) {
        return empMapper.countByDeptId(deptId);
    }

    @Override 
    public  PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用mapper接口查询总记录数
        long total = empMapper.count();

        //查询分页结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        return new PageResult<Emp>(total, rows);
    }
}