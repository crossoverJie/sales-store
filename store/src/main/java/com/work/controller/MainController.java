package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.work.entity.Achat;
import com.work.entity.Category;
import com.work.entity.User;
import com.work.service.AchatService;
import com.work.service.CategoryService;
import com.work.util.ComboboxUtil;

/**
 * 
 * ClassName: MainController 
 * @Description: 主要流程控制器
 * @author crossoverJie
 * @date 2016年4月16日 下午3:45:02
 */
@Controller
@RequestMapping("/main")
public class MainController {

	
	@Autowired
	private CategoryService categoryService ;
	
	@Autowired
	private AchatService achatService ;
	
	/**
	 * 
	 * @Description: 跳转到采购界面
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月16日  下午3:46:11
	 */
	@RequestMapping("/turnToCreateAchat")
	public String turnToCreateAchat(Model model,HttpSession session){
		User user = (User) session.getAttribute("user") ;
		if(user == null){
			return "redirect:../index/turnToIndex/1" ;
		}else{
			return "/front/Achat/createAchat" ;
		}
	}
	
	@RequestMapping("/getCategory")
	public void getCategory(Model model,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8") ;
		String level = request.getParameter("level") ;
		Category c = new Category() ;
		c.setLevel(level);
		List<Category> list = categoryService.findAll(c) ;
		List<ComboboxUtil> coms = new ArrayList<ComboboxUtil>() ;
		for(Category ca : list){
			ComboboxUtil com = new ComboboxUtil();
			com.setId(ca.getId());
			com.setText(ca.getName());
			coms.add(com);
		}
		String json = JSON.toJSONString(coms) ;
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getCategoryByParent")
	public void getCategoryByParent(HttpServletResponse response,Category c){
		List<Category> list = categoryService.findAll(c) ;
		List<ComboboxUtil> coms = new ArrayList<ComboboxUtil>() ;
		for(Category ca : list){
			ComboboxUtil com = new ComboboxUtil();
			com.setId(ca.getId());
			com.setText(ca.getName());
			coms.add(com);
		}
		String json = JSON.toJSONString(coms) ;
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/createAchat")
	public String createAchat(Model model,Achat achat,HttpSession session){
		User user = (User) session.getAttribute("user") ;
		achat.setCreate_user(user.getId()+"");
		achat.setCreate_date(new Date());
		achat.setState("0");//默认为管理员处理中
		achatService.save(achat) ;
		return "redirect:../user/achatDetail/"+user.getId() ;
	}
	
	
	
	
}
