package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.dto.PageSearch;
import com.hxzy.dto.PurchaseOrderSearchDTO;
import com.hxzy.entity.*;
import com.hxzy.mapper.PurchaseOrderMapper;
import com.hxzy.service.*;
import com.hxzy.util.DeptLocalThreadUtil;
import com.hxzy.util.PageInfoEntityToEntityVOUtil;
import com.hxzy.vo.PurchaseOrderVO;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl  extends BaseServiceImpl<PurchaseOrder,Integer>  implements PurchaseOrderService {

    private PurchaseOrderMapper  purchaseOrderMapper;

    @Autowired
    private BaseGoodsService baseGoodsService;
    @Autowired
    private PurchaseSupplierService purchaseSupplierService;
    @Autowired
    private  BaseRepoService baseRepoService;
    @Autowired
    private  EmployeeService employeeService;


    @Autowired
    public void setPurchaseOrderMapper(PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderMapper = purchaseOrderMapper;
        super.setMyBatisBaseDao(purchaseOrderMapper);
    }

    @Override
    public Result search(PageSearch pageSearch) {
        PageHelper.startPage(pageSearch.getPage(), pageSearch.getSize());
        //分页
        List<PurchaseOrder> arr = this.purchaseOrderMapper.search(pageSearch);
        List<PurchaseOrderVO>  purchaseOrderVOList=new ArrayList<PurchaseOrderVO>();
        for (PurchaseOrder p:arr){
            PurchaseOrderVO  purchaseOrderVO=new PurchaseOrderVO();
            BeanUtils.copyProperties(p,purchaseOrderVO);
            PurchaseSupplier purchaseSupplier=this.purchaseSupplierService.selectByPrimaryKey(p.getSupplierId());
            purchaseOrderVO.setPusuName(purchaseSupplier.getName());
            BaseGoods baseGoods=this.baseGoodsService.selectByPrimaryKey(p.getGoodsId());
            purchaseOrderVO.setGoodsname(baseGoods.getName());
            BaseRepo baseRepo=this.baseRepoService.selectByPrimaryKey(p.getRepoId());
            purchaseOrderVO.setBarName(baseRepo.getName());
            Employee employee=this.employeeService.selectByPrimaryKey(p.getEmployeeId());
            purchaseOrderVO.setEmployName(employee.getName());
            purchaseOrderVOList.add(purchaseOrderVO);

        }
        Page<PurchaseOrder> page=( Page<PurchaseOrder>)arr;
        PageInfo<PurchaseOrderVO> pageInfoPurchaseOrder= PageInfoEntityToEntityVOUtil.convertPageInfo(PurchaseOrderVO.class,page,purchaseOrderVOList);
        return Result.createResult_Data(ResultCode.OK,pageInfoPurchaseOrder );

    }





    @Override
    public Result existsValue(PurchaseOrderSearchDTO purchaseOrderSearchDTO) {
        if (StringUtils.isNotBlank(purchaseOrderSearchDTO.getOrderNumber())) {
            return existsName(purchaseOrderSearchDTO);
        }
        return existsName(purchaseOrderSearchDTO);
    }

    private Result existsName(PurchaseOrderSearchDTO purchaseOrderSearchDTO) {
        int count = 0;
        //新增
        if (purchaseOrderSearchDTO.getId() == 0) {
            count = this.purchaseOrderMapper.existsValue(purchaseOrderSearchDTO);
        } else {
            PurchaseOrder dbRole = this.purchaseOrderMapper.selectByPrimaryKey(purchaseOrderSearchDTO.getId());
            if (dbRole != null) {
                if (!dbRole.getOrderNumber().equals(purchaseOrderSearchDTO.getOrderNumber())) {
                    count = this.purchaseOrderMapper.existsValue(purchaseOrderSearchDTO);
                }
            }
        }
        Result result = null;
        //返回给前端  count==0 代表允许使用 ,否则已被占用
        if (count == 0) {
            result = Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        } else {
            result = Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }
        return result;
    }

}
