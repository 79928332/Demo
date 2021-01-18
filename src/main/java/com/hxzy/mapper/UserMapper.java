package com.hxzy.mapper;

import com.hxzy.dto.UserLoginDTO;
import com.hxzy.dto.UserSearchDTO;
import com.hxzy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper继承基类
 */
@Repository
public interface UserMapper extends MyBatisBaseDao<User, Integer> {
    int existsValue(UserSearchDTO userSearchDTO);


    /**
     * 删除用户岗位 sys_user_post
     * @param userId
     * @return
     */
    int deletePostByUserId(int userId);

    /**
     * 删除用户的角色 sys_user_role
     * @param userId
     * @return
     */
    int deleteRoleByUserId(int userId);

    /**
     * 为新增用户添加岗位 sys_user_post
     * @param userId
     * @param posts
     * @return
     */
    int insertPostByUserId(@Param(value = "userId") int userId, @Param(value = "posts") List<Integer> posts);

    /**
     * 为新增的用户添加角色 sys_user_role
     * @param userId
     * @param roles
     * @return
     */
    int insertRoleByUserId(@Param(value = "userId") int userId, @Param(value = "roles") List<Integer> roles);

    User login(UserLoginDTO userLoginDTO);

}