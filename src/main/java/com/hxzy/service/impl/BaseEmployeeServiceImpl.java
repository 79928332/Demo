package com.hxzy.service.impl;

import com.hxzy.entity.BaseEmployee;
import com.hxzy.mapper.BaseEmployeeMapper;
import com.hxzy.service.BaseEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmployeeServiceImpl  extends BaseServiceImpl<BaseEmployee,Integer>  implements BaseEmployeeService {

    private BaseEmployeeMapper  baseEmployeeMapper;

    @Autowired
    public void setBaseEmployeeMapper(BaseEmployeeMapper baseEmployeeMapper) {
        this.baseEmployeeMapper = baseEmployeeMapper;
        super.setMyBatisBaseDao(baseEmployeeMapper);
    }


}
