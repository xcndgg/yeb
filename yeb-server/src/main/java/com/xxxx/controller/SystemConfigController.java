package com.xxxx.controller;

import com.xxxx.pojo.Menu;
import com.xxxx.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/3 22:06
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController
{
    @Autowired
    private IMenuService menuService;
    @ApiOperation(value="通过用户id获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByHrId(){
        return menuService.getMenusByAdminId();
    }

}
