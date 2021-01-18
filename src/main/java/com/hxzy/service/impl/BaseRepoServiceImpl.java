package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.dto.BaseRepoDTO;
import com.hxzy.dto.PageSearch;
import com.hxzy.entity.BaseEmployee;
import com.hxzy.entity.BaseRepo;
import com.hxzy.mapper.BaseRepoMapper;
import com.hxzy.service.BaseEmployeeService;
import com.hxzy.service.BaseRepoService;
import com.hxzy.util.PageInfoEntityToEntityVOUtil;
import com.hxzy.vo.BaseRepoVO;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseRepoServiceImpl  extends BaseServiceImpl<BaseRepo,Integer>  implements BaseRepoService {

    @Autowired
    private BaseEmployeeService  baseEmployeeService;

    private BaseRepoMapper baseRepoMapper;

    @Autowired
    public void setBaseRepoMapper(BaseRepoMapper baseRepoMapper) {
        this.baseRepoMapper = baseRepoMapper;
        super.setMyBatisBaseDao(baseRepoMapper);
    }


    @Override
    public Result existsValue(BaseRepoDTO baseRepoDTO) {
        if (StringUtils.isNotBlank(baseRepoDTO.getName())) {
            return existsName(baseRepoDTO);
        }
        return existsName(baseRepoDTO);
    }

    @Override
    public Result searchAllName() {
        List<BaseRepo> arr=this.baseRepoMapper.searchAllName();
        return Result.createResult_Data(ResultCode.OK,arr);
    }


    private Result existsName(BaseRepoDTO baseRepoDTO) {
        int count = 0;
        //新增
        if (baseRepoDTO.getId() == 0) {
            count = this.baseRepoMapper.existsValue(baseRepoDTO);
        } else {
            BaseRepo dbRole = this.baseRepoMapper.selectByPrimaryKey(baseRepoDTO.getId());
            if (dbRole != null) {
                if (!dbRole.getName().equals(baseRepoDTO.getName())) {
                    count = this.baseRepoMapper.existsValue(baseRepoDTO);
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

    @Override
    public Result search(PageSearch pageSearch) {
        PageHelper.startPage(pageSearch.getPage(), pageSearch.getSize());
        //分页
        List<BaseRepo> arr = this.baseRepoMapper.search(pageSearch);

        List<BaseRepoVO>  baseRepoVOList=new ArrayList<BaseRepoVO>();
        for (BaseRepo p:arr){
            BaseRepoVO  baseRepoVO=new BaseRepoVO();
            BeanUtils.copyProperties(p,  baseRepoVO);
            BaseEmployee baseEmployee=this.baseEmployeeService.selectByPrimaryKey(p.getAdminId());
            baseRepoVO.setMobile(baseEmployee. getMobile());
            baseRepoVO.setEmpName( baseEmployee.getName() );
            baseRepoVOList.add(baseRepoVO);
        }
        Page<BaseRepo> page =(Page<BaseRepo>) arr;
        PageInfo<BaseRepoVO> pageInfoBaseRepoVO= PageInfoEntityToEntityVOUtil.convertPageInfo(BaseRepoVO.class,page,baseRepoVOList);

        return Result.createResult_Data(ResultCode.OK, pageInfoBaseRepoVO);

    }
}
