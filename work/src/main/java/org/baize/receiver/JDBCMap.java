package org.baize.receiver;
import com.alibaba.fastjson.JSON;
import org.baize.DateUtils;
import org.baize.core.Query;
import org.baize.core.QueryFactory;
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerInfo;
import org.baize.player.weath.Weath;
import org.baize.po.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 作者： 白泽
 * 时间： 2018/1/4.
 * 描述：
 */
public class JDBCMap {
    private static final Query query = QueryFactory.createQuery();
    private static final Player p = new Player();
    private static String[] filedNames;
    public static void update(PlayerEntity o){
        setObj(o);
        query.update(p,filedNames);
    }
    public static void insert(PlayerEntity o){
        setObj(o);
        query.insert(p);
    }
    public static void update(Object o){
        p.setWeath(JSON.toJSONString(o));
        query.update(p,new String[]{"weath"});
    }
    private static void setField(Object o){
        Object value = null;
        if(o.getClass().isPrimitive()){
            value = o;
        }else {
            value = JSON.toJSONString(o);
        }
        setField(null,p,value);
    }
//
//    public static void main(String[] args) {
//        PlayerEntity entity = new PlayerEntity();
//        entity.setAccount("123457");
//        Weath weath = new Weath(200,600);
//        PlayerInfo info = new PlayerInfo();
//        info.setCreateTime(""+DateUtils.month());
//        info.setHeadName("6846546");
//        info.setUpdateTime(""+DateUtils.currentDay());
//        info.setName("xczxczc");
//        entity.setWeath(weath);
//        entity.setPlayerinfo(info);
//        JDBCMap.update(entity);
//    }
    private static void setObj(Object o){
        Field[] fs = o.getClass().getDeclaredFields();
        for (int i = 0;i<fs.length;i++){
            Field f = fs[i];
            if(filedNames == null){
                filedNames[i] = f.getName();
            }
            Object value = null;
            try {
                if(f.getType().isPrimitive()){
                    value = f.get(o);
                }else {
                    value = JSON.toJSONString(f.get(o));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            setField(f,p,value);
        }
    }
    private static void setField(Field field,Object obj,Object value){
        try {
            field.setAccessible(true);
            Field modifiersField = null;
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(obj,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
