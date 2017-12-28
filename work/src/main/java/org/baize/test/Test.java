package org.baize.test;

import org.baize.maseegehandler.JdbcTemplate;
import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ModelTest test = new ModelTest();
        JdbcTemplate template = new JdbcTemplate(test);
        B b = new B();
        A a = new A();
        //C c = new C();
        D d = new D();
        test.setA(a);
        test.setB(b);
        test.setD(d);
        JdbcModel model = null;
        long start = System.currentTimeMillis();
        //template.inset(test);
        test = (ModelTest) template.select(test);
        long end = System.currentTimeMillis();
        System.out.println(test);
    }
}
