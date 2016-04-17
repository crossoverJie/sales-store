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

import com.work.dao.AchatDao;
import com.work.entity.Achat;
import com.work.util.Page;
import com.work.util.StringUtil;

@Repository("achatDao")
public class AchatDaoImpl implements AchatDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Achat load(int id) {
		return (Achat) this.getCurrentSession().load(Achat.class, id);
	}

	public Achat get(int id) {
		// TODO Auto-generated method stub
		return (Achat) this.getCurrentSession().get(Achat.class, id);
	}

	public List<Achat> findAll() {
		List<Achat> list = new ArrayList<Achat>() ;
		list = this.getCurrentSession().createQuery("from Achat ").list();
		return list ;
	}

	public void persist(Achat entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(Achat entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Achat entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Achat function = this.get(id) ;
		this.getCurrentSession().delete(function);
	}

	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}


	/**
	 * page是第几页 
	 * row是一页显示多少条数据
	 */
	@SuppressWarnings("unchecked")
	public Page<Achat> findByParams(Achat Achat, int page, int rows) {
		Criteria criteria=getCurrentSession().createCriteria(Achat.class);
		int open = (page-1)*rows ;
		int end = page*rows;
		Page<Achat> p = new Page<Achat>() ;
		String title =Achat.getTitle() ;
		String content = Achat.getContent() ;
		if(!StringUtil.isNullOrEmpty(title)){
			criteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(content)){
			criteria.add(Restrictions.like("content", content, MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<Achat> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAll().size() ;
		p.setTotal(total); 
		return p;
	}

	public int findAllCount(Achat achat) {
		Criteria criteria=getCurrentSession().createCriteria(Achat.class);
		String title =achat.getTitle() ;
		int id = achat.getId();
		String create_user = achat.getCreate_user() ;
		if(!StringUtil.isNullOrEmpty(title)){
			criteria.add(Restrictions.eq("title", title));
		}
		if(!StringUtil.isNullOrEmpty(id)){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(create_user)){
			criteria.add(Restrictions.eq("create_user", create_user));
		}
		List<Achat> list = criteria.list() ;
		return list.size();
	}

	public void update(Achat entity) {
		Achat category = this.get(entity.getId()) ;
		if(entity.getTitle() != null){
			category.setTitle(entity.getTitle());
		}
		if(entity.getContent() != null){
			category.setContent(entity.getContent());
		}
		if(entity.getContent() != null){
			category.setContent(entity.getContent());
		}
		if(entity.getState() != null){
			category.setState(entity.getState());
		}
		this.getCurrentSession().update(category); 
	}


	@SuppressWarnings("unchecked")
	public List<Achat> findAll(Achat achat) {
		Criteria criteria=getCurrentSession().createCriteria(Achat.class);
		String title =achat.getTitle() ;
		int id = achat.getId();
		String create_user = achat.getCreate_user() ;
		String state = achat.getState() ;
		if(!StringUtil.isNullOrEmpty(title)){
			criteria.add(Restrictions.eq("title", title));
		}
		if(id !=0){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(create_user)){
			criteria.add(Restrictions.eq("create_user", create_user));
		}
		if(!StringUtil.isNullOrEmpty(create_user)){
			criteria.add(Restrictions.eq("create_user", create_user));
		}
		if(!StringUtil.isNullOrEmpty(state)){
			criteria.add(Restrictions.eq("state", state));
		}
		List<Achat> list = criteria.list() ;
		return list;
	}


	

}
