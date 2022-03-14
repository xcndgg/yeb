package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.mapper.MenuMapper;
import com.xxxx.pojo.Admin;
import com.xxxx.pojo.Menu;
import com.xxxx.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_"+adminId);
        //如果list为空就去数据库获取
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(adminId);
            //将数据存入redis
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return null;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
