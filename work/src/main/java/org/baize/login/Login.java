package org.baize.login;

import org.baize.assemblybean.annon.Protocol;
import org.baize.excel.ExcelUtils;
import org.baize.excel.StaticConfigMessage;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerDataTable;
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerEntityDto;
import org.baize.receiver.JdbcReceiver;
import org.baize.receiver.OperateCommandAbstract;


/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("1")
public class Login extends OperateCommandAbstract {
    private final String account;

    public Login(String account) {
        this.account = account;
    }

    @Override
    public IProtostuff execute() {
        PlayerEntity entity = new PlayerEntity();
        entity.setAccount(account);
        entity = (PlayerEntity) JdbcReceiver.getInstance().select(entity);
        if(entity == null){
            PlayerDataTable dataTable = PlayerDataTable.get(1);
            entity.setAccount(account);
            JdbcReceiver.getInstance().insert(entity);
            entity = (PlayerEntity) JdbcReceiver.getInstance().select(entity);
        }
        if(entity == null){

        }
        return entity.dto();
    }



    //    public static void main(String[] args) {
//        ExcelUtils.init("excel");
//        System.out.println(StaticConfigMessage.getInstance().getMap(ShopDataTable.class));
//    }
}
