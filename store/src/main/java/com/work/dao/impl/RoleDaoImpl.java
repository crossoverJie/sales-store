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

import com.work.dao.RoleDao;
import com.work.entity.Role;
import com.work.util.Page;
import com.work.util.StringUtil;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Role load(int id) {
		return (Role) this.getCurrentSession().load(Role.class, id);
	}

	public Role get(int id) {
		// TODO Auto-generated method stub
		return (Role) this.getCurrentSession().get(Role.class, id);
	}

	public List<Role> findAll() {
		List<Role> list = new ArrayList<Role>() ;
		list = this.getCurrentSession().createQuery("from Role ").list();
		return list ;
	}

	public void persist(Role entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public int save(Role entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Role entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Role Role = this.load(id) ;
		this.getCurrentSession().delete(Role);
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
	public Page<Role> findByParams(Role Role, int page, int rows) {
		Criteria criteria=getCurrentSession().createCriteria(Role.class);
		int open = (page-1)*rows ;
		int end = page*rows;
		Page<Role> p = new Page<Role>() ;
		String role_name =Role.getRole_name() ;
		String remark = Role.getRemark() ;
		if(!StringUtil.isNullOrEmpty(role_name)){
			criteria.add(Restrictions.like("role_name", role_name, MatchMode.ANYWHERE).ignoreCase());
		}
		if(!StringUtil.isNullOrEmpty(remark)){
			criteria.add(Restrictions.like("remark", remark, MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.setFirstResult(open);
		criteria.setMaxResults(end) ;
		List<Role> list = criteria.list() ;
		p.setRows(list);
		p.setPageNo(page);
		p.setLimit(rows);
		int total = this.findAll().size() ;
		p.setTotal(total); ;
		return p;
	}

	public int findAllCount(Role Role) {
		Criteria criteria=getCurrentSession().createCriteria(Role.class);
		String role_name =Role.getRole_name() ;
		int id = Role.getId();
		String remark = Role.getRemark() ;
		if(!StringUtil.isNullOrEmpty(role_name)){
			criteria.add(Restrictions.eq("role_name", role_name));
		}
		if(!StringUtil.isNullOrEmpty(id)){
			criteria.add(Restrictions.eq("id", id));
		}
		if(!StringUtil.isNullOrEmpty(remark)){
			criteria.add(Restrictions.eq("remark", remark));
		}
		List<Role> list = criteria.list() ;
		return list.size();
	}

	public void update(Role entity) {
		Role role = this.get(entity.getId()) ;
		if(entity.getRemark() != null){
			role.setRemark(entity.getRemark());
		}
		if(entity.getRole_name() != null){
			role.setRole_name(entity.getRole_name());
		}
		if(entity.getFunction_id() != null){
			role.setFunction_id(entity.getFunction_id());
		}
		this.getCurrentSession().update(role); 
	}


	

}
