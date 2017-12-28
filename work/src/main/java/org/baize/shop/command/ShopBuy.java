package org.baize.shop.command;

import org.baize.assemblybean.annon.Protocol;
import org.baize.message.IProtostuff;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.shop.data.ShopDataTable;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("2")
public class ShopBuy extends OperateCommandAbstract {
    private final int id;
    private final int count;

    public ShopBuy(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public IProtostuff execute() {
        ShopDataTable dataTable = ShopDataTable.get(id);
        if(dataTable == null)
            ;
        
        return null;
    }
}
