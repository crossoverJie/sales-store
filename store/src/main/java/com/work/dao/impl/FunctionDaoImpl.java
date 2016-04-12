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

import com.work.dao.FunctionDao;
import com.work.entity.Function;
import com.work.util.Page;
import com.work.util.StringUtil;

@Repository("functionDao")
public class FunctionDaoImpl implements FunctionDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Function load(int id) {
		return (Function) this.getCurrentSession().load(Function.class, id);
	}

	public Function get(int id) {
		// TODO Auto-generated method stub
		return (Function) this.getCurrentSession().get(Function.class, id);
	}

	public List<Function> findAll() {
		List<Function> list = new ArrayList<Function>() ;
		list = this.getCurrentSession().createQuery("from Function ").list();
		return list ;
	}

	public void persist(Function entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(Function entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Function entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Function function = this.get(id) ;
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
	public Page<Function> findByParams(Function Function, int page, int rows) {
		Criteria criteria=getCurrentSession().createCriteria(Function.class);
		int open = (page-1)*rows ;
		int end = page*rows;
		Page<Function> p = new Page<Function>() ;
		String Function_name =Function.getFunction_name() ;
		String remark = Function.getRemark() ;
		if(!StringUtil.isNullOrEmpty(Function_name)){
			criteria.add(Restrictions.like("Function_name", Function_name, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(remark)){
			criteria.add(Restrictions.like("remark", remark, MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<Function> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAll().size() ;
		p.setTotal(total); ;
		return p;
	}

	public int findAllCount(Function function) {
		Criteria criteria=getCurrentSession().createCriteria(Function.class);
		String Function_name =function.getFunction_name() ;
		int id = function.getId();
		String remark = function.getRemark() ;
		if(!StringUtil.isNullOrEmpty(Function_name)){
			criteria.add(Restrictions.eq("Function_name", Function_name));
		}
		if(!StringUtil.isNullOrEmpty(id)){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(remark)){
			criteria.add(Restrictions.eq("remark", remark));
		}
		List<Function> list = criteria.list() ;
		return list.size();
	}

	public void update(Function entity) {
		Function function = this.get(entity.getId()) ;
		if(entity.getRemark() != null){
			function.setRemark(entity.getRemark());
		}
		if(entity.getFunction_name() != null){
			function.setFunction_name(entity.getFunction_name());
		}
		if(entity.getFunction_url() != null){
			function.setFunction_url(entity.getFunction_url());
		}
		this.getCurrentSession().update(function); 
	}


	@SuppressWarnings("unchecked")
	public List<Function> findAll(Function function) {
		Criteria criteria=getCurrentSession().createCriteria(Function.class);
		String Function_name =function.getFunction_name() ;
		int id = function.getId()==0?0:function.getId();
		int parent_id = function.getParent_id()==null?0:function.getParent_id() ;
		String remark = function.getRemark() ;
		if(!StringUtil.isNullOrEmpty(Function_name)){
			criteria.add(Restrictions.eq("Function_name", Function_name));
		}
		if(id!=0){
			criteria.add(Restrictions.eq("id", id));
		}
		if(parent_id!=0){
			criteria.add(Restrictions.eq("parent_id", parent_id));
		}
		if(!StringUtil.isNullOrEmpty(remark)){
			criteria.add(Restrictions.eq("remark", remark));
		}
		List<Function> list = criteria.list() ;
		return list;
	}

	/**
	 * 根据父节点删除ID
	 */
	public void deleteByPrentId(int parseInt) {
		Function f = new Function() ;
		f.setParent_id(parseInt);
		this.getCurrentSession().delete(f); 
	}

	

}
