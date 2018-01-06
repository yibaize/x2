package org.baize.player;

import org.baize.RandomUtils;
import org.baize.login.NameDataTable;
import org.baize.model.JdbcModel;
import org.baize.player.weath.Weath;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public class PlayerEntity implements JdbcModel{
    public static final String BANKER_ID = "sys_10x30x60x19x78";
    private String account;
    private PlayerInfo playerinfo;
    private Weath weath;
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public PlayerInfo getPlayerinfo() {
        return playerinfo;
    }

    public void setPlayerinfo(PlayerInfo playerinfo) {
        this.playerinfo = playerinfo;
    }

    public Weath getWeath() {
        return weath;
    }

    public void setWeath(Weath weath) {
        this.weath = weath;
    }

    public PlayerEntityDto selfDto(){
        PlayerEntityDto dto = new PlayerEntityDto();
        dto.setAccount(account);
        dto.setName(playerinfo.getName());
        dto.setDiamond(weath.getDiamond());
        dto.setGold(weath.getGold());
        return dto;
    }
    public PlayerEntityDto otherDto(){
        //PlayerEntityDto dto = new PlayerEntityDto();
//        dto.setId(id);
//        dto.setAccount(account);
//        dto.setName(name);
//        dto.setDiamond(weath.getDiamond());
//        dto.setGold(weath.getGold());
        return null;
    }
   public static PlayerEntity systemBanker(String account,Weath w){
        PlayerEntity banker = new PlayerEntity();
        banker.setAccount(account);
        PlayerInfo info = new PlayerInfo();
        StringBuilder sb = new StringBuilder();
        NameDataTable dataTable = NameDataTable.get(RandomUtils.randomIndex(64));
        sb.append(dataTable.getGender());
        dataTable = NameDataTable.get(RandomUtils.randomIndex(64));
        sb.append(dataTable.getSymbol());
        dataTable = NameDataTable.get(RandomUtils.randomIndex(64));
        sb.append(dataTable.getName());
        info.setName(sb.toString());
        info.setHeadName("h_1");
        banker.setPlayerinfo(info);
        banker.setWeath(w);
        return banker;
   }

    @Override
    public boolean equals(Object obj) {
       PlayerEntity e = (PlayerEntity) obj;
        return account == e.getAccount();
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "account='" + account + '\'' +
                ", playerinfo=" + playerinfo +
                ", weath=" + weath +
                '}';
    }
}
