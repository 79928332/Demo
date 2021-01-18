package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.controller.exception.AccountLockedException;
import com.hxzy.controller.exception.AccountNotFoundException;
import com.hxzy.controller.exception.InvalidPasswordException;
import com.hxzy.dto.PageSearch;
import com.hxzy.dto.UserLoginDTO;
import com.hxzy.dto.UserSearchDTO;
import com.hxzy.entity.Employee;
import com.hxzy.entity.User;
import com.hxzy.mapper.UserMapper;
import com.hxzy.service.EmployeeService;
import com.hxzy.service.UserService;
import com.hxzy.util.DeptLocalThreadUtil;
import com.hxzy.util.JwtUtil;
import com.hxzy.util.PageInfoEntityToEntityVOUtil;
import com.hxzy.util.encryption.BCryptUtil;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import com.hxzy.vo.UserVO;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl  extends BaseServiceImpl<User,Integer>  implements UserService {

    private UserMapper  userMapper;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
        super.setMyBatisBaseDao(userMapper);
    }

    /**
     * 使用本地线程池优化技术，来做缓存 mybatis底层在同一个事务也是这样来实现的
     * @param deptId
     * @return
     */
    private String getDeptNameForCache(int deptId){
        String deptName="";
        //判断是否有部门ID，如果有就直接取，如果没有再查询数据库
        Map<Integer,String> deptMap=DeptLocalThreadUtil.getDeptMap();
        if(deptMap==null){
            deptMap=new HashMap<Integer, String>();
            //查询数据库
            Employee employee=this.employeeService.selectByPrimaryKey(deptId);
            deptName=employee.getName();
            deptMap.put(deptId, deptName);

            //放到本地线程池中，供别人使用
            DeptLocalThreadUtil.setDeptMap( deptMap);
        }else{
            deptName= deptMap.get(deptId);
            if(StringUtils.isBlank(deptName)){
                //查询数据库
                Employee employee=this.employeeService.selectByPrimaryKey(deptId);
                deptName=employee.getName();
                deptMap.put(deptId, deptName);
                //放到本地线程池中，供别人使用
                DeptLocalThreadUtil.setDeptMap( deptMap);
            }
        }

        return deptName;
    }

    @Override
    public Result existsValue(UserSearchDTO userSearchDTO) {
        if(StringUtils.isNotBlank(userSearchDTO.getAccount())){
            return existsAccount(userSearchDTO);
        }else {
            return existsRoleName(userSearchDTO);
        }
    }

    private Result existsAccount(UserSearchDTO userSearchDTO){
        int count=0;
        if(userSearchDTO.getId()==0){
            count=this.userMapper.existsValue(userSearchDTO);
        }else {
            User dbuser=this.userMapper.selectByPrimaryKey(userSearchDTO.getId());
            if(dbuser!=null){
                if(!dbuser.getAccount().equals(userSearchDTO.getAccount())){
                    count=this.userMapper.existsValue(userSearchDTO);
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
    private Result existsRoleName(UserSearchDTO userSearchDTO){
        int count=0;
        if(userSearchDTO.getId()==0){
            count=this.userMapper.existsValue(userSearchDTO);
        }else {
            User dbuser=this.userMapper.selectByPrimaryKey(userSearchDTO.getId());
            if(dbuser!=null){
                if(!dbuser.getRoleName().equals(userSearchDTO.getRoleName())){
                    count=this.userMapper.existsValue(userSearchDTO);
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor =RuntimeException.class)
    @Override
    public boolean insert(User record) {
        //对密码进行加密
        String pwd=BCryptUtil.hashpw(record.getPassword(),BCryptUtil.gensalt());
        record.setPassword(pwd);
        boolean success =super.insert(record);

        return success;
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor =RuntimeException.class)
    @Override
    public boolean updateByPrimaryKeySelective(User record) {
        //对密码进行加密
        String pwd=BCryptUtil.hashpw(record.getPassword(),BCryptUtil.gensalt());
        record.setPassword(pwd);
        boolean success =super.updateByPrimaryKeySelective(record);

        return success;
    }

    @Override
    public Result search(PageSearch pageSearch) {
        PageHelper.startPage(pageSearch.getPage(), pageSearch.getSize());
        List<User> arr=this.userMapper.search(pageSearch);
        //自定义返回结果
        List<UserVO>  userVOList=new ArrayList<UserVO>();
        for(User  u : arr){
            UserVO  userVO=new UserVO();
            BeanUtils.copyProperties(u, userVO);
            Employee employee=this.employeeService.selectByPrimaryKey(u.getEmployeeId());
            userVO.setName( employee.getName());
            userVO.setCode(employee.getCode());
            userVOList.add(userVO);
        }

        //自己组装pageInfo
        Page<User> pageUser=(Page<User>) arr;

        //自己重组一个分页信息
        PageInfo<UserVO> pageInfoUserVO= PageInfoEntityToEntityVOUtil.convertPageInfo(UserVO.class,pageUser,userVOList);
        //清空本地缓存(缓存会越来越大)
        DeptLocalThreadUtil.removeDeptMap();

        return Result.createResult_Data(ResultCode.OK, pageInfoUserVO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        Result result=null;
        //加密
        String pwd=BCryptUtil.hashpw(userLoginDTO.getPassword(),BCryptUtil.gensalt());
        userLoginDTO.setPassword(pwd);
        //根据用户名来查询数据
        User dbUser=this.userMapper.login(userLoginDTO);
        if(dbUser==null){
            logger.error(userLoginDTO.getLoginName()+",账户找不到,无法登录");
            throw new AccountNotFoundException("账户或密码错误");
        }else{
            boolean isTrue= BCryptUtil.checkpw(userLoginDTO.getPassword(),  dbUser.getPassword());
            if(isTrue){
                if(dbUser.getState().equals("0")){
                    logger.error(userLoginDTO.getLoginName()+",账户被锁定");
                    throw new AccountLockedException();
                }else{
                    //登录成功   jwt来处理
                    String jwtStr= JwtUtil.createJwt(dbUser);
                    result=Result.createResult_Data(ResultCode.OK,jwtStr);

                    //更新登录时间
                    User saveUser=new User();
                    saveUser.setId(dbUser.getId());
                    saveUser.setUpdatime(new Date());
                    this.userMapper.updateByPrimaryKeySelective(saveUser);

                }
            }else{
                logger.error(userLoginDTO.getLoginName()+",密码错误,无法登录");
                throw new InvalidPasswordException("账户或密码错误");
            }
        }

        return result;
    }

    @Override
    public Result getUserInfoByJwt(String jwtToken) {

        Result result=null;

        Claims claims=JwtUtil.parseJwt(jwtToken);

        String jsonStr =claims.get("userInfo",String.class);
        result=Result.createResult_Data(ResultCode.OK,jsonStr);
        return result;
    }
}
