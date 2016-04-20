package com.work.dao.impl;

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

import com.work.dao.UserDao;
import com.work.entity.User;
import com.work.util.Page;
import com.work.util.StringUtil;

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
		String role_id = user.getRole_id() ;
		String provice = user.getProvince() ;
		if(!StringUtil.isNullOrEmpty(username)){
			criteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(realname)){
			criteria.add(Restrictions.like("realname", realname, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(role_id)){
			criteria.add(Restrictions.like("role_id", role_id, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(provice)){
			criteria.add(Restrictions.eq("province", provice));
		}
		
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<User> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAllCountLike(user) ;
		p.setTotal(total); ;
		
		return p;
	}

	public int findAllCountLike(User user) {
		Criteria criteria=getCurrentSession().createCriteria(User.class);
		String username =user.getUsername() ;
		String realname = user.getRealname() ;
		String role_id = user.getRole_id() ;
		if(!StringUtil.isNullOrEmpty(username)){
			criteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(realname)){
			criteria.add(Restrictions.like("realname", realname, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(role_id)){
			criteria.add(Restrictions.like("role_id", role_id, MatchMode.ANYWHERE).ignoreCase());
		}
		List<User> list = criteria.list() ;
		return list.size();
	}
	
	public int findAllCount(User user) {
		Criteria criteria=getCurrentSession().createCriteria(User.class);
		String username =user.getUsername() ;
		int id = user.getId();
		String password = user.getPassword() ;
		if(!StringUtil.isNullOrEmpty(username)){
			criteria.add(Restrictions.eq("username", username));
		}
		if(!StringUtil.isNullOrEmpty(id)){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(password)){
			criteria.add(Restrictions.eq("password", password));
		}
		List<User> list = criteria.list() ;
		return list.size();
	}

	public void update(User entity) {
		User user = this.get(entity.getId()) ;
		if(entity.getUsername() != null){
			user.setUsername(entity.getUsername());
		}
		if(entity.getRealname() != null){
			user.setRealname(entity.getRealname());
		}
		if(entity.getLogin_date()!= null){
			user.setLogin_date(entity.getLogin_date());
		}
		if(entity.getPassword()!= null){
			user.setPassword(entity.getPassword() );
		}
		if(entity.getProvince()!= null){
			user.setProvince(entity.getProvince());
		}
		if(entity.getRole_id()!= null){
			user.setRole_id(entity.getRole_id());
		}
		
		this.getCurrentSession().update(user); 
	}

}
