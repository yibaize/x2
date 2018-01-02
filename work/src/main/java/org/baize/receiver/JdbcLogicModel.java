package org.baize.receiver;

import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public abstract class JdbcLogicModel implements JdbcModel {
    private transient String account;

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String account() {
        return account;
    }

    public void update(){
        JdbcReceiver.getInstance().commit(this);
    }
}
