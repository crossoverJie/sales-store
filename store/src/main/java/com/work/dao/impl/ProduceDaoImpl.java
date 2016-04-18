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

import com.work.dao.ProduceDao;
import com.work.entity.Produce;
import com.work.util.Page;
import com.work.util.StringUtil;

@Repository("produceDao")
public class ProduceDaoImpl implements ProduceDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Produce load(int id) {
		return (Produce) this.getCurrentSession().load(Produce.class, id);
	}

	public Produce get(int id) {
		// TODO Auto-generated method stub
		return (Produce) this.getCurrentSession().get(Produce.class, id);
	}

	public List<Produce> findAll() {
		List<Produce> list = new ArrayList<Produce>() ;
		list = this.getCurrentSession().createQuery("from Produce ").list();
		return list ;
	}

	public void persist(Produce entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(Produce entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Produce entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Produce function = this.get(id) ;
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
	public Page<Produce> findByParams(Produce produce, int page, int rows) {
		Criteria criteria=getCurrentSession().createCriteria(Produce.class);
		int open = (page-1)*rows ;
		int end = page*rows;
		Page<Produce> p = new Page<Produce>() ;
		String name =produce.getName() ;
		String user_id = produce.getUser_id() ;
		if(!StringUtil.isNullOrEmpty(name)){
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(user_id)){
			criteria.add(Restrictions.eq("user_id", user_id));
		}
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<Produce> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAllCount(produce) ;
		p.setTotal(total); 
		return p;
	}

	public int findAllCount(Produce produce) {
		Criteria criteria=getCurrentSession().createCriteria(Produce.class);
		String name =produce.getName() ;
		if(!StringUtil.isNullOrEmpty(name)){
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
		}
		List<Produce> list = criteria.list() ;
		return list.size();
	}

	public void update(Produce entity) {
		Produce category = this.get(entity.getId()) ;
		if(entity.getName() != null){
			category.setName(entity.getName());
		}
		if(entity.getUser_id() != null){
			category.setUser_id(entity.getUser_id());
		}
		if(entity.getCategory_id() != null){
			category.setCategory_id(entity.getCategory_id());
		}
		if(entity.getKucun_number() != 0){
			category.setKucun_number(entity.getKucun_number());
		}
		this.getCurrentSession().update(category); 
	}


	@SuppressWarnings("unchecked")
	public List<Produce> findAll(Produce produce) {
		Criteria criteria=getCurrentSession().createCriteria(Produce.class);
		String name =produce.getName() ;
		if(!StringUtil.isNullOrEmpty(name)){
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
		}
		List<Produce> list = criteria.list() ;
		return list;
	}


	

}
