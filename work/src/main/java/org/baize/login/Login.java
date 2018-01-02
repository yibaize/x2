package org.baize.login;

import org.baize.assemblybean.annon.Protocol;
import org.baize.assemblybean.service.SelectAnnotationClass;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.hall.room.Room;
import org.baize.hall.room.RoomManager;
import org.baize.message.IProtostuff;
import org.baize.player.PlayerDataTable;
import org.baize.player.PlayerEntity;
import org.baize.player.PlayerOperation;
import org.baize.player.weath.Weath;
import org.baize.receiver.JdbcReceiver;
import org.baize.receiver.OperateCommandAbstract;
import org.baize.session.ISession;
import org.baize.session.SessionManager;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
@Protocol("1")
public class Login extends OperateCommandAbstract {
    private final String account;

    public Login(String account) {
        this.account = account;
    }

    @Override
    public IProtostuff execute() {
        ISession session = getSession();
        PlayerEntity entity = null;
        if(session != null){
            //回话已经登陆 抛异常
            new GenaryAppError(AppErrorCode.LOGIN_ERR);
        }else {
            entity = new PlayerEntity();
            entity.setAccount(account);
            //数据库查找
            entity = (PlayerEntity) JdbcReceiver.getInstance().select(entity);
            //数据库还没有
            if (entity == null) {
                //注册
                PlayerDataTable dataTable = PlayerDataTable.get(1);
                entity.setAccount(account);
                entity.setWeath(new Weath(dataTable.getGold(),dataTable.getDiamond()));
                JdbcReceiver.getInstance().insert(entity);
            }
        }
        if(SessionManager.isOnlinePlayer(account)){
            hasLogin(SessionManager.removeSession(account));
        }
        Room room = RoomManager.getInstance().getRoomById(1);
        //加入在线玩家会话
        PlayerOperation operation = new PlayerOperation(entity,session,room);
        if(SessionManager.putSession(account,session)){
            session.setAttachment(operation);
        }
        return entity.selfDto();
    }
    /**账号已经被登陆,踢老用户下线*/
    private void hasLogin(ISession session){
        session.removeAttachment();
        session.close();
    }

    public static void main(String[] args) {
        SelectAnnotationClass.getIocClazz("org.baize");
    }
}
