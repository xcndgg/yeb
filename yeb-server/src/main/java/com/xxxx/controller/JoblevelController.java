package com.xxxx.controller;


import com.xxxx.pojo.Joblevel;
import com.xxxx.pojo.Position;
import com.xxxx.pojo.RespBean;
import com.xxxx.service.IJoblevelService;
import com.xxxx.service.IPositionService;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    @Resource
    private IJoblevelService joblevelService;
    @ApiOperation(value="获取所有职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllJobleLevel(){
        return joblevelService.list();
    }
    @ApiOperation(value="添加职称信息")
    @PostMapping("/")
    public RespBean addJobelLevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.success("添加成功");
        }
        else{
            return RespBean.error("添加失败");
        }

    }
    @ApiOperation(value="更新职称信息")
    @PutMapping("/")
    public RespBean updateJobleLevel(@RequestBody Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return RespBean.success("更新成功");
        }
        else{
            return RespBean.error("更新失败");
        }
    }
    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosion(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.success("删除成功");
        }
        else{
            return RespBean.error("删除失败");
        }
    }
    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deleJObelLevels(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        else{
            return RespBean.error("批量删除失败");
        }
    }
}
