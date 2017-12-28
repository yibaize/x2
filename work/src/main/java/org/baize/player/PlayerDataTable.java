package org.baize.player;

import org.baize.excel.DataTableMessage;
import org.baize.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public class PlayerDataTable implements DataTableMessage {
    private final int id;
    private final long gold;
    private final long diamond;

    public PlayerDataTable() {
        this.id = 0;
        this.gold = 0;
        this.diamond = 0;
    }
    public static PlayerDataTable get(int id){
        return StaticConfigMessage.getInstance().get(PlayerDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public long getGold() {
        return gold;
    }

    public long getDiamond() {
        return diamond;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
