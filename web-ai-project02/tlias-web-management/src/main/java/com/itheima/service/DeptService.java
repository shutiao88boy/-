package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findall();

   //根据id删除部门
    void deleteById(Integer id);

    //添加部门
    void add(Dept dept);
}
