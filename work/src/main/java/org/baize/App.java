package org.baize;

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


    private static Map<Integer,Class<?>> m = new HashMap<>();
    public static void main( String[] args )
    {
        System.out.println(args);
    }
}
