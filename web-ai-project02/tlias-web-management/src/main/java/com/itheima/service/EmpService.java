package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
   //分页查询的方法 page页码  pagesize每页展示的记录数
    /*PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                         LocalDate begin, LocalDate end);*/

    PageResult<Emp> page(EmpQueryParam empQueryParam);

   //新增员工信息
    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    //查询回显
    Emp getInfo(Integer id);
}
