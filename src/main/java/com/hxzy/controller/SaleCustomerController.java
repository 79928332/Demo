package com.hxzy.controller;
import com.hxzy.dto.SaleCustomerDTO;
import com.hxzy.service.SaleCustomerService;
import com.hxzy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/api/salecu")
@Controller
public class SaleCustomerController {
    @Autowired
    private SaleCustomerService saleCustomerService;

    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(SaleCustomerDTO saleCustomerDTO){
        return  this.saleCustomerService.search(saleCustomerDTO);
    }
}
