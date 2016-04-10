package com.crossoverJie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossoverJie.dao.UserDao;
import com.crossoverJie.entity.User;
import com.crossoverJie.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao ;

	public User load(String id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	public User get(String id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public void persist(User entity) {
		// TODO Auto-generated method stub
		userDao.persist(entity);
	}

	public String save(User entity) {
		// TODO Auto-generated method stub
		return userDao.save(entity);
	}

	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	public void delete(String id) {
		userDao.delete(id);
	}

	public void flush() {
		// TODO Auto-generated method stub
		userDao.flush();
	}
	
	

}
