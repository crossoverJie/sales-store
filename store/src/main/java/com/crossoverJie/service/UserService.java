package com.crossoverJie.service;

import java.util.List;

import com.crossoverJie.entity.User;
import com.crossoverJie.util.Page;

public interface UserService {
	User load(int id);

	User get(int id);

	List<User> findAll();

	void persist(User entity);

	int save(User entity);

	void saveOrUpdate(User entity);

	void delete(int id);

	void flush();
	
	User findLogin(User entity) ;

	Page<User> findByParams(User user, int page, int rows);
}
