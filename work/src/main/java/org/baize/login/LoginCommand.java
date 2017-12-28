package org.baize.login;

import org.baize.assemblybean.annon.Protocol;
import org.baize.assemblybean.service.SelectAnnotationClass;
import org.baize.excel.ExcelUtils;
import org.baize.excel.StaticConfigMessage;
import org.baize.receiver.OperateCommandAbstract;


/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("1")
public class LoginCommand extends OperateCommandAbstract {
    public static void main(String[] args) {
        SelectAnnotationClass.getIocClazz("org.baize");
        ExcelUtils.init("excel");
        System.out.println(StaticConfigMessage.getInstance().getMap(ShopDataTable.class));
    }
}
