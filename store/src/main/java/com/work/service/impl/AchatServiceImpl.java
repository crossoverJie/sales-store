package com.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.AchatDao;
import com.work.dao.AchatDao;
import com.work.entity.Achat;
import com.work.entity.Category;
import com.work.service.AchatService;
import com.work.util.Page;

@Service("achatService")
public class AchatServiceImpl implements AchatService {

	@Autowired
	private AchatDao achatDao ;

	public Achat load(int id) {
		// TODO Auto-generated method stub
		return achatDao.load(id);
	}

	public Achat get(int id) {
		// TODO Auto-generated method stub
		return achatDao.get(id);
	}

	public List<Achat> findAll() {
		// TODO Auto-generated method stub
		return achatDao.findAll();
	}

	public void persist(Achat entity) {
		// TODO Auto-generated method stub
		achatDao.persist(entity);
	}

	public int save(Achat entity) {
		// TODO Auto-generated method stub
		return achatDao.save(entity);
	}

	public void saveOrUpdate(Achat entity) {
		achatDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		achatDao.delete(id);
	}

	public void flush() {
		achatDao.flush();
	}

	public Page<Achat> findByParams(Achat achat, int page, int rows) {
		
		return achatDao.findByParams(achat, page, rows) ;
		
	}

	public int findAllCount(Achat u) {
		return achatDao.findAllCount(u);
	}

	public void update(Achat achat) {
		achatDao.update(achat);
	}

	public List<Achat> findAll(Achat achat) {
		return achatDao.findAll(achat);
	}

	public void deleteByTitleAndContent(Achat achat){
		achatDao.deleteByTitleAndContent(achat); 
	}
	

}
