package org.baize.test;

import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class D implements JdbcModel {
    private String lk = "ASFDASDasdap;sdjpasdkjap;sdas";
    public String getLk() {
        return lk;
    }
    public void setLk(String lk) {
        this.lk = lk;
    }

    @Override
    public String toString() {
        return "D{" +
                "lk='" + lk + '\'' +
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
