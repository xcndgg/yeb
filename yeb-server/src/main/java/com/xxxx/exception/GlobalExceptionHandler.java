package com.xxxx.exception;

import com.xxxx.pojo.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/5 22:36
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean mySQLException(Exception e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("操作异常！");
        }
        return RespBean.error("数据库异常，操作失败");
    }
}
