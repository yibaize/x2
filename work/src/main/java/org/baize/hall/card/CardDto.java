package org.baize.hall.card;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
@Protostuff
public class CardDto implements IProtostuff{
    /**位置*/
    private int position;
    /**输赢结果*/
    private boolean result;
    /**类型，牛牛，没牛，牛几...（-1没牛,1-9有牛，0牛牛）*/
    private int type;
    /**牌位置*/
    private List<Integer> cardId;

    public CardDto() {
    }

    public CardDto(int position, boolean result, int type, List<Integer> cardId) {
        this.position = position;
        this.result = result;
        this.type = type;
        this.cardId = cardId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCardId() {
        return cardId;
    }

    public void setCardId(List<Integer> cardId) {
        this.cardId = cardId;
    }
}
