package com.itheima.mapper;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//员工信息
@Mapper
public interface EmpMapper {



   //-----------------------------原始分页查询记录方式---------------------------------
    /*//查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    public Long count();

    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id " +
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/

    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id " +"order by e.update_time desc ")

//    public List<Emp> list(String name, Integer gender,
//                          LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键--主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "    VALUES(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);


    /**
     * 根据ID查询员工详细信息
     */
    Emp getById(Integer id);

   /* 统计员工职位人数*/
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map> countEmpGenderData();
}
