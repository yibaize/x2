package org.baize.utils;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：
 */
public class StringUtils {
    /**
     * 手字母大写
     * @param str
     * @return
     */
    public static String firstChar2UpperCase(String str){
        return str.toUpperCase().substring(0,1)+str.substring(1);
    }
}
