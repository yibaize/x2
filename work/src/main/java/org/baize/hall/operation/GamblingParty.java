package org.baize.hall.operation;

import org.baize.hall.bottom.Bottom;
import org.baize.hall.bottom.BottomDto;
import org.baize.hall.card.CardDataDataTable;
import org.baize.hall.card.CardDto;
import org.baize.hall.card.HoldCard;
import org.baize.hall.niuniu.NiuNiuCompera;
import org.baize.hall.room.LeaveRoomListener;
import org.baize.player.PlayerOperation;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class GamblingParty implements LeaveRoomListener{
    private HoldCard bankerCard;
    private List<HoldCard> otherCard = null;
    private Map<Integer,Bottom> bottomPosition;
    private final Set<LeaveRoomListener> leaveRoomListeners = new HashSet<>();
    private AtomicLong allMoney = new AtomicLong(0);
    private int positionCount;
    private int cardCount;
    public GamblingParty(int positionCount, int cardCount) {
        this.positionCount = positionCount;
        this.cardCount = cardCount;
    }

    public List<HoldCard> getOtherCard() {
        return otherCard;
    }
    public HoldCard getBankerCard() {
        return bankerCard;
    }
    private List<CardDataDataTable> shuffle(){
        List<CardDataDataTable> cardList = new ArrayList<>(CardDataDataTable.cardList());
        Collections.shuffle(cardList);
        return cardList;
    }

    public void deal(){
        List<CardDataDataTable> cardList = shuffle();
        otherCard = new ArrayList<>(positionCount - 1);
        for(int i = 0;i<positionCount;i++){
            CardDataDataTable[] card = new CardDataDataTable[cardCount];
            for(int j = 0;j<cardCount;j++){
                card[j] = cardList.get((i*cardCount)+j);
            }
            HoldCard holdCard = new HoldCard(i+1,card);
            if(i == 0)
                bankerCard = holdCard;
            else
                otherCard.add(holdCard);
        }
    }
    public BottomDto bottom(PlayerOperation player,int position,long count){
        if(bottomPosition == null){
            bottomPosition = new HashMap<>();
            Bottom b = new Bottom();
            b.bottom(player,count);
            bottomPosition.put(position,b);
            leaveRoomListeners.add(b);
        }else {
            if(!bottomPosition.containsKey(position)){
                Bottom b = new Bottom();
                b.bottom(player,count);
                bottomPosition.put(position,b);
                leaveRoomListeners.add(b);
            }else {
                Bottom b = bottomPosition.get(position);
                b.bottom(player,count);
            }
        }
        allMoney.addAndGet(count);
        Map<Integer,Long> self = new HashMap<>();
        Map<Integer,Long> other = new HashMap<>();
        for(Map.Entry<Integer,Bottom> e:bottomPosition.entrySet()){
            self.put(e.getKey(),e.getValue().getBotoomMoney(player));
            other.put(e.getKey(),e.getValue().getAllMoney());
        }
        BottomDto dto = new BottomDto(self,other);
        return dto;
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
     * 发牌，比牌，返回结果
     * @return
     */
    public GamblingPartyDto comperaToCard(){
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
    public long end(int position){
        Bottom bottom = bottomPosition.getOrDefault(position,null);
        if(bottom != null)
            bottom.end();
        return bottom.getAllMoney();
    }
}
