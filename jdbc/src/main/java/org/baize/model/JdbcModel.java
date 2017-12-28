package org.baize.model;

/**
 * 作者： 白泽
 * 时间： 2017/12/21.
 * 描述：
 */
public abstract class JdbcModel {
    private transient int id;
    private transient String account;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
