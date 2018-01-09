package org.baize.message;

import org.baize.LoggerUtils;
import org.baize.manager.Request;
import org.baize.ransitshipment.IRepeater;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class TcpHandler {
    private static IRepeater massegeRansiter;
    public static void recieve(IRepeater ransiter){
        if(massegeRansiter == null){
           massegeRansiter = ransiter;
            LoggerUtils.getPlatformLog().warn("网络消息中转器注册成功...");
        }
    }
    public static void messageRecieve(ISession session, Request request){
            if(massegeRansiter == null) return;
            massegeRansiter.massegeRansiter(session,request);
    }
}
