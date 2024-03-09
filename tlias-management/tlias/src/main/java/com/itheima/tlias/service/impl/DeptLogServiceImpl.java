package com.itheima.tlias.service.impl;

import com.itheima.tlias.mapper.DeptLogMapper;
import com.itheima.tlias.pojo.DeptLog;
import com.itheima.tlias.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Service层组件
public class DeptLogServiceImpl implements DeptLogService {
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional // 默认是加入事务。
    @Override
    public void insert(DeptLog deptLog){
        deptLogMapper.insert(deptLog);
    }
}
