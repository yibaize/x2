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
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2018/1/4.
 * 描述：
 */
public class JDBCMap {
    private static final Query query = QueryFactory.createQuery();
    private final String[] filedNames;
    private final Map<Class<?>,String[]> filedNameMap;

    public JDBCMap(PlayerEntity o) {
        Field[] fs = o.getClass().getDeclaredFields();
        filedNameMap = new HashMap<>(fs.length);
        filedNames = new String[fs.length];
        for(int i = 0;i<fs.length;i++){
            Field f = fs[i];
            filedNameMap.putIfAbsent(f.getType(),new String[]{f.getName()});
            filedNames[i] = f.getName();
        }
        filedNameMap.putIfAbsent(o.getClass(),filedNames);
    }

    public void update(PlayerEntity o){
        Player p = new Player();
        setObj(o,p);
        query.update(p,filedNames);
    }
    public void update(Object o){
        Player p = new Player();
        String[] s = filedNameMap.get(o.getClass());
        if(s!= null){
            String s1 = s[0];
            try {
                Object value = "";
                if(o.getClass().isPrimitive())
                    value = o;
                else
                    value = JSON.toJSONString(o);
                setField(p.getClass().getDeclaredField(s1),p,value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        query.update(p,new String[]{"weath"});
    }
    public void insert(PlayerEntity o){
        Player p = new Player();
        setObj(o,p);
        query.insert(p);
    }
    public Object select(String account){
        Player p = (Player) query.queryById(Player.class,account);
        PlayerEntity e = new PlayerEntity();
        e.setAccount(p.getAccount());
        e.setWeath(JSON.parseObject(p.getWeath(),Weath.class));
        e.setPlayerinfo(JSON.parseObject(p.getPlayerinfo(),PlayerInfo.class));
        return e;
    }
    private void change(Object o,PlayerEntity e){
        Field[] fs = o.getClass().getDeclaredFields();
        for (int i = 0;i<fs.length;i++){
            Field f = fs[i];
            Object value = null;
            try {
                if(f.getType().isPrimitive()){
                    value = f.get(o);
                }else {
                    value = JSON.parseObject((String) f.get(o));
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            setField(f,e,value);
        }
    }
    private void setObj(Object o,Player p){
        Field[] fs = o.getClass().getDeclaredFields();
        for (int i = 0;i<fs.length;i++){
            Field f = fs[i];
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
    private void setField(Field field,Object obj,Object value){
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
