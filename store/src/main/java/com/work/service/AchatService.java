package com.work.service;

import java.util.List;

import com.work.entity.Achat;
import com.work.entity.Category;
import com.work.util.Page;

public interface AchatService {
	Achat load(int id);

	Achat get(int id);

	List<Achat> findAll();

	void persist(Achat entity);

	int save(Achat entity);

	void saveOrUpdate(Achat entity);

	void delete(int id);

	void flush();
	
	Page<Achat> findByParams(Achat achat, int page, int rows);

	int findAllCount(Achat u);
	
	List<Achat> findAll(Achat achat);

	void update(Achat user);
	public void deleteByTitleAndContent(Achat achat);
}
