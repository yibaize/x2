package org.baize.utils;

import org.baize.LoggerUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：jdbc查询常用操作
 */
public class JDBCUtils {
    public static void handleParams(PreparedStatement ps,Object[] params){
        if(params != null){
            for(int i = 0;i<params.length;i++){
                try {
                    ps.setObject(1+i, params[i]);
                } catch (SQLException e) {
                    LoggerUtils.getPlatformLog().error("设置对象时异常",e);
                }
            }
        }
    }
}
