package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.mapper.EmpExprMapper;
import com.demo.mapper.EmpMapper;
import com.demo.pojo.Emp;
import com.demo.pojo.EmpExpr;
import com.demo.pojo.EmpQueryParam;
import com.demo.pojo.PageResult;
import com.demo.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private  EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    @Transactional
    public void add(Emp emp) {
        LocalDateTime now = LocalDateTime.now();
        emp.setCreateTime(now);
        emp.setUpdateTime(now);
        // 先插入员工，自增主键由 @Options 回填到 emp.id
        empMapper.insert(emp);

        // 再批量插入工作经历：补全外键 empId 与时间字段
        List<EmpExpr> exprList = emp.getExprList();
        if (exprList != null && !exprList.isEmpty()) {
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
                expr.setCreateTime(now);
                expr.setUpdateTime(now);
            }
            empExprMapper.insertBatch(exprList);
        }
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