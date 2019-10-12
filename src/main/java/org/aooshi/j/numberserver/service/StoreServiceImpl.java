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
	public int add(String id, Long value) {
		return store.add(id, value);
	}

	@Override
	public int update(String id, Long value) {
		return store.update(id, value);
	}

	@Override
	public int delete(String id) {
		return store.delete(id);
	}

	@Override
	public List<Long> get(String id) {
		return store.get(id);
	}
	
	@Override
	public List<Long> increment(String id, Integer step) {
		return store.increment(id, step);
	}

	@Override
	public List<Long> decrement(String id, Integer step) {
		return store.decrement(id, step);
	}

	@Override
	public long incrementOrAdd(String id, Integer step, Long defaultValue) {
		return store.incrementOrAdd(id, step, defaultValue);
	}

	@Override
	public long decrementOrAdd(String id, Integer step, Long defaultValue) {
		return store.decrementOrAdd(id, step, defaultValue);
	}

	@Override
	public Long getOrAdd(String id, Long defaultValue) {
		return store.getOrAdd(id,defaultValue);
	}

}
