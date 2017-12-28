package org.baize.model;

import com.alibaba.fastjson.JSON;
import org.baize.annotations.TableName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/22.
 * 描述：sql语句拼装
 */
public class SqlJiont {
    private Map<Class<?>,String> fieldMap = new HashMap<>();
    private Map<String,Class<?>> mapField = new HashMap<>();
    private Map<String,Integer> map = new HashMap<>();
    private Map<Class<?>,Integer> fieldIndex = new HashMap<>();
    private String tableName;
    public SqlJiont(JdbcModel jdbcModel) {
        jiontTableInsertSql(jdbcModel.getClass());
    }

    /**
     * 拼接大的对象的sql插入语句
     * @param
     * @return
     */
    private String tableUpdate;
    public String jiontTableInsertSql(Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        tableName = tableName(clazz);
        sb.append("INSERT INTO "+tableName+" ");
        sb1.append("UPDATE "+tableName+" SET ");
        Field[] fields = clazz.getDeclaredFields();
        String key = "";
        String value = "";
        for(int i = 1;i<fields.length;i++){
            String v = fields[i].getName();
            Class<?> clzz = fields[i].getType();
            fieldIndex.put(clzz,i);
            fieldMap.put(clzz,v);
            mapField.put(v,clazz);
            map.put(v,i);
            if(i == 1){
                key += "("+v+",";
                value += "("+"?,";
                sb1.append(v+" = ? , ");
            }else if(i == fields.length-1){
                key += v+")";
                value += "?)";
                sb1.append(v+" = ? WHERE id = ?;");
            }else {
                key += v+",";
                value += "?,";
                sb1.append(v+" = ? , ");
            }
        }
        tableUpdate = sb1.toString();
        sb.append(key+" VALUES "+value+";");//INSERT INTO player (1,2,3,4,5,6,7,8,9,10)  VALUES (?,?,?,?,?,?,?,?,?,?) ;
        return sb.toString();
    }
    public String tableUpdate(){
        return tableUpdate;
    }
    public String tableSelect(){
        StringBuffer sb = new StringBuffer();
        return "SELECT * FROM "+tableName+" WHERE id = ?;";
    }
    public String tableSelectAll(){
        return "SELECT * FROM "+tableName+" WHERE id > ?;";
    }
    private String tableName(Class<?> clazz){
        Annotation ann = clazz.getAnnotation(TableName.class);
        if(ann instanceof TableName){
            TableName value = (TableName) ann;
            String tableNameTemp = value.value();
            if(tableNameTemp == null || tableNameTemp.equals(""))
                throw new UnsupportedOperationException("没有为"+clazz+"的TableName注解的tableName赋值");
            return tableNameTemp;
        }
        return "";
    }

    public Map<Class<?>, String> getFieldMap() {
        return fieldMap;
    }

    public Map<Class<?>, Integer> getFieldIndex() {
        return fieldIndex;
    }

    public Map<String, Integer> getMap() {
        return map;
    }


    public String getTableName() {
        return tableName;
    }
    public PreparedStatement valuation(PreparedStatement ps, JdbcModel model){
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
        }
        try {
            for(Field f:fields){
                String value = "{}";
                String name = f.getName();
                if(name == "id") {
                    continue;
                }
                if(map.containsKey(name) && name != "id");{
                    if(f.getType() == String.class)
                        value = (String) f.get(model);
                    else
                        value = JSON.toJSONString(f.get(model));//转json字段存储
                    ps.setString(map.get(name),value);
                }
            }
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public JdbcModel tableInfo(PreparedStatement ps, JdbcModel model){
        try {
            Object o = model.getClass().newInstance();
            Field[] fields = model.getClass().getDeclaredFields();
            ps.setObject(1,model.id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                for(int i = 0;i<fields.length;i++){
                    String s = "{}";
                    String fieldName = fields[i].getName();
                    if(map.containsKey(fieldName)){
                        s = rs.getString(fieldName);
                        Field field = fields[i];
                        if(field.getType() != String.class) {
                            JdbcModel m = JSON.parseObject(s, (Type) field.getType());
                            field.setAccessible(true);
                            Field modifiersField = Field.class.getDeclaredField("modifiers");
                            modifiersField.setAccessible(true);
                            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                            field.set(o,m);
                        }else {
                            field.setAccessible(true);
                            Field modifiersField = Field.class.getDeclaredField("modifiers");
                            modifiersField.setAccessible(true);
                            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                            field.set(o,s);
                        }
                    }
                }
            }
            return (JdbcModel) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String oneField(Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO "+tableName+" ("+fieldMap.get(clazz)+") VALUES (?);");
        return sb.toString();
    }
    public String selectOneField(Class<?> clazz){
        //SELECT b FROM test WHERE id = 1
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT "+fieldMap.get(clazz)+" FROM "+tableName+" WHERE id = ?;");
        return sb.toString();
    }
    public String updateOne(Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE "+tableName+" SET "+fieldMap.get(clazz)+" = ? WHERE id = ?;");
        return sb.toString();
    }
    public String delete(){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM "+tableName+" WHERE id = ?;");
        return sb.toString();
    }
}
