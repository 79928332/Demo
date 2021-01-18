package com.hxzy.controller;

import com.hxzy.dto.RoleSearchDTO;
import com.hxzy.entity.Menu;
import com.hxzy.entity.Role;
import com.hxzy.service.MenuService;
import com.hxzy.service.RoleService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import com.hxzy.vo.RoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RequestMapping(value = "/api/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService  roleService;
    @Autowired
    private MenuService menuService;


    @GetMapping(value = "/search")
    @ResponseBody
    public Result  search(RoleSearchDTO  roleSearchDTO){
        return this.roleService.search(roleSearchDTO);
    }

    @ResponseBody
    @PostMapping(value = "/insert")
    public Result insert(@Valid RoleVO role){
        Result  result=null;
        role.setState(1);
        role.setUpdater("admin");
        role.setUpdatime(new Date());
        //截取权限id
        String[] menuIds=role.getMenuIds().split(",");
        //自定义权限名数组
        String[] menuName=new String[menuIds.length];
        //查询权限名
        for (int i=0;i<menuIds.length;i++){
            Menu menu=this.menuService.selectByPrimaryKey(Integer.parseInt(menuIds[i]));
            menuName[i]=menu.getName();
        }
        //拼成字符串
        String nameAll= StringUtils.join(menuName,",");
        role.setMenuNames(nameAll);
        boolean isSuccessed=this.roleService.insert(role);


        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }

    /**
     * 修改操作
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@Valid Role  role){
        Result  result=null;

        boolean isSuccessed=this.roleService.updateByPrimaryKeySelective(role);


        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }

    /**
     * 判断值是否存在
     * @param roleSearchDTO
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/exist")
    public Result existValue(RoleSearchDTO roleSearchDTO){
        return this.roleService.existsValue(roleSearchDTO);
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    public Result delete(@Valid Role role){
        Result  result=null;
        role.setState(0);
        boolean isSuccessed=this.roleService.updateByPrimaryKeySelective(role);

        if(isSuccessed){
            result=Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result=Result.createResult(ResultCode.DB_SAVE_ERROR);
        }

        return result;
    }

    @ResponseBody
    @GetMapping(value = "/rolelist")
    public Result roleList(){
        return this.roleService.roleList();
    }


}
