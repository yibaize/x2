package org.baize.player.weath;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;
@Protostuff
public class WeathDto implements IProtostuff{
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