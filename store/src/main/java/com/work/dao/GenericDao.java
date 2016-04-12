package com.work.dao;

import java.io.Serializable;
import java.util.List;

import com.work.entity.User;
import com.work.util.Page;

public interface GenericDao<T, PK extends Serializable> {

	T load(int id);
	
	T get(int id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	int save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(int id);
	
	void flush();
	
	
	
	/**
	 * 
	 * @Description: 分页
	 * @param @param user
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return Page<T>  
	 * @throws
	 * @author work
	 * @date 2016年4月11日  上午12:46:16
	 */
	Page<T> findByParams(T entity, int page, int rows) ;
	
	void update(T entity) ;
	int findAllCount(T entity) ;
}
