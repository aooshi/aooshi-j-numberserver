package org.aooshi.j.numberserver.service;

import java.util.List;

import org.aooshi.j.numberserver.dao.IStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreServiceImpl implements IStoreService {

    @Autowired
    IStore store;
	
	@Override
	public int add(long id, long value) {
		return store.add(id, value);
	}

	@Override
	public int update(long id, long value) {
		return store.update(id, value);
	}

	@Override
	public int delete(long id) {
		return store.delete(id);
	}

	@Override
	public List<Long> get(long id) {
		return store.get(id);
	}
	
	@Override
	public List<Long> increment(long id, int step) {
		return store.increment(id, step);
	}

	@Override
	public List<Long> decrement(long id, int step) {
		return store.decrement(id, step);
	}

	@Override
	public long incrementOrAdd(long id, int step, long defaultValue) {
		return store.incrementOrAdd(id, step, defaultValue);
	}

	@Override
	public long decrementOrAdd(long id, int step, long defaultValue) {
		return store.decrementOrAdd(id, step, defaultValue);
	}

	@Override
	public Long getOrAdd(long id, long defaultValue) {
		return store.getOrAdd(id,defaultValue);
	}

}
