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

import com.work.dao.CategoryDao;
import com.work.entity.Category;
import com.work.util.Page;
import com.work.util.StringUtil;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Category load(int id) {
		return (Category) this.getCurrentSession().load(Category.class, id);
	}

	public Category get(int id) {
		// TODO Auto-generated method stub
		return (Category) this.getCurrentSession().get(Category.class, id);
	}

	public List<Category> findAll() {
		List<Category> list = new ArrayList<Category>() ;
		list = this.getCurrentSession().createQuery("from Category ").list();
		return list ;
	}

	public void persist(Category entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(Category entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Category entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Category function = this.get(id) ;
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
	public Page<Category> findByParams(Category Category, int page, int rows) {
//		Criteria criteria=getCurrentSession().createCriteria(Category.class);
//		int open = (page-1)*rows ;
//		int end = page*rows;
//		Page<Category> p = new Page<Category>() ;
//		String Category_name =Category.getCategory_name() ;
//		String remark = Category.getRemark() ;
//		if(!StringUtil.isNullOrEmpty(Category_name)){
//			criteria.add(Restrictions.like("Category_name", Category_name, MatchMode.ANYWHERE).ignoreCase());
//		}
//		if(!StringUtil.isNullOrEmpty(remark)){
//			criteria.add(Restrictions.like("remark", remark, MatchMode.ANYWHERE).ignoreCase());
//		}
//		criteria.setFirstResult(open);
//		criteria.setMaxResults(end) ;
//		List<Category> list = criteria.list() ;
//		p.setRows(list);
//		p.setPageNo(page);
//		p.setLimit(rows);
//		int total = this.findAll().size() ;
//		p.setTotal(total); ;
		return null;
	}

	public int findAllCount(Category produce) {
		Criteria criteria=getCurrentSession().createCriteria(Category.class);
		String name =produce.getName() ;
		int id = produce.getId();
		String user_id = produce.getUser_id() ;
		if(!StringUtil.isNullOrEmpty(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		if(!StringUtil.isNullOrEmpty(id)){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(user_id)){
			criteria.add(Restrictions.eq("user_id", user_id));
		}
		List<Category> list = criteria.list() ;
		return list.size();
	}

	public void update(Category entity) {
		Category produce = this.get(entity.getId()) ;
		if(entity.getName() != null){
			produce.setName(entity.getName());
		}
		if(entity.getParent_id() != 0){
			produce.setParent_id(entity.getParent_id());
		}
		if(entity.getLevel() != null){
			produce.setLevel(entity.getLevel());
		}
		if(entity.getUser_id() != null){
			produce.setUser_id(entity.getUser_id());
		}
		this.getCurrentSession().update(produce); 
	}


	@SuppressWarnings("unchecked")
	public List<Category> findAll(Category category) {
		Criteria criteria=getCurrentSession().createCriteria(Category.class);
		String name =category.getName() ;
		int id = category.getId();
		String user_id = category.getUser_id() ;
		String level = category.getLevel() ;
		int parent_id = category.getParent_id() ;
		if(!StringUtil.isNullOrEmpty(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		if(id !=0){
			criteria.add(Restrictions.eq("id", id));
		}
		if(parent_id !=0){
			criteria.add(Restrictions.eq("parent_id", parent_id));
		}
		if(!StringUtil.isNullOrEmpty(user_id)){
			criteria.add(Restrictions.eq("user_id", user_id));
		}
		if(!StringUtil.isNullOrEmpty(level)){
			criteria.add(Restrictions.eq("level", level));
		}
		List<Category> list = criteria.list() ;
		return list;
	}

	/**
	 * 根据父节点删除ID
	 */
	public void deleteByPrentId(int parseInt) {
		Category p = new Category() ;
		p.setParent_id(parseInt);
		this.getCurrentSession().delete(p); 
	}

	

}
