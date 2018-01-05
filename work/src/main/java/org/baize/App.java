package org.baize;


import org.baize.core.QueryFactory;
import org.baize.excel.ExcelUtils;
import org.baize.receiver.NetServer;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        new NetServer();
        QueryFactory.createQuery();
        ExcelUtils.init("excel");
        GameServer.start();
        System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream("PlayerSqlMap.properties"));
    }
}
