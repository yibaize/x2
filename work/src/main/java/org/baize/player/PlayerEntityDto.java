package org.baize.player;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protostuff
public class PlayerEntityDto implements IProtostuff{
    private String account;
    private String name;
    private long gold;
    private long diamond;

    public PlayerEntityDto() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }
}
