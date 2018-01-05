package org.baize.hall.operation;

import org.baize.hall.bottom.Bottom;
import org.baize.hall.bottom.BottomDto;
import org.baize.hall.card.CardDataDataTable;
import org.baize.hall.card.CardDto;
import org.baize.hall.card.CardManager;
import org.baize.hall.card.HoldCard;
import org.baize.hall.niuniu.NiuNiuCompera;
import org.baize.hall.niuniu.NiuNiuRoom;
import org.baize.hall.niuniu.PlayerSet;
import org.baize.hall.room.LeaveRoomListener;
import org.baize.hall.room.Room;
import org.baize.player.PlayerOperation;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class GamblingParty implements LeaveRoomListener{
    private static final Object LOCK = new Object();
    private final Set<LeaveRoomListener> leaveRoomListeners = new HashSet<>();
    private final CardManager cardManager;
    private HoldCard bankerCard;
    private List<HoldCard> otherCard = null;
    private Map<Integer,Bottom> bottomPosition;
    private AtomicLong allMoney = new AtomicLong(0);
    private NiuNiuRoom room;
    public GamblingParty(NiuNiuRoom room,int positionCount, int cardCount) {
        this.room = room;
        cardManager = new CardManager(positionCount,cardCount);
    }

    public BottomDto bottom(PlayerOperation player,int position,long count){
        synchronized(LOCK) {
            if (bottomPosition == null) {
                bottomPosition = new HashMap<>();
                Bottom b = new Bottom();
                b.bottom(player, count);
                bottomPosition.put(position, b);
                leaveRoomListeners.add(b);
            } else {
                if (!bottomPosition.containsKey(position)) {
                    Bottom b = new Bottom();
                    b.bottom(player, count);
                    bottomPosition.put(position, b);
                    leaveRoomListeners.add(b);
                } else {
                    Bottom b = bottomPosition.get(position);
                    b.bottom(player, count);
                }
            }
            allMoney.addAndGet(count);
            Map<Integer, Long> self = new HashMap<>();
            Map<Integer, Long> other = new HashMap<>();
            for (Map.Entry<Integer, Bottom> e : bottomPosition.entrySet()) {
                self.put(e.getKey(), e.getValue().getBotoomMoney(player));
                other.put(e.getKey(), e.getValue().getAllMoney());
            }
            BottomDto dto = new BottomDto(self, other);
            return dto;
        }
    }
    public long getAllMoney(){
        return allMoney.get();
    }
    public void setAllMoney(){
        allMoney.set(0);
    }
    @Override
    public void leave(PlayerOperation player) {
        Iterator<LeaveRoomListener> iterator = leaveRoomListeners.iterator();
        while (iterator.hasNext()){
            iterator.next().leave(player);
        }
    }

    /**
     * 发牌
     */
    public void shuffle(){
        List<HoldCard> h = cardManager.deal();
        bankerCard = h.remove(0);
        otherCard = h;

    }
    /**
     * 比牌
     * @return
     */
    private GamblingPartyDto comperaToCard(){
        int bankerReslt = NiuNiuCompera.check(bankerCard.cardFace());
        CardDto bankerDto = new CardDto(0,false,bankerReslt,bankerCard.cardId());
        List<CardDto> c = new ArrayList<>(otherCard.size());
        for(int i = 0;i<otherCard.size();i++){
            HoldCard otherHoldCard = otherCard.get(i);
            int otherResult = NiuNiuCompera.check(otherHoldCard.cardFace());
            boolean result = NiuNiuCompera.compareTo(bankerReslt,otherResult);
            CardDto otherDto = new CardDto(otherHoldCard.getPosition(),result,otherResult,otherHoldCard.cardId());
            c.add(otherDto);
        }
        return new GamblingPartyDto(bankerDto,c);
    }

    /**
     * 返回结果
     */
    public void end(){
        GamblingPartyDto result = comperaToCard();
        List<CardDto> c = result.getOtherDto();
        for (CardDto d:c){
            if(d.isResult())
                end(d.getPosition());//玩家结算
        }
        room.getPlayerSet().getNowBanker().getWeath().insertGold(getAllMoney());//庄家结算
        result.setEndTime((int) (room.getEndTime()/1000));
        result.setAllMoney(getAllMoney());
        room.endBattle();
        setAllMoney();
        room.getPlayerSet().notityInto(106,result);//通知结算
    }
    private long end(int position){
        Bottom bottom = bottomPosition.getOrDefault(position,null);
        long bankerMoney = getAllMoney();
        bankerMoney -= bottom.getAllMoney();
        allMoney.set(bankerMoney);
        if(bottom != null)
            bottom.end();
        return bottom.getAllMoney();
    }
    public void startBatlle(){
        room.endBattle();
        StartBattleInfoDto dto = new StartBattleInfoDto((int) room.getEndTime()/1000);
        room.getPlayerSet().notityInto(106,dto);//通知本场结束
    }
    public void endBattle(){
        room.getPlayerSet().notityInto(105,null);//通知本场结束
        room.endBattle();
    }
}
