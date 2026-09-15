package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import com.demo.pojo.Result;
import com.demo.pojo.EmpExpr;
import com.demo.service.EmpExprService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpExprController {

    @Autowired
    private EmpExprService empExprService;

    // 查询某员工的所有经历
    @GetMapping("/{empId}/exprs")
    public Result list(@PathVariable Integer empId) {
        log.info("查询员工 {} 的经历", empId);
        return Result.success(empExprService.findByEmpId(empId));
    }

    // 新增某员工的一条经历
    @PostMapping("/{empId}/exprs")
    public Result add(@PathVariable Integer empId, @RequestBody EmpExpr empExpr) {
        log.info("新增员工 {} 的经历: {}", empId, empExpr);
        empExpr.setEmpId(empId);
        empExprService.add(empExpr);
        return Result.success();
    }

    // 根据经历 id 查询
    @GetMapping("/exprs/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询经历: {}", id);
        return Result.success(empExprService.getById(id));
    }

    // 根据经历 id 修改
    @PutMapping("/exprs/{id}")
    public Result update(@PathVariable Integer id, @RequestBody EmpExpr empExpr) {
        log.info("修改经历: {}", empExpr);
        empExpr.setId(id);
        empExprService.update(empExpr);
        return Result.success();
    }

    // 根据经历 id 删除
    @DeleteMapping("/exprs/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除经历: {}", id);
        empExprService.deleteById(id);
        return Result.success();
    }
}