package org.baize.weath;

import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public class Weath extends JdbcModel{
    private long gold;
    private long diamond;
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
    public WeathDto dto(){
        return new WeathDto(gold,diamond);
    }
}
