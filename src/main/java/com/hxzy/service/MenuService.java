package com.hxzy.service;

import com.hxzy.dto.MenuSearchDTO;
import com.hxzy.entity.Menu;
import com.hxzy.vo.Result;

public interface  MenuService extends BaseService<Menu,Integer>{
    Result existsValue(MenuSearchDTO menuSearchDTO);

}
