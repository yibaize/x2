package org.baize.hall.bottom;


import org.baize.hall.operation.SecondsTimer;
import org.baize.hall.room.LeaveRoomListener;
import org.baize.player.PlayerOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class Bottom implements LeaveRoomListener{
    private Map<PlayerOperation,Long> playerMap;
    private long allMoney;
    private static final Object LOCK = new Object();
    public void bottom(PlayerOperation player,long count){
        synchronized (LOCK) {
            if (playerMap == null)
                playerMap = new HashMap<>();
            if (!playerMap.containsKey(player)) {
                playerMap.put(player, count);
            } else {
                long nowCount = playerMap.get(player);
                nowCount += count;
                playerMap.put(player, nowCount);
            }
            allMoney += count;
        }
        player.entity().getWeath().desertGold(count);
    }
    public long getAllMoney() {
        return allMoney;
    }

    @Override
    public void leave(PlayerOperation player) {
        if(playerMap.containsKey(player))
            playerMap.remove(player);
    }
    public long getBotoomMoney(PlayerOperation player){
        return playerMap.getOrDefault(player,0L);
    }
    public void end(){
        for(Map.Entry<PlayerOperation,Long> e:playerMap.entrySet()){
            e.getKey().entity().getWeath().insertGold(e.getValue());
        }
    }
}
