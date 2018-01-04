package org.baize.bean;

/**
 * 作者： 白泽
 * 时间： 2018/1/3.
 * 描述：封装了java属性和get set的源代码
 */
public class JavaFieldGetSet {
    /**属性的源码信息 如 private int id*/
    private String fieldInfo;
    /**get方法的源码信息 如public int getId（）{}*/
    private String getInfo;
    /**et方法的源码信息 如public void setId（int id）{}*/
    private String setInfo;

    public JavaFieldGetSet() {
    }

    public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

    @Override
    public String toString() {
        System.out.println(fieldInfo);
        System.out.println(getInfo);
        System.out.println(setInfo);
        return "";
    }
}
