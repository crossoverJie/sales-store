package com.work.dao;

import java.util.List;

import com.work.entity.Category;

public interface CategoryDao extends GenericDao<Category,String> {

	List<Category> findAll(Category produce);

	void deleteByPrentId(int parseInt);
}
