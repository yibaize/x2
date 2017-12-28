package org.baize.shop.data;

import org.baize.assemblybean.annon.DataTable;
import org.baize.excel.DataTableMessage;
import org.baize.excel.StaticConfigMessage;

@DataTable
public class ShopDataTable implements DataTableMessage {
    /**商品id*/
    private final int id;
    /**售价*/
    private final int sellingPrice;
    /**商品昵称*/
    private final String name;
    /**商店id*/
    private final int shopId;
    /**商品数量*/
    private final int goodsNum;

    public ShopDataTable() {
        this.id = 0;
        this.sellingPrice = 0;
        this.name = "";
        this.shopId = 0;
        this.goodsNum = 0;
    }
    public static ShopDataTable get(int id){
        return StaticConfigMessage.getInstance().get(ShopDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public String getName() {
        return name;
    }

    public int getShopId() {
        return shopId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public int id() {
        return id;
    }

    public void AfterInit() {

    }
}
