package com.hxzy.mapper;

import com.hxzy.dto.BaseRepoDTO;
import com.hxzy.entity.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BaseRepoMapper继承基类
 */
@Repository
public interface BaseRepoMapper extends MyBatisBaseDao<BaseRepo, Integer> {

    int existsValue(BaseRepoDTO baseRepoDTO);

    List<BaseRepo> searchAllName();
}