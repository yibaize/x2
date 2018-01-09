package org.baize.login;

import org.baize.hall.room.Room;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
public class MainRoom extends Room{
    public MainRoom() {
        super(1);
    }

    @Override
    public void leave(PlayerOperation player) {
    }

    @Override
    public void intoRoom(PlayerOperation playerOperation) {

    }

    @Override
    public IProtostuff roomInfom() {
        return null;
    }
}
