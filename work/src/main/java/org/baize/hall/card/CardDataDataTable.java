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
    private final int id;
    private final int cardFace;
    public CardDataDataTable() {
        this.id = 0;
        this.cardFace = 0;
    }
    public static CardDataDataTable get(int id){
        return StaticConfigMessage.getInstance().get(CardDataDataTable.class,id);
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

    }
}
