package com.crossoverJie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * ClassName: BaseController 
 * @Description: 基础控制器
 * @author crossoverJie
 * @date 2016年4月10日 下午11:06:07
 */
public class BaseController {
	@Autowired
	public HttpServletResponse response ;
	
	@Autowired
	public HttpServletRequest request ;
	
}
