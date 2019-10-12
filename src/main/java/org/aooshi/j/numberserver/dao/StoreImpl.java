package org.aooshi.j.numberserver.dao;

import java.util.Date;
import java.util.List;

import org.aooshi.j.numberserver.util.ActionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoreImpl implements IStore {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(String id, Long value) {
        Date date = new Date();
        int rows = 0;
        try {
            Object[] obj = new Object[]{id, value, date};
            String sql = "insert into jnumberserver_store(id,v,createtime) values(?,?,?)";
            rows = jdbcTemplate.update(sql, obj);
        } catch (DuplicateKeyException e) {
            //String message = "id exists";
            return ActionCode.ID_EXISTS;
        }

        if (rows > 0)
            return ActionCode.OK;

        return ActionCode.DB_EXECUTE_FAILURE;
    }

    @Override
    public int update(String id, Long value) {
        Object[] obj = new Object[]{value, id};
        String sql = "update jnumberserver_store set v=? where id=?";
        jdbcTemplate.update(sql, obj);
        return ActionCode.OK;
    }

    @Override
    public int delete(String id) {
        int rows = 0;
        Object[] obj = new Object[]{id};
        String sql = "delete from jnumberserver_store where id=?";
        rows = jdbcTemplate.update(sql, obj);
        if (rows > 0)
            return ActionCode.OK;

        return ActionCode.ID_NOT_EXISTS;
    }

    @Override
    public List<Long> get(String id) {
        Object[] obj = new Object[]{id};
        String sql = "select v from jnumberserver_store where id=? limit 1";
        List<Long> list = jdbcTemplate.queryForList(sql, obj, Long.class);
        return list;
    }

    @Override
    @Transactional
    public List<Long> increment(String id, Integer step) {
        List<Long> list = null;
        Object[] obj = new Object[]{step, id};
        String sql = "update jnumberserver_store set v=v+? where id=?";
        int rows = jdbcTemplate.update(sql, obj);
        if (rows == 0)
            return list;

        Object[] obj1 = new Object[]{id};
        String sql1 = "select v from jnumberserver_store where id=? limit 1";
        list = jdbcTemplate.queryForList(sql1, obj1, Long.class);

        return list;
    }

    @Override
    @Transactional
    public List<Long> decrement(String id, Integer step) {

        List<Long> list = null;
        Object[] obj = new Object[]{step, id};
        String sql = "update jnumberserver_store set v=v-? where id=?";
        int rows = jdbcTemplate.update(sql, obj);
        if (rows == 0)
            return list;

        Object[] obj1 = new Object[]{id};
        String sql1 = "select v from jnumberserver_store where id=? limit 1";
        list = jdbcTemplate.queryForList(sql1, obj1, Long.class);

        return list;
    }

    @Override
    @Transactional
    public long incrementOrAdd(String id, Integer step, Long defaultValue) {
        Object[] obj = new Object[]{step, id};
        String sql = "update jnumberserver_store set v=v+? where id=?";
        int rows = jdbcTemplate.update(sql, obj);
        if (rows == 0) {
            rows = jdbcTemplate.update("insert into jnumberserver_store(id,v,createtime) values(?,?,?)",
                    id, defaultValue, new Date());
        }
        obj = new Object[]{id};
        sql = "select v from jnumberserver_store where id=? limit 1";
        List<Long> list = jdbcTemplate.queryForList(sql, obj, Long.class);
        return list.get(0);
    }

    @Override
    @Transactional
    public long decrementOrAdd(String id, Integer step, Long defaultValue) {
        Object[] obj = new Object[]{step, id};
        String sql = "update jnumberserver_store set v=v-? where id=?";
        int rows = jdbcTemplate.update(sql, obj);
        if (rows == 0) {
            rows = jdbcTemplate.update("insert into jnumberserver_store(id,v,createtime) values(?,?,?)",
                    id, defaultValue, new Date());
        }
        obj = new Object[]{id};
        sql = "select v from jnumberserver_store where id=? limit 1 ";
        List<Long> list = jdbcTemplate.queryForList(sql, obj, Long.class);
        return list.get(0);
    }

    @Override
    @Transactional
    public Long getOrAdd(String id, Long defaultValue) {
        Object[] obj = new Object[]{id};
        String sql = "update jnumberserver_store set v=v+0 where id=?";
        jdbcTemplate.update(sql, obj);

        String sql1 = "select v from jnumberserver_store where id=? limit 1";
        List<Long> list = jdbcTemplate.queryForList(sql1, obj, Long.class);

        if (list == null || list.size() == 0) {
            jdbcTemplate.update("insert into jnumberserver_store(id,v,createtime) values(?,?,?)",
                    id, defaultValue, new Date());

            return defaultValue;
        } else {
            return list.get(0);
        }
    }
}
