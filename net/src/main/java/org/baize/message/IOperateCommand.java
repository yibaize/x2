package org.baize.message;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public interface IOperateCommand extends Runnable {
    IProtostuff execute();
    default void broadcast(){}
}
