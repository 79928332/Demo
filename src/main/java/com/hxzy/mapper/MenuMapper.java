package com.hxzy.mapper;

import com.hxzy.dto.MenuSearchDTO;
import com.hxzy.entity.Menu;

/**
 * MenuMapper继承基类
 */
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer> {

    int existsValue(MenuSearchDTO menuSearchDTO);
}