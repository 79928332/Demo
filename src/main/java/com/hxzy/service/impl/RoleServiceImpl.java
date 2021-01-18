package com.hxzy.service.impl;

import com.hxzy.dto.RoleSearchDTO;
import com.hxzy.entity.Role;
import com.hxzy.mapper.RoleMapper;
import com.hxzy.service.RoleService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl  extends BaseServiceImpl<Role,Integer>  implements RoleService {

    private RoleMapper  roleMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
        super.setMyBatisBaseDao(roleMapper);
    }


    /**
     * 判断值是否存在
     * @param roleSearchDTO
     * @return
     */
    @Override
    public Result existsValue(RoleSearchDTO roleSearchDTO) {

         if(StringUtils.isNotBlank( roleSearchDTO.getName())){
           return existsRoleName(roleSearchDTO);
         }else {
             return existsRoleKey(roleSearchDTO);
         }
    }

    @Override
    public Result roleList() {
        List<Role> arr=this.roleMapper.roleList();
        return Result.createResult_Data(ResultCode.OK,arr);
    }

    /**
     * 角色名称值是否重复
     * @param roleSearchDTO
     * @return
     */
    private Result  existsRoleName(RoleSearchDTO  roleSearchDTO){

        int count=0;
        //新增
        if(roleSearchDTO.getId()==0){
            count=this.roleMapper.existsValue(roleSearchDTO);
        }else{
            Role  dbRole=this.roleMapper.selectByPrimaryKey(roleSearchDTO.getId());
            if(dbRole!=null){
                if(!dbRole.getName() .equals(  roleSearchDTO.getName())){
                    count=this.roleMapper.existsValue(roleSearchDTO);
                }
            }
        }

        Result  result=null;
       //返回给前端  count==0 代表允许使用 ,否则已被占用
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else{
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }

        return result;
    }

    /**
     * 角色标识是否复重
     * @param roleSearchDTO
     * @return
     */
    private Result  existsRoleKey(RoleSearchDTO  roleSearchDTO){
        int count=0;
        //新增
        if(roleSearchDTO.getId()==0){
            count=this.roleMapper.existsValue(roleSearchDTO);
        }else{
            Role  dbRole=this.roleMapper.selectByPrimaryKey(roleSearchDTO.getId());
            if(dbRole!=null){
                if(!dbRole.getCode() .equals(  roleSearchDTO.getCode())){
                    count=this.roleMapper.existsValue(roleSearchDTO);
                }
            }
        }

        Result  result=null;
        //返回给前端  count==0 代表允许使用 ,否则已被占用
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else{
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }

        return result;
    }
}
