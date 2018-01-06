package org.baize.hall.shop.command;

import org.baize.assemblybean.annon.Protocol;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.player.weath.Weath;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.hall.shop.data.ShopDataTable;
/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("2")
public class ShopBuy extends OperateCommandAbstract {
    /**商品id*/
    private final int id;
    /**数量*/
    private final int count;
    /**购买编号，成功与否*/
    private final String buyCode;

    public ShopBuy(int id, int count, String buyCode) {
        this.id = id;
        this.count = count;
        this.buyCode = buyCode;
    }

    @Override
    public IProtostuff execute() {
        try {
            ShopDataTable dataTable = ShopDataTable.get(id);
            long gold = dataTable.getGoodsNum()*count;
            PlayerOperation operation = (PlayerOperation) getSession().getAttachment();
            Weath weath = operation.entity().getWeath();
            weath.insertGold(gold);
        }catch (Exception e){
            e.printStackTrace();
            new GenaryAppError(AppErrorCode.DATA_ERR);
        }
        return null;
    }
}
