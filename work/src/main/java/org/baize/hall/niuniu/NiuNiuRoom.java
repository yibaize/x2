package org.baize.hall.niuniu;

import org.baize.hall.banker.Banker;
import org.baize.hall.operation.GamblingParty;
import org.baize.hall.room.Room;
import org.baize.player.PlayerOperation;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class NiuNiuRoom extends Room{
    private Banker banker;
    private GamblingParty gamblingParty;
    public NiuNiuRoom() {
        super(2);
    }

    @Override
    public void leave(PlayerOperation player) {
        gamblingParty.leave(player);
        banker.leave(player);
        leaveRoom(player);
    }
    public void bottom(PlayerOperation player,int position,long count){
        gamblingParty.bottom(player,position,count);
    }
}
