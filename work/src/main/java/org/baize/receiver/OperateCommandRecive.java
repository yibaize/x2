package org.baize.receiver;
import org.baize.login.Login;
import org.baize.hall.shop.command.ShopBuy;
import org.baize.hall.scenes.ChangeScenes;
import org.baize.hall.bottom.BottomCommand;
public class OperateCommandRecive{
	private static OperateCommandRecive instance;
	public static OperateCommandRecive getInstance(){
		if(instance == null)
			instance = new OperateCommandRecive();
		return instance;
	}
	public OperateCommandAbstract recieve(int id,String[] params){
		switch (id){
			case 1:
				return getLogin(params);
			case 2:
				return getShopBuy(params);
			case 3:
				return getChangeScenes(params);
			case 4:
				return getBottomCommand(params);
			default:
				return null;
		}
	}
	private OperateCommandAbstract getLogin(String[] params){
		String value0 = params[0];
		return new Login(value0);
	}
	private OperateCommandAbstract getShopBuy(String[] params){
		int value0 = Integer.parseInt(params[0]);
		int value1 = Integer.parseInt(params[1]);
		String value2 = params[2];
		return new ShopBuy(value0,value1,value2);
	}
	private OperateCommandAbstract getChangeScenes(String[] params){
		int value0 = Integer.parseInt(params[0]);
		return new ChangeScenes(value0);
	}
	private OperateCommandAbstract getBottomCommand(String[] params){
		int value0 = Integer.parseInt(params[0]);
		long value1 = Long.parseLong(params[1]);
		return new BottomCommand(value0,value1);
	}
}
