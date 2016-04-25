package com.work.service;

import java.util.List;

import com.work.entity.Produce;
import com.work.util.Page;

public interface ProduceService {
	Produce load(int id);

	Produce get(int id);

	List<Produce> findAll();

	void persist(Produce entity);

	int save(Produce entity);

	void saveOrUpdate(Produce entity);

	void delete(int id);

	void flush();
	
	Page<Produce> findByParams(Produce produce, int page, int rows);

	int findAllCount(Produce u);
	
	List<Produce> findAll(Produce achat);

	void update(Produce user);
}
