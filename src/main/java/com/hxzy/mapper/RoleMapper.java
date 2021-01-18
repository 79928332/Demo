package com.hxzy.mapper;

import com.hxzy.dto.MenuSearchDTO;
import com.hxzy.dto.RoleSearchDTO;
import com.hxzy.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer> {
    int existsValue(RoleSearchDTO roleSearchDTO);

    List<Role> roleList();
}