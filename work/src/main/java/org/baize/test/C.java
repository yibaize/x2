package org.baize.test;

import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class C implements JdbcModel {
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
                '}';
    }

    @Override
    public String account() {
        return null;
    }

    @Override
    public void setAccount(String account) {

    }
}
