package com.itheima.tlias.controller;

import com.itheima.tlias.anno.TliasLog;
import com.itheima.tlias.pojo.Emp;
import com.itheima.tlias.pojo.PageBean;
import com.itheima.tlias.pojo.Result;
import com.itheima.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService; // 动态连接Service层的接口

    @GetMapping
    public Result pagelist(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String name, Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
                           ){
        /* 不使用注解@RequestParm(defaultValue)的情况下如何设置默认值。
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;
        */
        log.info("Controller --> 分页查询 参数: {}, {}, {}, {}, {}, {}",
                page, pageSize, name, gender, begin, end);  // 需要 Slf4j
        PageBean pageBean = empService.pagelist(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }


    @TliasLog
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @TliasLog
    @PostMapping
    public Result save(@RequestBody Emp emp){    // Emp是封装好的员工数据
        log.info("新增员工, emp:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 查询员工信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @TliasLog
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息: {}", emp);
        empService.update(emp);
        return Result.success();
    }

}
