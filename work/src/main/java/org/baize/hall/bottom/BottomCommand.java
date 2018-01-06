package org.baize.hall.bottom;

import org.baize.assemblybean.annon.Protocol;
import org.baize.hall.niuniu.NiuNiuRoom;
import org.baize.hall.room.Room;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
@Protocol("4")
public class BottomCommand extends OperateCommandAbstract {
    private final int position;
    private final long count;

    public BottomCommand(int position, long count) {
        this.position = position;
        this.count = count;
    }

    @Override
    public IProtostuff execute() {
        PlayerOperation player = (PlayerOperation) getSession().getAttachment();
        NiuNiuRoom room = (NiuNiuRoom) player.room();
        BottomDto dto = room.getGamblingParty().bottom(player,position,count);
        return dto;
    }
}
