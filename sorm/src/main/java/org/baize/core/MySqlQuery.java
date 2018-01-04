package org.baize.core;

import org.baize.po.User;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：负责针对mysql数据库的查询
 */
public class MySqlQuery extends Query {
//    public static void testDML(){
//        User u = new User();
//        u.setId(11);
//        u.setAge(1000);
//        u.setName(1000);
////        new MySqlQuery().update(u,new String[]{"age","name"});
//        new MySqlQuery().insert(u);
////        new MySqlQuery().delete(u);
//    }
//    public static void testQueryRows(){
//        List<User> users = new MySqlQuery().queryRows("SELECT id,age,name FROM user WHERE id>? AND age>?",
//                User.class,new Object[]{0,6});
//        System.out.println(users);
//
//        Object o = new MySqlQuery().queryValue("SELECT COUNT(*) FROM user WHERE age > ?",new Object[]{10});
//        System.out.println(o);
//    }
//    public static void main(String[] args) {
//        testDML();
////        testQueryRows();
//    }

    @Override
    public Object queryPagenate(int pageNum, int size) {
        return null;
    }
}
