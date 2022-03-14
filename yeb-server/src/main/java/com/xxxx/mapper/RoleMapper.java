package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @author:xcdgg
     * @description:根据id查角色列表
     * @return:
     * @date:11:00 2022/1/29
     */
    List<Role> getRoles(Integer adminId);
}
