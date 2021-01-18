package com.hxzy.service.impl;

import com.hxzy.entity.Employee;
import com.hxzy.mapper.EmployeeMapper;
import com.hxzy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl  extends BaseServiceImpl<Employee,Integer>  implements EmployeeService {

    private EmployeeMapper  employeeMapper;

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
        super.setMyBatisBaseDao(employeeMapper);
    }


}
