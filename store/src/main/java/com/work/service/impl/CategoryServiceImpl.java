package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.CategoryDao;
import com.work.entity.Category;
import com.work.service.CategoryService;
import com.work.util.Page;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao ;

	public Category load(int id) {
		// TODO Auto-generated method stub
		return categoryDao.load(id);
	}

	public Category get(int id) {
		// TODO Auto-generated method stub
		return categoryDao.get(id);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void persist(Category entity) {
		// TODO Auto-generated method stub
		categoryDao.persist(entity);
	}

	public int save(Category entity) {
		// TODO Auto-generated method stub
		return categoryDao.save(entity);
	}

	public void saveOrUpdate(Category entity) {
		categoryDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		categoryDao.delete(id);
	}

	public void flush() {
		categoryDao.flush();
	}


	public Page<Category> findByParams(Category Produce, int page, int rows) {
		return categoryDao.findByParams(Produce, page, rows) ;
	}

	public int findAllCount(Category u) {
		return categoryDao.findAllCount(u);
	}

	public void update(Category category) {
		categoryDao.update(category);
	}

	public List<Category> findAll(Category function) {
		return categoryDao.findAll(function);
	}

	public void deleteByPrentId(int parseInt) {
		categoryDao.deleteByPrentId(parseInt) ;
	}
	
	

}
