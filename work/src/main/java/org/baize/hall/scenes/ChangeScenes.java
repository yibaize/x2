package org.baize.hall.scenes;

import org.baize.assemblybean.annon.Protocol;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.room.Room;
import org.baize.room.RoomManager;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
@Protocol("2")
public class ChangeScenes extends OperateCommandAbstract {
    private final int roomId;

    public ChangeScenes(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public IProtostuff execute() {
        Room room = RoomManager.getInstance().getRoomById(roomId);
        PlayerOperation player = (PlayerOperation) getSession().getAttachment();
        player.setRoom(room);
        room.into(player);
        return null;
    }
}
