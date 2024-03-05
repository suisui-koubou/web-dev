package com.itheima.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tlias.mapper.EmpMapper;
import com.itheima.tlias.pojo.Emp;
import com.itheima.tlias.pojo.PageBean;
import com.itheima.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service // 交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {


    // 调用Mapper接口
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean pagelist(Integer pageNo, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        // 1. 获取总记录数
//        Long cnt = empMapper.count();
//        // 2. 获取分页查询列表
//        Integer start = (pageNo - 1) * pageSize;
//        List<Emp> empList = empMapper.pagelist(start, pageSize);
//        // 3.封装PageBean
//        PageBean pageBean = new PageBean(cnt, empList);
//        return pageBean;
        // -----------------------
        // 使用 Pagehelper
        // 1. 设置分页参数
        PageHelper.startPage(pageNo, pageSize);
        // 2. 执行查询
        List<Emp> empList = empMapper.pagelist(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList; // 或者在Mapper直接返回Page类, 因为PageHelper实际提供的是Page类。
        // 3. 封装PageBean对象
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
