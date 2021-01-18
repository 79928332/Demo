package com.hxzy.util;

/**
 * 全局公共常量
 */
public class RuoYiConst {

    /**
     * redis菜单表的前缀
     */
    public static final String  MENU_REDIS_KEY="menu:data:";

    /**
     * 菜单表全查询的过期时间(30分钟)
     */
    public static final int     MENU_REDIS_DATA_EXPIRED_MUTILE_TIME=30*60;

    /**
     * 带条件查询菜单表过期时间(1钟)
     */
    public static final int     MENU_REDIS_FILTER_EXPIRED_MUTILE_TIME=1*60;

    /**
     * redis部门表的前缀
     */
    public static final String  DEPT_REDIS_KEY="dept:data:";

    /**
     * 部门表全查询的过期时间(30分钟)
     */
    public static final int     DEPT_REDIS_DATA_EXPIRED_MUTILE_TIME=30*60;

    /**
     * 带条件查询部门表过期时间(1钟)
     */
    public static final int     DEPT_REDIS_FILTER_EXPIRED_MUTILE_TIME=1*60;

    /**
     * redis部门表TreeSelect的前缀
     */
    public static final String  DEPT_TREESELECT_REDIS_KEY="dept:treeselect:data";

    /**
     * 部门表TreeSelect全查询的过期时间(30分钟)
     */
    public static final int     DEPT_TREESELECT_REDIS_DATA_EXPIRED_MUTILE_TIME=30*60;

//    //商品表信息前缀
//    public static final String BASE_GOODS_REDIS_KEY="basegoods:data";
//
//    public static final int     BASE_GOODS_DATA_EXPIRED_MUTILE_TIME=30*60;
//
//    public static final int     BASE_GOODS_FILTER_EXPIRED_MUTILE_TIME=1*60;



}
