package org.baize.player.weath;

import org.baize.receiver.JdbcLogicModel;
import org.baize.session.SessionManager;

public class Weath extends JdbcLogicModel {
    private long gold;
    private long diamond;

    public Weath(long gold, long diamond) {
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

    private static final Object LOCK = new Object();

    public void insertGold(long count) {
        synchronized (LOCK) {
            gold += count;
            notifyx();
        }
    }

    public void desertGold(long count) {
        synchronized (LOCK) {
            gold -= count;
            notifyx();
        }
    }

    private void notifyx() {
        update();
        //通知自己
        SessionManager.sendMessage(this.account(), (short) 100, dto());
    }

    public WeathDto dto() {
        return new WeathDto(gold, diamond);
    }
}
