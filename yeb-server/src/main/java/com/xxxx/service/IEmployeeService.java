package com.xxxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.Employee;
import com.xxxx.pojo.RespBean;
import com.xxxx.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee employee);

    List<Employee> getEmployee(Integer id);
}
