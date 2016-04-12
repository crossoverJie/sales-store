package com.work.dao;

import com.work.entity.User;

public interface UserDao extends GenericDao<User,String> {
	User findByLogin(User entity) ;
}
