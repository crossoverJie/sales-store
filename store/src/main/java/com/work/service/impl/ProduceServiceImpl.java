package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.ProduceDao;
import com.work.dao.ProduceDao;
import com.work.entity.Produce;
import com.work.entity.Category;
import com.work.service.ProduceService;
import com.work.util.Page;

@Service("produceService")
public class ProduceServiceImpl implements ProduceService {

	@Autowired
	private ProduceDao produceDao ;

	public Produce load(int id) {
		// TODO Auto-generated method stub
		return produceDao.load(id);
	}

	public Produce get(int id) {
		// TODO Auto-generated method stub
		return produceDao.get(id);
	}

	public List<Produce> findAll() {
		// TODO Auto-generated method stub
		return produceDao.findAll();
	}

	public void persist(Produce entity) {
		// TODO Auto-generated method stub
		produceDao.persist(entity);
	}

	public int save(Produce entity) {
		// TODO Auto-generated method stub
		return produceDao.save(entity);
	}

	public void saveOrUpdate(Produce entity) {
		produceDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		produceDao.delete(id);
	}

	public void flush() {
		produceDao.flush();
	}

	public Page<Produce> findByParams(Produce produce, int page, int rows) {
		
		return produceDao.findByParams(produce, page, rows) ;
		
	}

	public int findAllCount(Produce u) {
		return produceDao.findAllCount(u);
	}

	public void update(Produce produce) {
		produceDao.update(produce);
	}

	public List<Produce> findAll(Produce produce) {
		return produceDao.findAll(produce);
	}

	
	

}
