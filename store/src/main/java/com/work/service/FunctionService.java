package com.work.service;

import java.util.List;

import com.work.entity.Function;
import com.work.util.Page;

public interface FunctionService {
	Function load(int id);

	Function get(int id);

	List<Function> findAll();

	void persist(Function entity);

	int save(Function entity);

	void saveOrUpdate(Function entity);

	void delete(int id);

	void flush();

	Page<Function> findByParams(Function Function, int page, int rows);

	int findAllCount(Function u);

	void update(Function Function);
}
