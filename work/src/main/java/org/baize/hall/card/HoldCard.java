package org.baize.hall.card;

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
}
