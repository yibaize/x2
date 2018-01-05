package org.baize.hall.operation;

import org.baize.hall.room.Room;
import org.baize.hall.room.RoomManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
public class RobotManager {
    private List<GamblingParty> robot;
    private List<Room> rooms;
    private static RobotManager instance;
    private final ScheduledExecutorService timerTask;
    public static RobotManager getInstance(){
        if(instance == null)
            instance = new RobotManager();
        return instance;
    }
    private RobotManager() {
        robot = new ArrayList<>();
        rooms = RoomManager.getInstance().getAllRoom();
        this.timerTask = Executors.newScheduledThreadPool(1);
        executor();
    }
    private void executor(){
        timerTask.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }
    private int timer;
    private Runnable runnable = () -> {
        timer++;
        if(timer == 1){
            for (GamblingParty g:robot){
                g.startBatlle();
            }
        }else if(timer == 10){
            for (GamblingParty g:robot){
                g.shuffle();
            }
        }else if(timer == 15){
            for (GamblingParty g:robot){
                g.end();
            }
        } else if(timer == 20){
            for (GamblingParty g:robot){
                g.endBattle();
            }
        }
    };
}
