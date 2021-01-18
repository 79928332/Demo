package com.hxzy.service.impl;

import com.hxzy.dto.BaseParamDTO;
import com.hxzy.entity.BaseParam;
import com.hxzy.mapper.BaseParamMapper;
import com.hxzy.service.BaseParamService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BaseParamServiceImpl  extends BaseServiceImpl<BaseParam,Integer>  implements BaseParamService {

    private BaseParamMapper  baseParamMapper;

    @Autowired
    public void setBaseParamMapper(BaseParamMapper baseParamMapper) {
        this.baseParamMapper = baseParamMapper;
        super.setMyBatisBaseDao(baseParamMapper);
    }


    @Override
    public Result searchAll(BaseParamDTO baseParamDTO) {
        List<BaseParam> arr=this.baseParamMapper.searchAll(baseParamDTO);
        return Result.createResult_Data(ResultCode.OK,arr);
    }

    @Override
    public Result existsValue(BaseParamDTO baseParamDTO) {
        int count=0;
        if(baseParamDTO.getId()==0){
            count=this.baseParamMapper.existsValue(baseParamDTO);
        }else {
            BaseParam baseParam=this.baseParamMapper.selectByPrimaryKey(baseParamDTO.getId());
            if(baseParam!=null){
                if(StringUtils.isNotBlank(baseParamDTO.getName())){
                    if(!baseParam.getName().equals(baseParamDTO.getName())){
                        count=this.baseParamMapper.existsValue(baseParamDTO);
                    }
                }
            }
        }
        Result result=null;
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else {
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }
        return result;
    }
}
