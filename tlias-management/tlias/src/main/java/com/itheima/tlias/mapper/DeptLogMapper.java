package com.itheima.tlias.mapper;

import com.itheima.tlias.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    // dept_log(create_time,description) 是数据库表列名
    // values(#{createTime},#{description}) 是传入POJO数据对象的成员属性。
    @Insert("insert into dept_log(create_time,description) values(#{createTime},#{description})")
    void insert(DeptLog log);
}
