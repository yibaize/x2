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

    public GamblingPartyDto(CardDto bankerDto, List<CardDto> otherDto) {
        this.bankerDto = bankerDto;
        this.otherDto = otherDto;
        this.endTime = endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public CardDto getBankerDto() {
        return bankerDto;
    }

    public List<CardDto> getOtherDto() {
        return otherDto;
    }
}
