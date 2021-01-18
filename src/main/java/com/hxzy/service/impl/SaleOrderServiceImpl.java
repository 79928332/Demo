package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.dto.PageSearch;
import com.hxzy.entity.BaseEmployee;
import com.hxzy.entity.BaseGoods;
import com.hxzy.entity.BaseRepo;
import com.hxzy.entity.SaleOrder;
import com.hxzy.mapper.SaleOrderMapper;
import com.hxzy.service.BaseEmployeeService;
import com.hxzy.service.BaseGoodsService;
import com.hxzy.service.BaseRepoService;
import com.hxzy.service.SaleOrderService;
import com.hxzy.util.PageInfoEntityToEntityVOUtil;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import com.hxzy.vo.SaleOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderServiceImpl  extends BaseServiceImpl<SaleOrder,Integer>  implements SaleOrderService {

    private SaleOrderMapper  saleOrderMapper;

    private BaseGoodsService baseGoodsService;

    private BaseRepoService baseRepoService;

    private BaseEmployeeService baseEmployeeService;

    @Autowired
    public void setBaseGoodsService(BaseGoodsService baseGoodsService) {
        this.baseGoodsService = baseGoodsService;
    }

    @Autowired
    public void setBaseRepoService(BaseRepoService baseRepoService) {
        this.baseRepoService = baseRepoService;
    }

    @Autowired
    public void setBaseEmployeeService(BaseEmployeeService baseEmployeeService) {
        this.baseEmployeeService = baseEmployeeService;
    }

    @Autowired
    public void setSaleOrderMapper(SaleOrderMapper saleOrderMapper) {
        this.saleOrderMapper = saleOrderMapper;
        super.setMyBatisBaseDao(saleOrderMapper);
    }

    @Override
    public Result search(PageSearch pageSearch) {
        //开启分页
        PageHelper.startPage(pageSearch.getPage(),pageSearch.getSize());
        //查询
        List<SaleOrder> arr=this.saleOrderMapper.search(pageSearch);

        List<SaleOrderVO> saleOrderVOList=new ArrayList<SaleOrderVO>();


        for (SaleOrder saleOrder:arr){
            SaleOrderVO saleOrderVO=new SaleOrderVO();
            BeanUtils.copyProperties(saleOrder,saleOrderVO);
            System.out.println(saleOrder.getGoodsId());
            //再查询每个商品名称主键对应的对象
            BaseGoods baseGoods=this.baseGoodsService.selectByPrimaryKey(saleOrder.getGoodsId());
            BaseRepo baseRepo=this.baseRepoService.selectByPrimaryKey(saleOrder.getRepoId());
            BaseEmployee baseEmployee=this.baseEmployeeService.selectByPrimaryKey(saleOrder.getEmployeeId());


            saleOrderVO.setGoodsName(baseGoods.getName());
            saleOrderVO.setGoodsType(baseGoods.getType());
            saleOrderVO.setBrand(baseGoods.getBrand());
            saleOrderVO.setUnitName(baseGoods.getUnit());
            saleOrderVO.setRepoName(baseRepo.getName());
            saleOrderVO.setEmployeeName(baseEmployee.getName());

            if(saleOrder.getCheckState()==1){
                saleOrderVO.setCheckStates("待审核");
            }else if(saleOrder.getCheckState()==2){
                saleOrderVO.setCheckStates("审核异常");
            }else if(saleOrder.getCheckState()==3){
                saleOrderVO.setCheckStates("审核通过");
            }else{
                saleOrderVO.setCheckStates("审核不通过");
            }

            if(saleOrder.getType()==1){
                saleOrderVO.setTypeName("销售订单");
            }else {
                saleOrderVO.setTypeName("退货订单");
            }
            saleOrderVOList.add(saleOrderVO);

        }
        Page pg=(Page)arr;

        PageInfo<SaleOrderVO> saleOrderVOPageInfo= PageInfoEntityToEntityVOUtil.convertPageInfo(SaleOrderVO.class,pg,saleOrderVOList);

        return Result.createResult_Data(ResultCode.OK,saleOrderVOPageInfo);
    }
}
