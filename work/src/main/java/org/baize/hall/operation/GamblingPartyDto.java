package org.baize.hall.operation;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.hall.card.CardDto;
import org.baize.message.IProtostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
@Protostuff
public class GamblingPartyDto  implements IProtostuff {
    /**本局庄家牌局结果及id*/
    private CardDto bankerDto;
    /**本局牌局结果及id*/
    private List<CardDto> otherDto;
    /**结算后庄家的金币*/
    private long bankerGold;
    public GamblingPartyDto() {
    }

    public GamblingPartyDto(CardDto bankerDto, List<CardDto> otherDto) {
        this.bankerDto = bankerDto;
        this.otherDto = otherDto;
    }

    public CardDto getBankerDto() {
        return bankerDto;
    }

    public void setBankerDto(CardDto bankerDto) {
        this.bankerDto = bankerDto;
    }

    public List<CardDto> getOtherDto() {
        return otherDto;
    }

    public void setOtherDto(List<CardDto> otherDto) {
        this.otherDto = otherDto;
    }

    public long getBankerGold() {
        return bankerGold;
    }

    public void setBankerGold(long bankerGold) {
        this.bankerGold = bankerGold;
    }
}
