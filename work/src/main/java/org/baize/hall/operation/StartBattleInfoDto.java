package org.baize.hall.operation;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;

/**
 * 作者： 白泽
 * 时间： 2018/1/5.
 * 描述：
 */
@Protostuff
public class StartBattleInfoDto implements IProtostuff {
    private int endTime;

    public StartBattleInfoDto(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
