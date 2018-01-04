package org.baize.core;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：负责java类型和数据库类型的相互转换
 */
public interface TypeConvertor {
    /**
     * 数据库转java类型
     * @param columnType
     * @return
     */
    String databaseType2JavaType(String columnType);

    /**
     * java转数据库类型
     * @param javaDataType
     * @return
     */
    String javaType2DatabaseType(String javaDataType);
}
