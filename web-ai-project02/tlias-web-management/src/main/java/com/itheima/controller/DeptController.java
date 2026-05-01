package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {


//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping("/depts")
    public Result list(){
        /*System.out.println("查询全部部门数据");*/
        log.debug("查询全部部门数据");
        List<Dept> deptList = deptService.findall();
        return Result.success(deptList);
    }

    @GetMapping("/depts")
    public Result delete( Integer id){
        ;
        /*System.out.println("删除部门数据："+id);*/
        log.info("删除部门数据：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        /*System.out.println("添加部门："+dept);*/
        log.info("添加部门：{}",dept);
        deptService.add(dept);
        return Result.success();

    }

}
