package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);
    /**
     * 添加部门
     * @param dep
     */
    void addDep(Department dep);

    /**
     * 删除部门
     * @param dep
     */
    void deleteDep(Department dep);
}
