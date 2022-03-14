package com.xxxx.config.security;
import com.xxxx.config.security.component.*;
import com.xxxx.pojo.Admin;
import com.xxxx.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.annotation.Resource;


/**
 * Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    @Lazy
    private IAdminService adminService;
    @Resource
    private RestAuthorizationEntryPoint restAuthenticationEntryPoint;
    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Resource
    private CustomFilter customFilter;
    @Resource
    private CustomUrlDecisionManager customUrlDecisionManager;
    /**
     * 放行静态资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/login","/login",
                 "/logout",
                 "/css/**",
                 "/js/**",
                 "/index.html",
                 "/img/**",
                "/fonts/**",
                 "/favicon.ico",
                 "/doc.html",
                 "/webjars/**",
                "/swagger-resources/**",
                 "/v2/api-docs/**",
                 "/captcha");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用JWT，不需要csrf
        http.csrf()
                .disable()
                //基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("login")
                .permitAll()
                //所有请求都要求认证
                .anyRequest()
                .authenticated()
                 //动态权限配置
                .withObjectPostProcessor(new
                                  ObjectPostProcessor<FilterSecurityInterceptor>() {
                                      @Override
                                      public <O extends FilterSecurityInterceptor> O postProcess(O
                                                                                                         object) {

                                          object.setAccessDecisionManager(customUrlDecisionManager);
                                          object.setSecurityMetadataSource(customFilter);
                                          return object;
                                      }
                                  })
                .and()
                //禁用缓存
                .headers()
                .cacheControl();
        //添加jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                //未登录，自定义的401处理
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                //权限不足，自定义的403处理
                .accessDeniedHandler(restfulAccessDeniedHandler);
    }

    /**
     * 配置自定义的登录逻辑生效
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (null != admin) {
                admin.setRoles(adminService.getRoles(admin.getId()));
                return admin;
            }
            throw new UsernameNotFoundException("用户名或密码不正确！");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthencationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthencationTokenFilter();
    }
}
