package com.crossoverJie.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crossoverJie.dao.UserDao;
import com.crossoverJie.entity.User;
import com.crossoverJie.util.Page;
import com.crossoverJie.util.StringUtil;

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
		list = this.getCurrentSession().createQuery("from User ").list();
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

	public User findByLogin(User entity) {
		List<User> list = this.getCurrentSession().
				createQuery(" from User  "
						+ "where username=? and password=?"
						).setParameter(0, entity.getUsername())
						.setParameter(1, entity.getPassword())
				.list();
		if(list.size() == 0){
			return null ;
		}else{
			return list.get(0) ;
		}
	}

	/**
	 * page是第几页 
	 * row是一页显示多少条数据
	 */
	@SuppressWarnings("unchecked")
	public Page<User> findByParams(User user, int page, int rows) {
		Criteria criteria=getCurrentSession().createCriteria(User.class);
		int open = (page-1)*rows ;
		int end = page*rows;
		Page<User> p = new Page<User>() ;
		String username =user.getUsername() ;
		String realname = user.getRealname() ;
		if(!StringUtil.isNullOrEmpty(username)){
			criteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(realname)){
			criteria.add(Restrictions.like("realname", realname, MatchMode.ANYWHERE).ignoreCase());
		}
		
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<User> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAll().size() ;
		p.setTotal(total); ;
		
		return p;
	}

	public int findAllCount(User user) {
		Criteria criteria=getCurrentSession().createCriteria(User.class);
		String username =user.getUsername() ;
		if(!StringUtil.isNullOrEmpty(username)){
			criteria.add(Restrictions.eq("username", username));
		}
		List<User> list = criteria.list() ;
		return list.size();
	}

}
