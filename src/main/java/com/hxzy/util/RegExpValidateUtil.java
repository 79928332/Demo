package com.hxzy.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义正则表达式类
 */
public class RegExpValidateUtil {


    /**
     * 存放自定义正则表达式map
     */
    private static Map<String,String>  patternMap=new HashMap<String,String>();

    static{
        // http://www.mamicode.com/info-detail-74914.html
        patternMap.put("email","^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        patternMap.put("mobile","^1\\d{10}$");
        patternMap.put("int","^-?[1-9]\\d*$");
    }

    /**
     * 是否是邮件
     * @param value
     * @return
     */
    public static boolean isEmail(String value){
       return regExpValidate(value,patternMap.get("email"));
    }

    /**
     * 是否是手机号
     * @param value
     * @return
     */
    public static boolean isMobile(String value){
        return regExpValidate(value,patternMap.get("mobile"));
    }

    /**
     * 是否是整数
     * @param value
     * @return
     */
    public static boolean isInt(String value){
        return regExpValidate(value,patternMap.get("int"));
    }



    /**
     * 使用java正则表达式来验证
     * @param value 要验证的字符串
     * @param pattern 正则表达式
     * @return
     */
    private static boolean  regExpValidate(String value,String pattern){
        if(StringUtils.isBlank(value)){
            return false;
        }
        //正则表达式验证规则类
        Pattern  pt=Pattern.compile(pattern);
        //创建一个Matcher匹配类
        Matcher  matcher= pt.matcher(value);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String value="122221111";
        boolean result=RegExpValidateUtil.isMobile(value);
        System.out.println(result);
    }
}
