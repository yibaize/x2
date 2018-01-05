package org.baize.hall.niuniu;

import org.baize.ProtostuffUtils;
import org.baize.manager.Response;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerOperation;
import org.baize.player.weath.Weath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2018/1/5.
 * 描述：
 */
public class NiuNiuPlayerSet extends PlayerSet{
    private PlayerEntity nowBanker;
    private List<PlayerEntity> bankerList;
    private Map<String,PlayerOperation> onlinePlayers;
    private NiuNiuRoom niuNiuRoom;

    public NiuNiuPlayerSet(NiuNiuRoom niuNiuRoom) {
        bankerList = new ArrayList<>();
        onlinePlayers = new HashMap<>();
        this.niuNiuRoom = niuNiuRoom;
    }

    public PlayerEntity getNowBanker() {
        return nowBanker;
    }

    public List<PlayerEntity> getBankerList() {
        return bankerList;
    }

    /**
     * 获取房间所有玩家
     * @return
     */
    public List<PlayerOperation> allOnlinePlayer(){
        return new ArrayList<>(onlinePlayers.values());
    }

    /**
     * 在线人数
     * @return
     */
    public int onlinePlayerSize(){
        return onlinePlayers.size()+20;
    }
    /**
     * 有玩家进入房间
     * @param playerOperation
     */
    public void into(PlayerOperation playerOperation){
        if(onlinePlayers.putIfAbsent(playerOperation.getAccount(),playerOperation) != null){
            notityInto(101,null);
        }
    }

    /**
     * 玩家下线通知
     * @param playerOperation
     */
    public void leave(PlayerOperation playerOperation){
        if(onlinePlayers.remove(playerOperation.getAccount()) != null){
            notityInto(102,null);
            if(bankerList.contains(playerOperation.entity())){
                bankerList.remove(playerOperation.entity());
            }
            if(nowBanker.equals(playerOperation.entity())){
                downBanker(playerOperation);
            }
        }
    }

    public void bankerUp(PlayerOperation banker){
        if(!bankerList.contains(banker)){
            bankerList.add(banker.entity());
            notityInto(101,null);
        }
    }
    private void up(){
        if(nowBanker.getAccount().equals(PlayerEntity.BANKER_ID) && bankerList.size() > 0){
            nowBanker = bankerList.remove(0);
            notityInto(103, nowBanker.otherDto());
        }
    }
    public boolean downBanker(PlayerOperation banker){
        if(banker.entity().equals(nowBanker)){
            nowBanker = PlayerEntity.systemBanker(PlayerEntity.BANKER_ID,new Weath(PlayerEntity.BANKER_ID,1420235421L,54645L));
            up();
            return true;
        }
        return true;
    }
    /**
     * 有玩家进入房间通知所有该房间成员
     * @param cmd
     * @param msg
     */
    public void notityInto(int cmd, IProtostuff msg){
        byte[] buf = null;
        if(msg != null){
            buf = ProtostuffUtils.serializer(msg);
        }
        Response response = new Response((short) cmd,buf);
        for(PlayerOperation p:onlinePlayers.values()){
            p.write(response);
        }
    }
    public static void main(String[] args) {
        NiuNiuPlayerSet set = new NiuNiuPlayerSet(null);
        List<PlayerOperation> p = set.allOnlinePlayer();
        System.out.println(p);
    }
}
