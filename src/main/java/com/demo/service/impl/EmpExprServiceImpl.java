package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.EmpExprMapper;
import com.demo.pojo.EmpExpr;
import com.demo.service.EmpExprService;

@Service
public class EmpExprServiceImpl implements EmpExprService {

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public List<EmpExpr> findByEmpId(Integer empId) {
        return empExprMapper.findByEmpId(empId);
    }

    @Override
    public EmpExpr getById(Integer id) {
        return empExprMapper.getById(id);
    }

    @Override
    public void add(EmpExpr empExpr) {
        empExpr.setCreateTime(LocalDateTime.now());
        empExpr.setUpdateTime(LocalDateTime.now());
        empExprMapper.insert(empExpr);
    }

    @Override
    public void update(EmpExpr empExpr) {
        empExpr.setUpdateTime(LocalDateTime.now());
        empExprMapper.update(empExpr);
    }

    @Override
    public void deleteById(Integer id) {
        empExprMapper.deleteById(id);
    }
}