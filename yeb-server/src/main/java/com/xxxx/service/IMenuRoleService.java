package com.xxxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.MenuRole;
import com.xxxx.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface IMenuRoleService extends IService<MenuRole> {
    public RespBean updateMenuRole(Integer id,Integer[] mids);
}
