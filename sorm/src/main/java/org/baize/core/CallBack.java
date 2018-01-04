package org.baize.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：
 */
public interface CallBack {
    public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}