package com.hxzy.controller;

import com.hxzy.dto.EmployeeSearchDTO;
import com.hxzy.entity.Employee;
import com.hxzy.service.EmployeeService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@RequestMapping(value = "/api/employee")
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(EmployeeSearchDTO employeeSearchDTO){
        return this.employeeService.search(employeeSearchDTO);
    }

    @ResponseBody
    @PostMapping(value="/insert")
    public Result insert(@Validated Employee employee){
        Result result=null;
        employee.setId(0);
        employee.setState(1);
        employee.setUpdater("admin");
        employee.setUpdatime(new Date());

        boolean boo=this.employeeService.insert(employee);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value="/update")
    public Result update(@Validated Employee employee){
        Result result=null;
        employee.setUpdater("admin");
        employee.setUpdatime(new Date());

        boolean boo=this.employeeService.updateByPrimaryKeySelective(employee);
        if(boo){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else {
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
}
