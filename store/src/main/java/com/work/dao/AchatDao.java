package com.work.dao;

import java.util.List;

import com.work.entity.Achat;

public interface AchatDao extends GenericDao<Achat,String> {

	List<Achat> findAll(Achat achat);
}
