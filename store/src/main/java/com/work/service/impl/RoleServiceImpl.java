package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.RoleDao;
import com.work.entity.Role;
import com.work.service.RoleService;
import com.work.util.Page;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao RoleDao ;

	public Role load(int id) {
		// TODO Auto-generated method stub
		return RoleDao.load(id);
	}

	public Role get(int id) {
		// TODO Auto-generated method stub
		return RoleDao.get(id);
	}

	public List<Role> findAll() {
		return RoleDao.findAll();
	}

	public void persist(Role entity) {
		// TODO Auto-generated method stub
		RoleDao.persist(entity);
	}

	public int save(Role entity) {
		// TODO Auto-generated method stub
		return RoleDao.save(entity);
	}

	public void saveOrUpdate(Role entity) {
		RoleDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		RoleDao.delete(id);
	}

	public void flush() {
		RoleDao.flush();
	}

	public Role findLogin(Role entity) {
		return RoleDao.findByLogin(entity);
	}

	public Page<Role> findByParams(Role Role, int page, int rows) {
		return RoleDao.findByParams(Role, page, rows) ;
	}

	public int findAllCount(Role u) {
		return RoleDao.findAllCount(u);
	}

	public void update(Role Role) {
		RoleDao.update(Role);
	}
	
	

}
