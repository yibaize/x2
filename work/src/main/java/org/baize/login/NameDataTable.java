package org.baize.login;


import org.baize.excel.DataTableMessage;
import org.baize.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：
 */
public class NameDataTable implements DataTableMessage{
    private final int id;
    private final String name;
    private final String gender;
    private final String symbol;
    public NameDataTable(){
        this.id = 0;
        this.name = "";
        this.gender = "";
        this.symbol = "";
    }
    public static NameDataTable get(int id){
        return StaticConfigMessage.getInstance().get(NameDataTable.class,id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
