package org.baize.receiver;

import org.baize.login.Login;

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
				return login(params);
			default:
				return null;
		}
	}
	private OperateCommandAbstract login(String[] pa){
		String account = pa[0];
		return new Login(account);
	}
}
