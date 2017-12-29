package org.baize.hall.operation;

import org.baize.hall.bottom.Bottom;
import org.baize.hall.card.CardDataDataTable;
import org.baize.hall.card.HoldCard;
import org.baize.hall.room.LeaveRoomListener;
import org.baize.player.PlayerOperation;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class GamblingParty implements LeaveRoomListener{
    private HoldCard bankerCard;
    private List<HoldCard> otherCard;
    private Map<Integer,Bottom> bottomPosition;
    private final Set<LeaveRoomListener> leaveRoomListeners = new HashSet<>();
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
    public void deal(int positionCount, int cardCount){
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
    public void bottom(PlayerOperation player,int position,long count){
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
    }

    @Override
    public void leave(PlayerOperation player) {
        Iterator<LeaveRoomListener> iterator = leaveRoomListeners.iterator();
        while (iterator.hasNext()){
            iterator.next().leave(player);
        }
    }
}
