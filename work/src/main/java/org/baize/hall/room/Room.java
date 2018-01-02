package org.baize.hall.room;

import org.baize.DateUtils;
import org.baize.ProtostuffUtils;
import org.baize.hall.operation.GamblingParty;
import org.baize.hall.operation.GamblingPartyDto;
import org.baize.manager.Response;
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
    private Map<String,PlayerOperation> playerMap;
    private final GamblingParty gamblingParty;
    public Room(int roomId,GamblingParty gamblingParty) {
        this.roomId = roomId;
        this.gamblingParty = gamblingParty;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean isStartBattle() {
        return isStartBattle;
    }

    public GamblingParty getGamblingParty() {
        return gamblingParty;
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

    public final void battling(){
        result = gamblingParty.comperaToCard();
        result.setEndTime((int) getEndTime()/1000);
        Response response = new Response((short) 106, ProtostuffUtils.serializer(result));
        for(Map.Entry<String,PlayerOperation> e:playerMap.entrySet()){
            e.getValue().write(response);
        }
    }
    public final boolean endBattle(){
        isStartBattle = false;
        return false;
    }
    public RoomInfoDto into(PlayerOperation player){
        playerMap.put(player.getAccount(),player);
        return null;
    }
    public void leaveRoom(PlayerOperation player){
        playerMap.remove(player.getAccount(),player);
    }
    public int online(){
        return playerMap == null ? 0 : playerMap.size();
    }

    public long getEndTime() {
        return endTime - System.currentTimeMillis();
    }
    public Map<String,PlayerOperation> players(){
        return playerMap;
    }
    public abstract void bottom(PlayerOperation player, int position, long count);
    public abstract void end();
}
