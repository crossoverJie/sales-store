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
	private FunctionDao FunctionDao ;

	public Function load(int id) {
		// TODO Auto-generated method stub
		return FunctionDao.load(id);
	}

	public Function get(int id) {
		// TODO Auto-generated method stub
		return FunctionDao.get(id);
	}

	public List<Function> findAll() {
		return FunctionDao.findAll();
	}

	public void persist(Function entity) {
		// TODO Auto-generated method stub
		FunctionDao.persist(entity);
	}

	public int save(Function entity) {
		// TODO Auto-generated method stub
		return FunctionDao.save(entity);
	}

	public void saveOrUpdate(Function entity) {
		FunctionDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		FunctionDao.delete(id);
	}

	public void flush() {
		FunctionDao.flush();
	}

	public Function findLogin(Function entity) {
		return FunctionDao.findByLogin(entity);
	}

	public Page<Function> findByParams(Function Function, int page, int rows) {
		return FunctionDao.findByParams(Function, page, rows) ;
	}

	public int findAllCount(Function u) {
		return FunctionDao.findAllCount(u);
	}

	public void update(Function Function) {
		FunctionDao.update(Function);
	}
	
	

}
