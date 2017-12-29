package org.baize.player;

import org.baize.hall.room.Room;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/12/29.
 * 描述：
 */
public class PlayerOperation {
    private final int id;
    private final PlayerEntity entity;
    private Room room;
    private final ISession session;
    public PlayerOperation(PlayerEntity entity,ISession session, Room room) {
        this.entity = entity;
        this.session = session;
        this.id = entity.getId();
        this.room = room;
    }

    public PlayerEntity entity() {
        return entity;
    }

    public PlayerEntity getEntity() {
        return entity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        PlayerOperation other = (PlayerOperation)obj;
        return other.getId() == getId();
    }
}
