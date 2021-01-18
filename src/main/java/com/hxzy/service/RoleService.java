package com.hxzy.service;

import com.hxzy.dto.RoleSearchDTO;
import com.hxzy.entity.Role;
import com.hxzy.vo.Result;

public interface RoleService extends BaseService<Role,Integer>{

        /**
         * 判断值是否存在
         * @param roleSearchDTO
         * @return
         */
        Result existsValue(RoleSearchDTO roleSearchDTO);

        Result roleList();
}
