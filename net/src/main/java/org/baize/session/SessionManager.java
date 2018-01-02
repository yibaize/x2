package org.baize.session;

import org.baize.manager.Response;
import org.baize.message.IProtostuff;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class SessionManager {
    private static final ConcurrentHashMap<String, ISession> onlineSessions = new ConcurrentHashMap<>();
    public static boolean putSession(String playerId,ISession session){
        boolean success = false;
        if(!onlineSessions.containsKey(playerId))
            success = onlineSessions.putIfAbsent(playerId,session) == null ? true : false;
        return success;
    }
    public static ISession removeSession(String playerId){
        if(!onlineSessions.containsKey(playerId)) return null;
        return onlineSessions.remove(playerId);
    }

    /**
     * 是否在线
     * @param playerId
     * @return
     */
    public static boolean isOnlinePlayer(String playerId){
        return onlineSessions.containsKey(playerId);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<String> onlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
    public static int onLinePlayerNum(){
        return onlineSessions == null ? 0 : onlineSessions.size();
    }
    public static Map<String,ISession> map(){
        return onlineSessions;
    }

    /**
     * 发送消息[protoBuf协议]
     * @param playerId
     * @param
     */
    public static void sendMessage(String playerId, short id, IProtostuff msg){
        Response response = msg(id,msg);
        ISession session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            session.write(response);
        }
    }
    private static Response msg(short id, IProtostuff msg){
        Response response = new Response();
        response.setId(id);
        byte[] buf = null;
//        if(msg != null)
//            buf = ProtostuffUtils.serializer(msg);
        response.setData(buf);
        return response;
    }
    /**通知所有在线玩家包括自己*/
    public static void notifyAllx(short id,IProtostuff msg){
        Response response = msg(id,msg);
        for (Map.Entry<String,ISession> e: SessionManager.map().entrySet()){
            e.getValue().write(response);
        }
    }
    /**通知所有在线玩家除了自己*/
    public static void notifyAllx(String playerId,short id,byte[] buf){
        Response response = new Response();
        for (Map.Entry<String,ISession> e: SessionManager.map().entrySet()){
            if(e.getKey().equals(playerId))
                e.getValue().write(response);
        }
    }
}
