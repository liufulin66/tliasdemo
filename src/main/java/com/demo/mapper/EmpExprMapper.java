package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    @Select("select * from emp_expr where emp_id = #{empId} order by start_date desc")
    List<EmpExpr> findByEmpId(Integer empId);

    @Select("select * from emp_expr where id = #{id}")
    EmpExpr getById(Integer id);

    @Insert("insert into emp_expr(emp_id, company, position, start_date, end_date, description, create_time, update_time) " +
            "values(#{empId}, #{company}, #{position}, #{startDate}, #{endDate}, #{description}, #{createTime}, #{updateTime})")
    void insert(EmpExpr empExpr);

    @Update("update emp_expr set company = #{company}, position = #{position}, start_date = #{startDate}, " +
            "end_date = #{endDate}, description = #{description}, update_time = #{updateTime} where id = #{id}")
    void update(EmpExpr empExpr);

    @Delete("delete from emp_expr where id = #{id}")
    void deleteById(Integer id);
}