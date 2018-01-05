package org.baize.receiver;


import org.baize.StringUtils;
import org.baize.manager.Request;
import org.baize.message.IProtostuff;
import org.baize.message.TcpHandler;
import org.baize.ransitshipment.IRepeater;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/12/26.
 * 描述：网络消息接收器
 */
public class NetServer implements IRepeater {
    public NetServer() {
        TcpHandler.recieve(this);
    }

    public void massegeRansiter(ISession session, Request request) {
        try {
            short id = request.getId();
            String[] s = StringUtils.split(request.getData().getMsg(),",");
            OperateCommandAbstract msg = OperateCommandRecive.getInstance().recieve(id,s);
            msg.setCmdId(id);
            msg.setSession(session);
            IProtostuff ptf = msg.execute();
            msg.broadcast();
        }catch (Exception e){
            System.out.println("数据接收异常...");
        }
    }
}
