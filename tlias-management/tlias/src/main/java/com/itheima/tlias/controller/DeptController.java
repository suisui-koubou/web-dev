package com.itheima.tlias.controller;

import com.itheima.tlias.pojo.Dept;
import com.itheima.tlias.pojo.Result;
import com.itheima.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    // 注释 @Slf4j 能够起到下面获取 Logger的作用。
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);


    // 注入Service对象(IOC容器?)
    @Autowired
    private DeptService deptService;

    // 根据API手册 1.4根据ID查询部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id获取部门");
        Dept dept = deptService.getById(id);
        if (dept == null){
            return Result.error("empty");
        }
        return Result.success(dept);
    }

    // 根据API手册 1.5 修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门");
        deptService.update(dept);
        return Result.success();
    }

    // 根据API手册 1.1部门列表查询实现
    // @RequestMapping(value = "/depts", method = RequestMethod.GET) // 指定请求方式 GET
    @GetMapping // 效果同限定方法为RequestMethod.GET
    public Result list(){
        log.info("查询全部部门数据");
        // 调用Service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    // 删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    // 新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

}
