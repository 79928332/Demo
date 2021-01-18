package com.hxzy.service;

import com.hxzy.dto.UserLoginDTO;
import com.hxzy.dto.UserSearchDTO;
import com.hxzy.entity.User;
import com.hxzy.vo.Result;

public interface  UserService extends BaseService<User,Integer>{

    Result existsValue(UserSearchDTO userSearchDTO);

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    Result login(UserLoginDTO userLoginDTO);

    Result getUserInfoByJwt(String jwtToken);

}
