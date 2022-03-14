package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.MenuRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(Integer rid, Integer[] mids);
}
