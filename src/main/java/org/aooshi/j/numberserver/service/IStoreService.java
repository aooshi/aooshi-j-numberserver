package org.aooshi.j.numberserver.service;

import java.util.List;

public interface IStoreService
{
	/**
	 *  添加一个标识，如果已存在则将失败
	 * @param id
	 * @return
	 */
    int add(long id, long value);
    
    /**
	 * 
     * 将一个标识值置换成为新值
     * @param id
     * @param value
     * @return
     */
    int update(long id, long value);
    
    /**
	 * 
     * 删除一个标识
     * @param id
     * @return
     */
    int delete(long id);

    /**
	 * 
     * 获取一个标识（不会引发标识的值变化）
     * @param id
     * @return
     */
    List<Long> get(long id);
    /**
     * 获取一个标识（不会引发标识的值变化）
     * @param id
     * @param defaultValue
     * @return
     */
    Long getOrAdd(long id,long defaultValue);


    /**
     * 
     * @param id
     * @param step
     * @return
     */
    List<Long> increment(long id,int step);

    /**
     * @param id
     * @param step
     * @return
     */
    List<Long> decrement(long id,int step);
    
    /**
	 * 
     * @param id
     * @param defaultValue
     * @param step
     * @return
     */
    long incrementOrAdd(long id,int step,long defaultValue);

    /**
     * 
     * @param id
     * @param defaultValue
     * @param step
     * @return
     */
    long decrementOrAdd(long id,int step,long defaultValue);
    
}
