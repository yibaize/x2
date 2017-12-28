package org.baize.test;

import org.baize.annotations.TableName;
import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
@TableName("test")
public class ModelTest extends JdbcModel {
    private int id;
    private A a;
    private B b;
    private D d;
    private String c = "123456";

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return Integer.parseInt(c);
    }

    @Override
    public String toString() {
        return "ModelTest{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", d=" + d +
                ", c='" + c + '\'' +
                '}';
    }
}
