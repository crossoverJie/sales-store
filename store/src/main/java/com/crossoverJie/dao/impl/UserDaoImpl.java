package com.crossoverJie.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crossoverJie.dao.UserDao;
import com.crossoverJie.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public User load(int id) {
		return (User) this.getCurrentSession().load(User.class, id);
	}

	public User get(int id) {
		// TODO Auto-generated method stub
		return (User) this.getCurrentSession().get(User.class, id);
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<User>() ;
		list = this.getCurrentSession().createQuery("from user ").list();
		return list ;
	}

	public void persist(User entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(User entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		User user = this.load(id) ;
		this.getCurrentSession().delete(user);
	}

	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

}
