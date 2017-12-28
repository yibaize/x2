package org.baize.receiver;


import org.baize.maseegehandler.JdbcTemplate;
import org.baize.model.JdbcModel;

/**
 * 作者： 白泽
 * 时间： 2017/12/26.
 * 描述：
 */
public class JdbcReceiver {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReceiver(JdbcModel model) {
        this.jdbcTemplate = new JdbcTemplate(model);
    }
    public void commit(JdbcModel model){
        jdbcTemplate.commit(model);
    }
}
