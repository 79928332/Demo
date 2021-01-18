package com.hxzy.service.impl;

import com.hxzy.entity.SaleCustomer;
import com.hxzy.mapper.SaleCustomerMapper;
import com.hxzy.service.SaleCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleCustomerServiceImpl  extends BaseServiceImpl<SaleCustomer,Integer>  implements SaleCustomerService {

    private SaleCustomerMapper  saleCustomerMapper;

    @Autowired
    public void setSaleCustomerMapper(SaleCustomerMapper saleCustomerMapper) {
        this.saleCustomerMapper = saleCustomerMapper;
        super.setMyBatisBaseDao(saleCustomerMapper);
    }


}
