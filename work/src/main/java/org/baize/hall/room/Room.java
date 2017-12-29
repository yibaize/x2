package org.baize.hall.room;

import org.baize.DateUtils;
import org.baize.player.PlayerOperation;

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
    private Map<Integer,PlayerOperation> playerMap;
    public Room(int roomId) {
        this.roomId = roomId;
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
    public final boolean endBattle(){
        isStartBattle = false;
        return false;
    }
    public void into(PlayerOperation player){
        playerMap.put(player.getId(),player);
    }
    public void leaveRoom(PlayerOperation player){
        playerMap.remove(player.getId(),player);
    }
    public int online(){
        return playerMap == null ? 0 : playerMap.size();
    }

    public long getEndTime() {
        return endTime - System.currentTimeMillis();
    }
}
