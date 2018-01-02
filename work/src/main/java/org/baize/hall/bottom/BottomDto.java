package org.baize.hall.bottom;

import org.baize.assemblybean.annon.Protostuff;
import org.baize.message.IProtostuff;

import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2018/1/2.
 * 描述：
 */
@Protostuff
public class BottomDto implements IProtostuff{
    private Map<Integer,Long> self;
    private Map<Integer,Long> other;

    public BottomDto(Map<Integer, Long> self, Map<Integer, Long> other) {
        this.self = self;
        this.other = other;
    }

    public Map<Integer, Long> getSelf() {
        return self;
    }

    public Map<Integer, Long> getOther() {
        return other;
    }
}
