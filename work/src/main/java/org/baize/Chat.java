package org.baize;

import org.baize.assemblybean.annon.Protocol;
import org.baize.hall.room.Room;
import org.baize.manager.Msg;
import org.baize.manager.Response;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2018/1/10.
 * 描述：
 */
@Protocol("7")
public class Chat extends OperateCommandAbstract{
    private String msg;
    @Override
    public IProtostuff execute() {
        return null;
    }

    @Override
    public void broadcast() {
        Msg m = new Msg(msg);
        byte[] b = ProtostuffUtils.serializer(m);
        Response response = new Response((short)7,b);
        PlayerOperation self = (PlayerOperation) getSession().getAttachment();
        Room r = self.room();
        List<PlayerOperation> allPlayer = r.getPlayers();
        for(PlayerOperation p:allPlayer){
            if(!p.equals(self))
            p.write(response);
        }
    }
}
