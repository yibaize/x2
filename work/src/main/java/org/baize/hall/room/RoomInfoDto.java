package org.baize.hall.room;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerEntityDto;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
@Protostuff
public class RoomInfoDto implements IProtostuff {
    private int roomId;
    private int online;
    private int lendTime;
    private PlayerEntityDto banker;
    private boolean state;
    private List<String> bankerUpNamelList;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getLendTime() {
        return lendTime;
    }

    public void setLendTime(int lendTime) {
        this.lendTime = lendTime;
    }

    public PlayerEntityDto getBanker() {
        return banker;
    }

    public void setBanker(PlayerEntityDto banker) {
        this.banker = banker;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<String> getBankerUpNamelList() {
        return bankerUpNamelList;
    }

    public void setBankerUpNamelList(List<String> bankerUpNamelList) {
        this.bankerUpNamelList = bankerUpNamelList;
    }
}
