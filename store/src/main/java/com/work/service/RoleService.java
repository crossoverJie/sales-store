package com.work.service;

import java.util.List;

import com.work.entity.Role;
import com.work.util.Page;

public interface RoleService {
	Role load(int id);

	Role get(int id);

	List<Role> findAll();

	void persist(Role entity);

	int save(Role entity);

	void saveOrUpdate(Role entity);

	void delete(int id);

	void flush();

	Page<Role> findByParams(Role Role, int page, int rows);

	int findAllCount(Role u);

	void update(Role Role);
}
