package com.xxxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.Admin;
import com.xxxx.pojo.RespBean;
import com.xxxx.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface IAdminService extends IService<Admin> {
    /**
     * @author:xcdgg
     * @description:登录成功后返回token
     * @param username,password
     * @return:
     * @date:20:39 2022/1/28
     */
    RespBean login(String username, String password, HttpServletRequest request);
    /**
     * @author:xcdgg
     * @description:根据用户名获取用户
     * @param username
     * @return:
     * @date:20:40 2022/1/28
     */
    Admin getAdminByUserName(String username);


    List<Role> getRoles(Integer adminId);

    List<Admin> getAllAdminds(String keyWords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}