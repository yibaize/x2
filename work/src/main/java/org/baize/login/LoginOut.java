package org.baize.login;

import org.baize.assemblybean.annon.Protocol;
import org.baize.error.CloseConnectionError;
import org.baize.hall.room.Room;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.session.ISession;
import org.baize.session.SessionManager;

/**
 * 作者： 白泽
 * 时间： 2018/1/8.
 * 描述：退出
 */
@Protocol("5")
public class LoginOut extends OperateCommandAbstract {
    private final int errorCode;

    public LoginOut(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public IProtostuff execute() {
        ISession session = getSession();
        PlayerOperation operation = (PlayerOperation) session.getAttachment();
        if(operation == null)
            new CloseConnectionError(errorCode);
        Room room = operation.room();
        if(operation == null)
            new CloseConnectionError(errorCode);
        room.leave(operation);
        SessionManager.removeSession(operation.getAccount());
        session.close();
        new CloseConnectionError(errorCode);
        return null;
    }

}
