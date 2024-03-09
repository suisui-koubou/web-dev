package com.itheima.tlias.mapper;

import com.itheima.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    /**
//     * 查询总记录数
//     */
//    @Select("select count(*) from emp")
//    public Long count();
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    public List<Emp> pagelist(Integer start, Integer pageSize);

    /**
     * Pagehelper的查询
     * @return
     */
    // @Select("select * from emp") // 由于 EmpMapper.xml 配置了SQL语句的映射，所以此处注解是不需要的。
    public List<Emp> pagelist(String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            " values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);


    /**
     * 根据部门数据删除员工
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteEmpByDeptId(Integer deptId);
}
