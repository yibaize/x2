package org.baize.player;

import org.baize.annotations.TableName;
import org.baize.player.weath.Weath;
import org.baize.receiver.JdbcLogicModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@TableName("user")
public class PlayerEntity extends JdbcLogicModel{
    private String account;
    private String name;
    private Weath weath;

    @Override
    public String account() {
        return account;
    }
    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dto.setName(name);
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
}
