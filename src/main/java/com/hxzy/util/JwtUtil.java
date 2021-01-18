package com.hxzy.util;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * JwtUTIL
 */
public class JwtUtil {

    //自定义密钥
    private static final String  JWTKEYS="hxzy#_mrp$_123!_$";
    //30分钟过期
    private static final long    EXPIRED_TIME=1000L*60 *30;

    /**
     * 解析jwt串  https://github.com/jwtk/jjwt
     * @param jwtStr
     * @return
     */
    public static Claims parseJwt(String jwtStr){
        Claims claims=Jwts.parser().setSigningKey(JWTKEYS).parseClaimsJws(jwtStr).getBody();
        return claims;
    }

    /**
     * 创建jwt字符
     * @param user
     * @return
     */
    public static String createJwt(User user){
         Claims  claims=Jwts.claims();
         claims.put("userInfo", JSONObject.toJSONString(user));

         //过期时间=当前毫秒+30分钟毫秒数
        Date expiresDate = new Date(System.currentTimeMillis() + EXPIRED_TIME);

        String jwtStr=Jwts.builder()
                //唯一标识
                .setSubject(UUID.randomUUID().toString())
                //存放数据
                .setClaims(claims)
                //设定过期时间30分钟
                .setExpiration(expiresDate)
                //密钥
                .signWith(SignatureAlgorithm.HS256,"hxzy#_mrp$_123!_$")
                .compact();
        return jwtStr;
    }


    public static void main(String[] args) {
        User  user=new User();
        user.setId(1);
        user.setAccount("admin");
        user.setPassword("admin8888");


        String jwtStr=createJwt(user);
        System.out.println(jwtStr);

        //转换
        Claims  claims=parseJwt(jwtStr);
        String json= claims.get("userInfo",String.class);
        User u= JSONObject.parseObject(json,User.class);

        System.out.println(u.getAccount());
        System.out.println(u.getPassword());


    }

}
