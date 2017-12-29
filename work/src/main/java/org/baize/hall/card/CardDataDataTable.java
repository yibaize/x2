package org.baize.hall.card;

import org.baize.excel.DataTableMessage;
import org.baize.excel.StaticConfigMessage;
import org.baize.hall.shop.data.ShopDataTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class CardDataDataTable implements DataTableMessage,Comparable<CardDataDataTable> {
    private int id;
    private int cardFace;
    private static List<CardDataDataTable> cards;
    public CardDataDataTable(int id, int cardFace) {
        this.id = id;
        this.cardFace = cardFace;
    }
    public static CardDataDataTable get(int id){
        return StaticConfigMessage.getInstance().get(CardDataDataTable.class,id);
    }
    public static List<CardDataDataTable> cardList(){
        return cards;
    }
    public int getId() {
        return id;
    }

    public int getCardFace() {
        return cardFace;
    }

    @Override
    public int compareTo(CardDataDataTable o) {
        return cardFace - o.getCardFace();
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {
        Map<Serializable,Object> card = StaticConfigMessage.getInstance().getMap(CardDataDataTable.class);
        cards = new ArrayList<>(card.size());
        for (Map.Entry<Serializable,Object> e:card.entrySet()){
            cards.add((CardDataDataTable) e.getValue());
        }
    }
}
