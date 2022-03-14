package com.xxxx.controller;



import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.mapper.EmployeeMapper;
import com.xxxx.mapper.MailLogMapper;
import com.xxxx.pojo.*;
import com.xxxx.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xcdgg
 * @since 2022-01-28
 */


@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;

    @Resource
    private IPoliticsStatusService politicsStatusService;

    @Resource
    private IJoblevelService joblevelService;

    @Resource
    private INationService nationService;

    @Resource
    private IPositionService positionService;

    @Resource
    private IDepartmentService departmentService;


    @ApiOperation(value = "获取所有员工（分页）")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage
            , @RequestParam(defaultValue = "10") Integer size
            , Employee employee, LocalDate[] beginDateScope){
        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有名族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有的职位")
    @GetMapping("/positions")
    public List<Position> getAllPosition(){
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("maxWorkID")
    public RespBean maxWorkID(){
        return employeeService.maxWorkID();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return RespBean.success("更新员工信息成功!");
        }
        return RespBean.error("更新员工信息失败!");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if(employeeService.removeById(id)){
            return RespBean.success("删除员工成功");
        }
        return RespBean.error("删除员工失败！");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> list = employeeService.getEmployee(null);

        for (Employee employee : list) {
            System.out.println();
            System.out.println("emp："+employee.getPoliticsStatus());
            System.out.println();
        }

        ExportParams params = new ExportParams("员工表","员工表", ExcelType.HSSF); // title：导出的文件名 sheetName：左下角sheet的名字 excell类型
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out = null;
        try {
            // 用流的形式传输
            response.setHeader("content-type","application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file){
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Position> positionList = positionService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                // 民族id
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                // 政治面貌id
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                // 部门id
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(employee.getDepartment().getName()))).getId());
                // 职称id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                // 职位id
                employee.setPosId(positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if(employeeService.saveBatch(list)){
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败！");
    }

}
