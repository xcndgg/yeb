package com.xxxx;

import com.xxxx.mapper.MenuMapper;
import com.xxxx.pojo.Menu;
import com.xxxx.service.IMenuService;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/3 21:34
 */

@SpringBootTest(classes = YebApplication.class)
public class MyTest {
    @Autowired
    IMenuService menuService;
    @Test
    public void show(){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String code = bc.encode("123");
        System.out.println(code);
        System.out.println(menuService.getMenusByAdminId());
    }
}
