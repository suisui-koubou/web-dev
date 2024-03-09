package com.itheima.tlias.service;

import com.itheima.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {
    // 查询全部部门数据 Interface(实现在 DeptServiceImpl)
    List<Dept> list();


    void delete(Integer id) throws Exception;

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
