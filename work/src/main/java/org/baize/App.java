package org.baize;


import org.baize.assemblybean.service.SelectAnnotationClass;
import org.baize.core.QueryFactory;
import org.baize.excel.ExcelUtils;
import org.baize.hall.operation.RobotManager;
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
        RobotManager.getInstance();
        QueryFactory.createQuery();
        ExcelUtils.init("excel");
        GameServer.start();
//        SelectAnnotationClass.getIocClazz("org.baize");
//        System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream("PlayerSqlMap.properties"));
    }
}
