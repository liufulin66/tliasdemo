package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.pojo.Emp;
import com.demo.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {

    // 关联 dept 表查询，带回部门名称
    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    List<Emp> findAll();

    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id where e.id = #{id}")
    Emp getById(Integer id);

    @Insert("insert into emp(username, password, name, gender, phone, image, job, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{password}, #{name}, #{gender}, #{phone}, #{image}, #{job}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Update("update emp set username = #{username}, password = #{password}, name = #{name}, gender = #{gender}, phone = #{phone}, " +
            "image = #{image}, job = #{job}, entry_date = #{entryDate}, dept_id = #{deptId}, update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    @Delete("delete from emp where id = #{id}")
    void deleteById(Integer id);

    @Select("select count(*) from emp where dept_id = #{deptId}")
    int countByDeptId(Integer deptId);

    //条件分页查询（动态 SQL 写在 resources/mapper/EmpMapper.xml 中，分页由 PageHelper 控制）
    List<Emp> list(EmpQueryParam param);
}