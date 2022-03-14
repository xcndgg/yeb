package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * @author:xcdgg
     * @description:获取所有员工分页
     * @param page
     * @param employee
     * @param beginDateScope
     * @return:com.baomidou.mybatisplus.core.metadata.IPage<com.xxxx.pojo.Employee>
     * @date:20:01 2022/2/7
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page,@Param("employee") Employee employee
            ,@Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);
}
