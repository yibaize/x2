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
    private CardDto bankerDto;
    private List<CardDto> otherDto;
    private int endTime;
    /**如果大于0说明庄家有的赚*/
    private long allMoney;
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

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public long getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(long allMoney) {
        this.allMoney = allMoney;
    }
}
