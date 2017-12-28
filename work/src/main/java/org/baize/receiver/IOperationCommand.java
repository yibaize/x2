package org.baize.receiver;

import org.baize.message.IProtostuff;

/**
 * 作者： 白泽
 * 时间： 2017/12/28.
 * 描述：
 */
public interface IOperationCommand extends Runnable{
    IProtostuff execute();
    default void broadcast(){}
}
