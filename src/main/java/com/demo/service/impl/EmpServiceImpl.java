package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.mapper.EmpMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.EmpQueryParam;
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
    public  PageResult<Emp> page(EmpQueryParam param) {
        //开启分页：紧跟其后的第一个查询会被自动改写为分页 SQL
        PageHelper.startPage(param.getPage(), param.getPageSize());

        //条件分页查询当页数据（条件为空时该条件不会被拼进 SQL）
        List<Emp> rows = empMapper.list(param);

        //总记录数由 PageHelper 一并查出，从查询结果中获取
        long total = new PageInfo<>(rows).getTotal();

        return new PageResult<Emp>(total, rows);
    }
}