package org.baize.receiver;


import org.baize.manager.Response;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerEntity;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public abstract class OperateCommandAbstract implements IOperationCommand{
    public static final JDBCMap JDBC_MAP = new JDBCMap(new PlayerEntity());
    private short cmdId;
    private ISession session;

    public short getCmdId() {
        return cmdId;
    }

    public void setCmdId(short cmdId) {
        this.cmdId = cmdId;
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

    public void run() {
        IProtostuff pro = execute();
        byte[] buf = null;
        if(pro != null)
            buf = null;// ProtostuffUtils.serializer(pro);
        Response response = new Response(cmdId,buf);
        session.write(response);
        broadcast();
    }
}
