package org.baize.hall.room;

import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.hall.niuniu.NiuNiuRoom;
import org.baize.login.MainRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        rooms.put(2,new NiuNiuRoom());
        rooms.put(1,new MainRoom());
    }
    public Room getRoomById(int roomId){
        if(!rooms.containsKey(roomId))
            new GenaryAppError(AppErrorCode.DATA_ERR);
        return rooms.get(roomId);
    }
    public List<Room> getAllRoom(){
        return new ArrayList<>(rooms.values());
    }
}