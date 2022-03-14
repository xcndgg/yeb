package com.xxxx.utils;

import com.xxxx.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/6 23:37
 */
public class AdminUtils {
    /**
     * @author:xcdgg
     * @description:获取当前登录员
     * @return:
     * @date:23:37 2022/2/6
     */
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
