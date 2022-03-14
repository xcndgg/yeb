package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.AdminRole;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId,@Param("rids") Integer[] rids);
}
