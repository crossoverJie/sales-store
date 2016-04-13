package com.work.service;

import java.util.List;

import com.work.entity.Category;
import com.work.util.Page;

public interface CategoryService {
	Category load(int id);

	Category get(int id);

	List<Category> findAll();

	void persist(Category entity);

	int save(Category entity);

	void saveOrUpdate(Category entity);

	void delete(int id);

	void flush();

	Page<Category> findByParams(Category Produce, int page, int rows);

	int findAllCount(Category u);

	void update(Category Produce);

	List<Category> findAll(Category function);

	void deleteByPrentId(int parseInt);
}
