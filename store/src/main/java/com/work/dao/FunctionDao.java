package com.work.dao;

import java.util.List;

import com.work.entity.Function;

public interface FunctionDao extends GenericDao<Function,String> {

	List<Function> findAll(Function function);

	void deleteByPrentId(int parseInt);
}
