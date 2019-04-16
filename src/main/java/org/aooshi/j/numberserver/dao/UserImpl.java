package org.aooshi.j.numberserver.dao;

import java.util.List;

import org.aooshi.j.numberserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserImpl implements IUser {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findUserByUid(int uid) {
		
		//INSERT INTO `shouying`.`jnumberserver_user` (`uid`, `pwd`, `state`, `createtime`) 
		//VALUES (1,'7mMHi2KkjYWpDGMBaAbecXHYFBtPh6HE',0,now());
		
		String sql = "select * from jnumberserver_user where uid="+ uid +" limit 1";
		Object[] args = new Object[0];
		List<User> list = jdbcTemplate.query(sql,args, new BeanPropertyRowMapper<User>(User.class));
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
