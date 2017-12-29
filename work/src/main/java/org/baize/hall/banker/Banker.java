package org.baize.hall.banker;

import org.baize.hall.room.LeaveRoomListener;
import org.baize.player.PlayerOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class Banker implements LeaveRoomListener{
    private PlayerOperation banker;
    private final List<PlayerOperation> bankerUpList;

    public Banker() {
        bankerUpList = new ArrayList<>();
    }

    public PlayerOperation getBanker() {
        return banker;
    }

    public void setBanker(PlayerOperation banker) {
        this.banker = banker;
    }

    public List<PlayerOperation> getBankerUpList() {
        return bankerUpList;
    }
    public void up(PlayerOperation player){
        if(!bankerUpList.contains(player))
            bankerUpList.add(player);
    }
    private void down(PlayerOperation player){
        if(banker.equals(player)){
            if(bankerUpList.size()>0) {
                banker = bankerUpList.remove(0);
            }
        }
    }

    @Override
    public void leave(PlayerOperation player) {
        down(player);
        if(bankerUpList.contains(player))
            bankerUpList.remove(player);
    }
}
