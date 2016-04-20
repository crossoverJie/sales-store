package com.work.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.work.entity.Achat;
import com.work.entity.Category;
import com.work.entity.Produce;
import com.work.entity.User;
import com.work.service.AchatService;
import com.work.service.CategoryService;
import com.work.service.ProduceService;
import com.work.service.UserService;
import com.work.util.Page;

@Controller
@RequestMapping("/produce")
public class ProduceController {
	
	@Autowired
	private AchatService achatService ;
	
	@Autowired
	private ProduceService produceService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService ;
	
	@RequestMapping("/turnToProduceList")
	public String turnToAchatList(){
		return "/produce/produceList" ;
	}
	
	
	@RequestMapping("/getProduceList")
	public void getProduceList(Produce produce,HttpSession session, HttpServletResponse response,int page,int rows){
		User u = (User) session.getAttribute("user") ;
		produce.setUser_id(u.getId()+"");
		Page<Produce> produces = produceService.findByParams(produce, page, rows) ;
		for(Produce pro : produces.getRows()){
			String category_id = pro.getCategory_id() ;
			Category c = categoryService.get(Integer.parseInt(category_id)) ;
			pro.setCategory_name(c.getName());
			
			String user_id = pro.getUser_id();
			User user = userService.get(Integer.parseInt(user_id)) ;
			pro.setUsername(user.getUsername());
			
		}
		String json = JSON.toJSONString(produces) ;
		try {
			System.out.println(json);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/create")
	public String save(HttpServletResponse response,HttpSession session,Produce produce) throws IOException{
		User user = (User) session.getAttribute("user") ;
		produce.setUser_id(user.getId()+"");
		produceService.save(produce) ;
		return "/produce/produceList" ;
	}
	
	@RequestMapping("/edit")
	public void edit(HttpServletResponse response,Produce produce) throws IOException{
		produceService.update(produce);
		response.getWriter().print("true") ;
	}
	
	
	@RequestMapping("/delete")
	public void delete(String ids,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			produceService.delete(Integer.parseInt(id)) ;
			response.getWriter().print("true") ;
		}
	}
	
	
	
}
