package com.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.entity.Produce;
import com.work.service.CategoryService;
import com.work.service.ProduceService;

/**
 * 
 * ClassName: TopicController 
 * @Description: 详情控制器
 * @author crossoverJie
 * @date 2016年5月1日 下午6:43:47
 */
@Controller
@RequestMapping("/")
public class TopicController {
	
	@Autowired
	private ProduceService produceService;
	
	@Autowired
	private CategoryService categoryservice ;
	
	@RequestMapping("/topic/{id}")
	public String showProduceDetail(@PathVariable int id,Model model){
		
		Produce produce = produceService.get(id) ;
		model.addAttribute("produce", produce) ;
		return "/front/topic/topicDetail" ;
	}
	
}
