package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.config.security.component.JwtTokenUtil;
import com.xxxx.mapper.AdminMapper;
import com.xxxx.mapper.AdminRoleMapper;
import com.xxxx.mapper.RoleMapper;
import com.xxxx.pojo.Admin;
import com.xxxx.pojo.AdminRole;
import com.xxxx.pojo.RespBean;
import com.xxxx.pojo.Role;
import com.xxxx.service.IAdminService;
import com.xxxx.utils.AdminUtils;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private AdminRoleMapper adminRoleMapper;


    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
//        String captcha = (String)request.getSession().getAttribute("captcha");
//        if(StringUtils.isBlank(captcha)||!captcha.equals(captcha)){
//            return RespBean.error("验证码填写错误");
//        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确!");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员!");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }
    /**
     * @author:xcdgg
     * @description:更具用户名获取Id
     * @param username
     * @return:com.xxxx.server.pojo.Admin
     * @date:20:57 2022/1/28
     */


    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public List<Role> getRoles(Integer id) {
        return roleMapper.getRoles(id);
    }

    @Override
    public List<Admin> getAllAdminds(String keyWords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keyWords);
    }

    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId,rids);
        if(rids.length==result){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
