package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    //----------------------原始分页查询---------------------
   /* @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
*/

        /*   //调用Mapper接口查询总记录数
           Long total= empMapper.count();

        //调用mapper接口查询结果列表
        Integer start=(page-1)*pageSize;
        List<Emp> rows=empMapper.list(start,pageSize);

        //封装结果PageResult


        return new PageResult<Emp>(total,rows);*/

    //基于PageHelper分页插件分页查询
   /* @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                                LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Emp> empList=empMapper.list(name, gender,
                begin, end);

        //解析查询结果，封装
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //执行查询
        List<Emp> empList=empMapper.list(empQueryParam);

        //解析查询结果，封装
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());

    }

    //新增员工信息
    @Transactional  //事务管理
    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);


        //保存员工工作信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){

            //遍历集合，为empId赋值
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    //查询回显
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }
}
