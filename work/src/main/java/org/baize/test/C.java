package org.baize.test;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class C extends JdbcModel {
    private String name = "asdasd";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "C{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
