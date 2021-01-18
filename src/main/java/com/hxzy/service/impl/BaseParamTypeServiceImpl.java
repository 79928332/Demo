package com.hxzy.service.impl;

import com.hxzy.dto.BaseParamTypeDTO;
import com.hxzy.entity.BaseParamType;
import com.hxzy.mapper.BaseParamTypeMapper;
import com.hxzy.service.BaseParamTypeService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseParamTypeServiceImpl  extends BaseServiceImpl<BaseParamType,Integer>  implements BaseParamTypeService {

    private BaseParamTypeMapper  baseParamTypeMapper;

    @Autowired
    public void setBaseParamTypeMapper(BaseParamTypeMapper baseParamTypeMapper) {
        this.baseParamTypeMapper = baseParamTypeMapper;
        super.setMyBatisBaseDao(baseParamTypeMapper);
    }


    @Override
    public Result typeList() {
        List<BaseParamType> arr=this.baseParamTypeMapper.typeList();
        return Result.createResult_Data(ResultCode.OK,arr);
    }

    @Override
    public Result existsValue(BaseParamTypeDTO baseParamTypeDTO) {
        int count=0;
        if(baseParamTypeDTO.getId()==0){
            count=this.baseParamTypeMapper.existsValue(baseParamTypeDTO);
        }else{
            BaseParamType baseParamType=this.baseParamTypeMapper.selectByPrimaryKey(baseParamTypeDTO.getId());
            if(baseParamType!=null){
                if(StringUtils.isNotBlank(baseParamTypeDTO.getTypeName())){
                    if(!baseParamType.getTypeName().equals(baseParamTypeDTO.getTypeName())){
                        count=this.baseParamTypeMapper.existsValue(baseParamTypeDTO);
                    }
                }else if(StringUtils.isNotBlank(baseParamTypeDTO.getType())){
                    if(!baseParamType.getType().equals(baseParamTypeDTO.getType())){
                        count=this.baseParamTypeMapper.existsValue(baseParamTypeDTO);
                    }
                }
            }
        }
        Result result=null;
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else{
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }
        return result;
    }
}
