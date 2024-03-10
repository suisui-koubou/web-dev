package com.itheima.tlias.service.impl;

import com.itheima.tlias.aop.MyFirstAnnotation;
import com.itheima.tlias.mapper.DeptLogMapper;
import com.itheima.tlias.mapper.DeptMapper;
import com.itheima.tlias.mapper.EmpMapper;
import com.itheima.tlias.pojo.Dept;
import com.itheima.tlias.pojo.DeptLog;
import com.itheima.tlias.service.DeptLogService;
import com.itheima.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service // 交给 IOC 容器管理
public class DeptServiceImpl implements DeptService {

    // 注入 Mapper对象，以便Service层调用Mapper方法。
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    // -----------------------------------------------------


    @MyFirstAnnotation // 我创建的注解
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception{
        try{
            deptMapper.deleteById(id);       //根据ID删除部门数据
            empMapper.deleteEmpByDeptId(id); //根据部门ID删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了 解散部门的操作，此次解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

}
