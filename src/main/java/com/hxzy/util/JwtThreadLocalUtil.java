package com.hxzy.util;

import com.hxzy.entity.User;

/**
 * jwt相关的本地线程池
 */
public class JwtThreadLocalUtil {

    private static ThreadLocal<User>  userThreadLocal=new ThreadLocal<User>();


    /**
     * 取值
     * @return
     */
    public  static User getUser(){
        return userThreadLocal.get();
    }

    /**
     * 赋值
     * @param user
     */
    public static void setUser(User user){
        userThreadLocal.set(user);
    }

    /**
     * 移出
     */
    public static void removeUser(){
        userThreadLocal.remove();
    }

}
