package org.baize.hall.niuniu;
import org.baize.ProtostuffUtils;
import org.baize.hall.banker.Banker;
import org.baize.hall.bottom.BottomDto;
import org.baize.hall.card.CardDto;
import org.baize.hall.operation.GamblingParty;
import org.baize.hall.room.Room;
import org.baize.hall.room.RoomInfoDto;
import org.baize.manager.Response;
import org.baize.player.PlayerOperation;
import org.baize.player.weath.WeathDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class NiuNiuRoom extends Room{
    private Banker banker;
    public NiuNiuRoom() {
        super(2,new GamblingParty(5,5));
    }

    @Override
    public void leave(PlayerOperation player) {
        getGamblingParty().leave(player);
        banker.leave(player);
        leaveRoom(player);
    }

    @Override
    public RoomInfoDto into(PlayerOperation player) {
        super.into(player);
        RoomInfoDto dto = new RoomInfoDto();
        List<PlayerOperation> bankerList = banker.getBankerUpList();
        List<String> name = new ArrayList<>(bankerList.size());
        for (PlayerOperation p:bankerList){
//            name.add(p.entity().getName());
        }
        dto.setBanker(banker.getBanker().entity().otherDto());
        dto.setBankerUpNamelList(name);
        dto.setOnline(online());
        dto.setState(isStartBattle());
        dto.setRoomId(getRoomId());
        dto.setLendTime((int) getEndTime()/1000);
        return dto;
    }

    @Override
    public void bottom(PlayerOperation player, int position, long count){
        BottomDto dto = getGamblingParty().bottom(player,position,count);
        byte[] buf = null;
        if(dto != null) {
            buf = ProtostuffUtils.serializer(dto);
        }
        Response response = new Response((short) 104, buf);
        for(Map.Entry<String,PlayerOperation> e:players().entrySet()){
            e.getValue().write(response);
        }
    }

    @Override
    public void end() {
        GamblingParty gamblingParty = getGamblingParty();
        long allMoney = gamblingParty.getAllMoney();
        List<CardDto> dto = getResult().getOtherDto();
        for(CardDto d:dto){
            if(d.isResult()){
                allMoney -= gamblingParty.end(d.getPosition());
            }
        }
        PlayerOperation bankerOperation = banker.getBanker();
        if(bankerOperation != null) {
            bankerOperation.entity().getWeath().insertGold(allMoney);
            WeathDto dto1 = bankerOperation.entity().getWeath().dto();
            byte[] buf = null;
            if (dto1 != null)
                buf = ProtostuffUtils.serializer(dto1);
            Response response = new Response((short) 109, buf);
            for (Map.Entry<String, PlayerOperation> e : players().entrySet()) {
                e.getValue().write(response);
            }
        }
    }
}
