package com.hxzy.util;

import java.util.Map;

/**
 * 本地线程部门相关的数据 (只是为了拓展你们的知识广度)
 */
public class DeptLocalThreadUtil {

    private static ThreadLocal<Map<Integer,String>>  deptThreadLocal=new ThreadLocal<Map<Integer,String>>();

    /**
     * 取值
     * @return
     */
    public static Map<Integer,String>  getDeptMap(){
        return  deptThreadLocal.get();
    }

    /**
     * 赋值
     * @param value
     */
    public static void setDeptMap(Map<Integer,String>  value){
        deptThreadLocal.set(value);
    }

    /**
     * 移出部门缓存
     */
    public static void removeDeptMap(){
       deptThreadLocal.remove();
    }

}
