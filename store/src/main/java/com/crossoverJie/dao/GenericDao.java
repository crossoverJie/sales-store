package com.crossoverJie.dao;

import java.io.Serializable;
import java.util.List;

import com.crossoverJie.entity.User;
import com.crossoverJie.util.Page;

public interface GenericDao<T, PK extends Serializable> {

	T load(int id);
	
	T get(int id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	int save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(int id);
	
	void flush();
	
	T findByLogin(T entity) ;
	
	/**
	 * 
	 * @Description: 分页
	 * @param @param user
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return Page<T>  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月11日  上午12:46:16
	 */
	Page<T> findByParams(User user, int page, int rows) ;
}
