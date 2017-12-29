package org.baize.hall.room;

import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {
    private static RoomManager instance;
    public static RoomManager getInstance(){
        if(instance == null)
            instance = new RoomManager();
        return instance;
    }
    private final Map<Integer,Room> rooms;
    private RoomManager() {
        rooms = new HashMap<>();
    }

    public Room getRoomById(int roomId){
        if(!rooms.containsKey(roomId))
            new GenaryAppError(AppErrorCode.DATA_ERR);
        return rooms.get(roomId);
    }
}