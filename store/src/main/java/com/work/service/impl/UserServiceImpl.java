package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.UserDao;
import com.work.entity.User;
import com.work.service.UserService;
import com.work.util.Page;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao ;

	public User load(int id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	public User get(int id) {
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

	public int save(User entity) {
		// TODO Auto-generated method stub
		return userDao.save(entity);
	}

	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void flush() {
		userDao.flush();
	}

	public User findLogin(User entity) {
		return userDao.findByLogin(entity);
	}

	public Page<User> findByParams(User user, int page, int rows) {
		
		return userDao.findByParams(user, page, rows) ;
		
	}

	public int findAllCount(User u) {
		return userDao.findAllCount(u);
	}
	
	

}
