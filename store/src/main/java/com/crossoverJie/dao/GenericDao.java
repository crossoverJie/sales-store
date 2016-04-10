package com.crossoverJie.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

	T load(int id);
	
	T get(int id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	int save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(int id);
	
	void flush();
	
}
