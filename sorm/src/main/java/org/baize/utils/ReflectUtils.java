package org.baize.utils;

import org.baize.LoggerUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：反射常用操作
 */
public class ReflectUtils {
    public static Object invokeGet(String fieldName,Object o){
        try {
            Class c = o.getClass();
            Method m = c.getDeclaredMethod("get"+ StringUtils.firstChar2UpperCase(fieldName),null);
            return m.invoke(o,null);
        } catch (Exception e) {
            LoggerUtils.getPlatformLog().error("发射赋值对象时异常",e);
            return null;
        }
    }
    public static void invokeSet(Object obj,String columnName,Object columnValue){
        try {
            if(columnValue != null) {
                Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                        columnValue.getClass());
                m.invoke(obj, columnValue);
            }
        } catch (Exception e) {
            LoggerUtils.getPlatformLog().error("发射赋值对象时异常",e);
        }

    }
}
