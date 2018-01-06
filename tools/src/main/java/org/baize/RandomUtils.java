package org.baize;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public class RandomUtils {
    /**
     * 返回随机的index，[0,size-1]
     *
     * @param size
     * @return
     */
    public static int randomIndex(int size) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("无法获取index，数组越界！");
        }
        return ThreadLocalRandom.current().nextInt(size);
    }
}
