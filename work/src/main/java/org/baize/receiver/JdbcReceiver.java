package org.baize.receiver;


import org.baize.maseegehandler.JdbcTemplate;
import org.baize.model.JdbcModel;
import org.baize.player.PlayerEntity;

/**
 * 作者： 白泽
 * 时间： 2017/12/26.
 * 描述：
 */
public class JdbcReceiver {
    private final JdbcTemplate jdbcTemplate;
    private static JdbcReceiver instance;
    public static JdbcReceiver getInstance(){
        if(instance == null)
            instance = new JdbcReceiver();
        return instance;
    }
    public JdbcReceiver() {
        this.jdbcTemplate = new JdbcTemplate(new PlayerEntity());
    }
    public void commit(JdbcModel model){
        jdbcTemplate.commit(model);
    }
    public JdbcModel select(JdbcModel model){
        return jdbcTemplate.select(model);
    }
    public void insert(JdbcModel model){
        jdbcTemplate.insert(model);
    }
}
