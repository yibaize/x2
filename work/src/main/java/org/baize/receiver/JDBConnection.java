package org.baize.receiver;


import org.baize.annotations.TableName;
import org.baize.core.Query;
import org.baize.core.QueryFactory;
import org.baize.excel.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2018/1/4.
 * 描述：
 */
public abstract class JDBConnection {
    private final Query query = QueryFactory.createQuery();
    private final Map<Class<?>,String> fieldName = new HashMap<>();
    public JDBConnection(Object o){
        Field[] fs = o.getClass().getDeclaredFields();
        for(int i = 0;i<fs.length;i++){
            fieldName.put(fs[i].getType(),fs[i].getName());
        }
    }
    public void updae(Object o){
        query.update(o,new String[]{""});
    }

    public Query getQUERY() {
        return query;
    }
}
