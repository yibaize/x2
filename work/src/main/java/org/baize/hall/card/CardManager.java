package org.baize.hall.card;

import org.baize.excel.StaticConfigMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2018/1/5.
 * 描述：
 */
public class CardManager {
    private final List<CardDataDataTable> cards;
    private int positionCount;
    private int cardCount;
    public CardManager(int positionCount, int cardCount) {
        Map<Serializable,Object> card = StaticConfigMessage.getInstance().getMap(CardDataDataTable.class);
        cards = new ArrayList<>(card.size());
        for (Map.Entry<Serializable,Object> e:card.entrySet()){
            cards.add((CardDataDataTable) e.getValue());
        }
        this.positionCount = positionCount;
        this.cardCount = cardCount;
    }

    public List<CardDataDataTable> getCards() {
        return cards;
    }
    private List<CardDataDataTable> shuffle(){
        List<CardDataDataTable> cardList = cards;
        Collections.shuffle(cardList);
        return cardList;
    }

    /**
     * 发牌，洗牌
     * @param positionCount 要发多少堆牌
     * @param cardCount 每堆多少张牌
     * @return
     */
    public List<HoldCard> deal(){
        List<CardDataDataTable> cardList = shuffle();
        List<HoldCard> holdCards = new ArrayList<>(positionCount);
        for(int i = 0;i<positionCount;i++){
            CardDataDataTable[] card = new CardDataDataTable[cardCount];
            for(int j = 0;j<cardCount;j++){
                card[j] = cardList.get((i*cardCount)+j);
            }
            HoldCard holdCard = new HoldCard(i+1,card);
            holdCards.add(holdCard);
        }
        return holdCards;
    }
}
