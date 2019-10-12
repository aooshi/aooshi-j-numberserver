package org.aooshi.j.numberserver.dao;

import java.util.List;

public interface IStore {

    /**
     * 添加一个标识，如果已存在则将失败
     *
     * @param id
     * @return
     */
    int add(String id, Long value);

    /**
     * 将一个标识值置换成为新值
     *
     * @param id
     * @param value
     * @return
     */
    int update(String id, Long value);

    /**
     * 删除一个标识
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取一个标识（不会引发标识的值变化）
     *
     * @param id
     * @return
     */
    List<Long> get(String id);

    /**
     * @param id
     * @param step
     * @return
     */
    List<Long> increment(String id, Integer step);

    /**
     * @param id
     * @param step
     * @return
     */
    List<Long> decrement(String id, Integer step);


    /**
     * 获取一个标识（不会引发标识的值变化）
     *
     * @param id
     * @param defaultValue
     * @return
     */
    Long getOrAdd(String id, Long defaultValue);

    /**
     * @param id
     * @param defaultValue
     * @param step
     * @return
     */
    long incrementOrAdd(String id, Integer step, Long defaultValue);

    /**
     * @param id
     * @param defaultValue
     * @param step
     * @return
     */
    long decrementOrAdd(String id, Integer step, Long defaultValue);
}
