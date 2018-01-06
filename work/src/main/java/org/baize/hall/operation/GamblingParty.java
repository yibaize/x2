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
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerOperation;
import org.baize.player.weath.Weath;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
            }
            BottomDto dto = new BottomDto(self, other);
            atomicBoolean.compareAndSet(false,true);//下注通知标记
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
     * 通知场景玩家同步下注金额，这里用定时器的原因是防止每个玩家下一次注就要同步通知所有玩家减少网络压力
     * 这里要每秒并且有人下注才同步一次
     */
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public void notifyBottom(){
        if(atomicBoolean.get()) {
            Map<Integer, Long> self = new HashMap<>();
            Map<Integer, Long> other = new HashMap<>();
            for (Map.Entry<Integer, Bottom> e : bottomPosition.entrySet()) {
                other.put(e.getKey(), e.getValue().getAllMoney());
            }
            BottomDto dto = new BottomDto(self, other);
            //通知房间所有玩家下注金币变化
            room.getPlayerSet().notityInto(104, dto);
            atomicBoolean.compareAndSet(true,false);//下注通知标记
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

        room.endBattle();
        Weath bankerweath =  room.getPlayerSet().getNowBanker().getWeath();
        bankerweath.insertGold(getAllMoney());//庄家结算
        result.setBankerGold(bankerweath.getGold());//庄家金币
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
