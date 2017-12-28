package org.baize.player;

import org.baize.annotations.TableName;
import org.baize.model.JdbcModel;
import org.baize.weath.Weath;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@TableName("user")
public class PlayerEntity extends JdbcModel{
    private int id;
    private String account;
    private String name;
    private Weath weath;
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

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

    public PlayerEntityDto dto(){
        PlayerEntityDto dto = new PlayerEntityDto();
        dto.setId(id);
        dto.setAccount(account);
        dto.setName(name);
        dto.setDiamond(weath.getDiamond());
        dto.setGold(weath.getGold());
        return dto;
    }
}
