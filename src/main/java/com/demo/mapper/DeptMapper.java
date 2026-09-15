package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.pojo.Dept;

@Mapper 
public interface DeptMapper {
//查询所有部门数据
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);


    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{creatTime}, #{updataTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
