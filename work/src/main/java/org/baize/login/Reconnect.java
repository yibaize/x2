package org.baize.login;

import org.baize.assemblybean.annon.Protocol;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.hall.room.Room;
import org.baize.hall.room.RoomManager;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;

/**
 * 作者： 白泽
 * 时间： 2018/1/8.
 * 描述：断线重连
 */
@Protocol("6")
public class Reconnect extends OperateCommandAbstract{
    private final String account;
    private final int roomId;

    public Reconnect(String account, int roomId) {
        this.account = account;
        this.roomId = roomId;
    }

    @Override
    public IProtostuff execute() {
        Room room = RoomManager.getInstance().getRoomById(roomId);
        if(room == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        Login l = new Login(account);
        l.setSession(getSession());
        if(roomId == 1)
            return l.execute();
        l.execute();
        PlayerOperation operation = (PlayerOperation) l.getSession().getAttachment();
        operation.setRoom(room);
        return operation.room().roomInfom();
    }
}
