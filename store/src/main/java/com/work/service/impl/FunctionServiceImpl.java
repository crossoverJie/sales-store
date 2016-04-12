package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.FunctionDao;
import com.work.entity.Function;
import com.work.service.FunctionService;
import com.work.util.Page;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao ;

	public Function load(int id) {
		// TODO Auto-generated method stub
		return functionDao.load(id);
	}

	public Function get(int id) {
		// TODO Auto-generated method stub
		return functionDao.get(id);
	}

	public List<Function> findAll() {
		return functionDao.findAll();
	}

	public void persist(Function entity) {
		// TODO Auto-generated method stub
		functionDao.persist(entity);
	}

	public int save(Function entity) {
		// TODO Auto-generated method stub
		return functionDao.save(entity);
	}

	public void saveOrUpdate(Function entity) {
		functionDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		functionDao.delete(id);
	}

	public void flush() {
		functionDao.flush();
	}


	public Page<Function> findByParams(Function Function, int page, int rows) {
		return functionDao.findByParams(Function, page, rows) ;
	}

	public int findAllCount(Function u) {
		return functionDao.findAllCount(u);
	}

	public void update(Function Function) {
		functionDao.update(Function);
	}

	public List<Function> findAll(Function function) {
		return functionDao.findAll(function);
	}

	public void deleteByPrentId(int parseInt) {
		functionDao.deleteByPrentId(parseInt) ;
	}
	
	

}
