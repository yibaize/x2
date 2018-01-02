package org.baize.player;

import org.baize.hall.room.Room;
import org.baize.manager.Response;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class PlayerOperation {
    private final String account;
    private final PlayerEntity entity;
    private Room room;
    private final ISession session;
    public PlayerOperation(PlayerEntity entity,ISession session, Room room) {
        this.entity = entity;
        this.session = session;
        this.room = room;
        this.account = entity.account();
    }

    public String getAccount() {
        return account;
    }

    public PlayerEntity entity() {
        return entity;
    }

    public Room room() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void write(Response response){
        session.write(response);
    }
    @Override
    public boolean equals(Object obj) {
        PlayerOperation other = (PlayerOperation)obj;
        return other.account.equals(account);
    }
}
