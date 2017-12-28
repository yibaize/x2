package org.baize.weath;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public class WeathDto {
    private long gold;
    private long diamond;

    public WeathDto() {
    }

    public WeathDto(long gold, long diamond) {
        this.gold = gold;
        this.diamond = diamond;
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