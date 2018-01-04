package org.baize;

import org.baize.annotations.TableName;
import org.baize.assemblybean.annon.ExcelValue;
import org.baize.test.A;
import org.baize.test.B;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    private String s1;
    private String s2;
    private int i1;
    private A a;
    private B b;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
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

    private static Map<Integer,Class<?>> m = new HashMap<>();
    public static void main( String[] args )
    {

    }
}
