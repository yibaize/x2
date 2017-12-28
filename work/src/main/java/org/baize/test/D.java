package org.baize.test;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class D extends JdbcModel{
    private String lk = "ASFDASDasdap;sdjpasdkjap;sdas";
    public String getLk() {
        return lk;
    }
    public void setLk(String lk) {
        this.lk = lk;
    }

    @Override
    public String toString() {
        return "D{" +
                "lk='" + lk + '\'' +
                ", id=" + id +
                '}';
    }
}
