package com.hxzy.dto;

import com.hxzy.util.RuoYiConst;
import org.apache.commons.lang3.StringUtils;

public class MenuSearchDTO extends PageSearch {
    /**
     * 菜单名称
     */
    private String name;
    private Integer id;
    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;
    private String path;
    public Integer getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 读取redis相对应key
     * @return
     */
    public String getRedisKey(){
        String redisKey= RuoYiConst.MENU_REDIS_KEY;
        if(StringUtils.isBlank(this.name) && StringUtils.isBlank(this.status)){
            redisKey+="all";
        }else{
            String temp= (this.name+  this.status).hashCode()+"";
            redisKey+=temp;
        }
        return redisKey;
    }

    /**
     * redis过期时间
     * @return
     */
    public int getRedisExpireTime(){
        if(StringUtils.isBlank(this.name) && StringUtils.isBlank(this.status)){
            return RuoYiConst.MENU_REDIS_DATA_EXPIRED_MUTILE_TIME;
        }else{
            String temp= (this.name+  this.status).hashCode()+"";
            return RuoYiConst.MENU_REDIS_FILTER_EXPIRED_MUTILE_TIME;
        }
    }

}
