package com.xxxx.controller;

/**
 * @author xcdgg
 * @description
 * @date 2022/1/28 20:23
 */

import com.xxxx.pojo.AdminLoginParam;
import com.xxxx.pojo.Admin;
import com.xxxx.pojo.RespBean;
import com.xxxx.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Api(tags="loginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;


    @ApiOperation("登录返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam,HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),request);
    }

    @ApiOperation("获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation("退出登录")
    @RequestMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }


}
