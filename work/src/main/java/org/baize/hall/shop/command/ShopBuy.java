package org.baize.hall.shop.command;

import org.baize.assemblybean.annon.Protocol;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerOperation;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.hall.shop.data.ShopDataTable;
import org.baize.weath.Weath;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("2")
public class ShopBuy extends OperateCommandAbstract {
    /**商品id*/
    private final int id;
    /**购买编号，成功与否*/
    private final String buyCode;

    public ShopBuy(int id, String buyCode) {
        this.id = id;
        this.buyCode = buyCode;
    }

    @Override
    public IProtostuff execute() {
        ShopDataTable dataTable = ShopDataTable.get(id);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        long gold = dataTable.getGoodsNum();
        PlayerOperation operation = (PlayerOperation) getSession().getAttachment();
        Weath weath = operation.entity().getWeath();
        weath.insertGold(gold);
        return null;
    }
}
