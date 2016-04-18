package com.work.dao;

import java.util.List;

import com.work.entity.Produce;

public interface ProduceDao extends GenericDao<Produce,String> {

	List<Produce> findAll(Produce produce);
}
