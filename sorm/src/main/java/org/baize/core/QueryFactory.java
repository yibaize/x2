package org.baize.core;

import org.baize.LoggerUtils;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：
 */
public class QueryFactory {
    private static Query prototypeObj;  //原型对象
    static {

        try {
            Class c = Class.forName(DBManager.getConfig().getQueryClass());  ////加载指定的query类
            prototypeObj = (Query) c.newInstance();
        } catch (Exception e) {
            LoggerUtils.getPlatformLog().error("加载指定的query类",e);
        }
        //加载po包下面所有的类，便于重用，提高效率！
        TableContext.loadPOTables();
    }

    private QueryFactory(){  //私有构造器
    }

    public static Query createQuery(){
        try {
            return (Query) prototypeObj.clone();
        } catch (CloneNotSupportedException e) {
            LoggerUtils.getPlatformLog().error("clone对象Query时异常",e);
            return null;
        }
    }
}
