package org.baize.receiver;


import io.netty.buffer.ByteBuf;
import org.baize.LoggerUtils;
import org.baize.NettySerializable;
import org.baize.StringUtils;
import org.baize.error.AppErrorCode;
import org.baize.error.CloseConnectionError;
import org.baize.error.GenaryAppError;
import org.baize.error.LogAppError;
import org.baize.manager.Request;
import org.baize.manager.Response;
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
            if(e instanceof GenaryAppError){
                GenaryAppError error = (GenaryAppError) e;
                error((short) error.getErrorCode(),session);
            }else if(e instanceof LogAppError){
                LogAppError error = (LogAppError) e;
                LoggerUtils.getLogicLog().error(error.getMessage());
                error((short) AppErrorCode.DATA_ERR,session);
            }else if(e instanceof CloseConnectionError){
                CloseConnectionError error = (CloseConnectionError) e;
                error((short) error.getErrorCode(),session);
                session.close();
            }
            LoggerUtils.getLogicLog().debug("数据接收异常...");
        }
    }
    private void error(short errorCode,ISession session){
        ByteBuf buf = NettySerializable.getBuffer();
        buf.writeShort(errorCode);
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        Response response = new Response((short)404,bytes);
        session.write(response);
    }
}
