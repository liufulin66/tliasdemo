package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pojo.Result;
import com.demo.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import com.demo.pojo.Dept;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired 
    private DeptService deptService;

//查询全部部门的数据
    @GetMapping
    public Result list(){
        log.info("查询全部部门的数据");
        List<Dept> deptlist= deptService.findAll();
        return  Result.success(deptlist);
    }
//根据id删除部分数据
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据id删除部门: {}", id);
        boolean deleted = deptService.deleteById(id);
        if (deleted) {
            return Result.success();
        }
        return Result.error("该部门下仍存在员工，无法删除");
    }
//添加部门数据
    @PostMapping
    public Result add(@RequestBody Dept dept){//接收json格式的数据@RequestBody
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }
//根据ID查询部门信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
//根据id修改部门信息
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }








}