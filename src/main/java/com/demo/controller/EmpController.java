package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import com.demo.pojo.Result;
import com.demo.pojo.Emp;
import com.demo.service.EmpService;

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
public class EmpController {

    @Autowired
    private EmpService empService;

    // 查询全部员工
    @GetMapping
    public Result list() {
        log.info("查询全部员工的数据");
        return Result.success(empService.findAll());
    }

    // 根据 id 查询员工
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工: {}", id);
        return Result.success(empService.getById(id));
    }

    // 新增员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.add(emp);
        return Result.success();
    }

    // 根据 id 修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.update(emp);
        return Result.success();
    }

    // 根据 id 删除员工
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除员工: {}", id);
        empService.deleteById(id);
        return Result.success();
    }
}