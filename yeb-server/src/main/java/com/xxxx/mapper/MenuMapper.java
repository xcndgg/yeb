package com.xxxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.Menu;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */
@Component
public interface MenuMapper extends BaseMapper<Menu> {
  /**
   * @author:xcdgg
   * @description:通过id查询菜单列表
   * @return:
   * @date:21:27 2022/2/3
   */
  List<Menu> getMenusByAdminId(Integer id);
  /**
   * @author:xcdgg
   * @description:根据角色获取菜单列表
   * @return:
   * @date:10:26 2022/2/5
   */
  List<Menu> getAllMenusWithRole();

  List<Menu> getAllMenus();
}
