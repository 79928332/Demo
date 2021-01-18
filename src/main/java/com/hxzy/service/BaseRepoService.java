package com.hxzy.service;

import com.hxzy.dto.BaseRepoDTO;
import com.hxzy.entity.BaseRepo;
import com.hxzy.vo.Result;

public interface  BaseRepoService extends BaseService<BaseRepo,Integer>{
    /**
     * 判断值是否存在
     * @param baseRepoDTO
     * @return
     */
    Result existsValue(BaseRepoDTO baseRepoDTO);

    Result searchAllName();

}
