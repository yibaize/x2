package org.baize.hall.scenes;

import org.baize.assemblybean.annon.Protocol;
import org.baize.hall.room.Room;
import org.baize.hall.room.RoomManager;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;
/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
@Protocol("4")
public class ChangeScenes extends OperateCommandAbstract {
    private final int roomId;

    public ChangeScenes(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public IProtostuff execute() {

        return null;
    }
}
