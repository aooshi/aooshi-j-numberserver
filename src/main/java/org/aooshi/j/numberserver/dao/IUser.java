package org.aooshi.j.numberserver.dao;

import org.aooshi.j.numberserver.domain.User;

public interface IUser {
	
    User findUserByUid(int uid);
}
