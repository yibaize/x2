package org.baize.ransitshipment;


import org.baize.manager.Request;
import org.baize.session.ISession;

/**
 * 作者： 白泽
 * 时间： 2017/12/26.
 * 描述：
 */
public interface IRepeater {
    void massegeRansiter(ISession session, Request request);
}
