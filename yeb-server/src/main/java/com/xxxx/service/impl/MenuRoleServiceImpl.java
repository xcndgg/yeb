package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.mapper.MenuRoleMapper;
import com.xxxx.pojo.MenuRole;
import com.xxxx.pojo.RespBean;
import com.xxxx.service.IMenuRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Resource
    private MenuRoleMapper menuRoleMapper;
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null||mids.length == 0){
            return RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        if(result == mids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
