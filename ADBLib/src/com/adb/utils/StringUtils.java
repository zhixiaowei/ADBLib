package com.adb.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class StringUtils {

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){

        if (str == null||str.trim().isEmpty()){
            return false;
        }

        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * 转化编码格式
     * @param str
     * @param charsetName
     * @return
     */
    public static String decode(String str,String charsetName){
        try {
            return URLDecoder.decode(str, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 重复字符串
     * @param str 重复的字符串
     * @param count 重复的次数
     * @return
     */
    public static String repeat(String str,int count){
        return String.format("%0" + count + "d", 0).replace("0",str);
    }

    /**
     * 删除字符串前缀的所有 prefix，如deleteAllPrefix("aaaaaaaaBC","a"),返回值为:BC
     * @param str
     * @param prefix 前缀
     * @return 如果不存在该前缀，则原值返回
     */
    public static String deleteAllPrefix(String str,String prefix){
        return str.replaceAll("^("+prefix+"+)", "");
    }

}
