package com.xxxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.Department;
import com.xxxx.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    RespBean addDep(Department dep);

    RespBean deleteDep(Integer id);
}
