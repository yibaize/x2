package org.baize.bean;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：封装表中一个字段信息
 */
public class ColumnInfo {
    /**字段名称*/
    private String name;
    /**字段类型*/
    private String dataType;
    /**字段的建类型 0 普通建，1主键 2外建*/
    private int keyType;

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }
}
