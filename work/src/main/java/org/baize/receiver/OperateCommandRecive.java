package org.baize.receiver;
import org.baize.login.LoginCommand;
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
				return getLoginCommand(params);
			default:
				return null;
		}
	}
	private OperateCommandAbstract getLoginCommand(String[] params){
		return new LoginCommand();
	}
}
