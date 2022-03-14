package com.xxxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenusByAdminId();


    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();

    List<Menu> getAllMenusWithRole();
}
