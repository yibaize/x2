package org.baize.test;

import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class A implements JdbcModel {
    private int i = 12;
    private int j = 782425;
    private String account;
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }


    @Override
    public String account() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }
}
