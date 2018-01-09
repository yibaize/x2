package org.baize.hall.niuniu;
import org.baize.hall.operation.GamblingParty;
import org.baize.hall.room.Room;
import org.baize.hall.room.RoomInfoDto;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerOperation;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class NiuNiuRoom extends Room{
    private NiuNiuPlayerSet playerSet;

    public NiuNiuRoom() {
        super(2);
        playerSet = new NiuNiuPlayerSet(this);
        setGamblingParty(new GamblingParty(this,5,5));
    }

    public NiuNiuPlayerSet getPlayerSet() {
        return playerSet;
    }

    @Override
    public void leave(PlayerOperation player) {
        playerSet.leave(player);
        getGamblingParty().leave(player);
    }

    @Override
    public void intoRoom(PlayerOperation playerOperation) {
        playerSet.into(playerOperation);
    }

    @Override
    public IProtostuff roomInfom() {
        RoomInfoDto dto = new RoomInfoDto();
        List<PlayerEntity> bankerList = playerSet.getBankerList();
        List<String> name = new ArrayList<>(bankerList.size());
        for (PlayerEntity p:bankerList){
            name.add(p.getPlayerinfo().getName());
        }
        dto.setBanker(playerSet.getNowBanker().otherDto());
        dto.setBankerUpNamelList(name);
        dto.setState(isStartBattle());
        dto.setRoomId(getRoomId());
        dto.setLendTime((int) getEndTime()/1000);
        return dto;
    }
}
