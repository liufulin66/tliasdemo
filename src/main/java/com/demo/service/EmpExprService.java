package com.demo.service;

import java.util.List;

import com.demo.pojo.EmpExpr;

public interface EmpExprService {

    List<EmpExpr> findByEmpId(Integer empId);

    EmpExpr getById(Integer id);

    void add(EmpExpr empExpr);

    void update(EmpExpr empExpr);

    void deleteById(Integer id);
}