package com.xxxx.controller;


import com.xxxx.pojo.Department;
import com.xxxx.pojo.RespBean;
import com.xxxx.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */


@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Resource
    private IDepartmentService departmentService;
    @ApiOperation(value="获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }

}
