package org.baize.hall.card;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class HoldCard {
    /**牌堆位置*/
    private int position;
    /**每堆牌的牌*/
    private CardDataDataTable[] card;
    public HoldCard(int position, CardDataDataTable[] card) {
        this.position = position;
        this.card = card;
    }

    public int getPosition() {
        return position;
    }

    public CardDataDataTable[] getCard() {
        return card;
    }
    public int[] cardFace(){
        int [] cardFace = new int[card.length];
        for(int i = 0;i<card.length;i++){
            cardFace[i] = card[i].getCardFace();
        }
        return cardFace;
    }
    public List<Integer> cardId(){
        List<Integer> cardid = new ArrayList<>(card.length);
        for(int i = 0;i<card.length;i++){
            cardid.add(card[i].getId());
        }
        return cardid;
    }
}
