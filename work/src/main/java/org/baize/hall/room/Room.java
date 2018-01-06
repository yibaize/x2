package org.baize.hall.room;

import org.baize.DateUtils;
import org.baize.ProtostuffUtils;
import org.baize.hall.operation.GamblingParty;
import org.baize.hall.operation.GamblingPartyDto;
import org.baize.manager.Response;
import org.baize.player.PlayerOperation;
import org.baize.session.SessionManager;

import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public abstract class Room implements LeaveRoomListener{
    private int roomId;
    private boolean isStartBattle;
    private long endTime;
    private GamblingParty gamblingParty;
    public Room(int roomId) {
        this.roomId = roomId;
    }

    public GamblingParty getGamblingParty() {
        return gamblingParty;
    }

    public void setGamblingParty(GamblingParty gamblingParty) {
        this.gamblingParty = gamblingParty;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean isStartBattle() {
        return isStartBattle;
    }

    public final boolean startBattle(){
        this.endTime = DateUtils.getFutureTimeMillis();
        isStartBattle = true;
        return false;
    }
    private GamblingPartyDto result;

    public GamblingPartyDto getResult() {
        return result;
    }

    public final boolean endBattle(){
        isStartBattle = false;
        endTime = 0;
        return false;
    }
    public abstract void intoRoom(PlayerOperation playerOperation);
    public long getEndTime() {
        return endTime - System.currentTimeMillis();
    }
}
