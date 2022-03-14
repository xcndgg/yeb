package com.xxxx.controller;


import com.xxxx.pojo.Position;
import com.xxxx.pojo.RespBean;
import com.xxxx.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */


@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Resource
    private IPositionService positionService;
    @ApiOperation(value="获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllposition(){
        return positionService.list();
    }
    @ApiOperation(value="添加职位信息")
    @PostMapping("/")
    public RespBean addPosion(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("添加成功");
        }
        else{
            return RespBean.error("添加失败");
        }

    }
    @ApiOperation(value="更新职位信息")
    @PutMapping("/")
    public RespBean updatePosion(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.success("更新成功");
        }
        else{
            return RespBean.error("更新失败");
        }
    }
    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosion(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        else{
            return RespBean.error("删除失败");
        }
    }
    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean delePosionByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        else{
            return RespBean.error("批量删除失败");
        }
    }
}
