package com.xxxx.controller;


import com.xxxx.pojo.Admin;
import com.xxxx.pojo.RespBean;
import com.xxxx.pojo.Role;
import com.xxxx.service.IAdminRoleService;
import com.xxxx.service.IAdminService;
import com.xxxx.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@RequestMapping("/system/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;
    @Resource
    private IRoleService roleService;
    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keyWords){
        return adminService.getAllAdminds(keyWords);
    }
    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if(adminService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
    @ApiOperation(value="获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }
    @ApiOperation(value = "更行操作员角色")
    @PutMapping("/roles")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

}
