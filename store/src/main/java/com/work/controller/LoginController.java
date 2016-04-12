package com.work.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.Response;
import com.work.entity.User;
import com.work.service.UserService;

/**
 * 
 * ClassName: LoginController 
 * @Description: 登录的控制器
 * @author work
 * @date 2016年4月10日 下午11:03:10
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @Description: 登录方法
	 * @param @param user
	 * @param @param model
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author work
	 * @date 2016年4月10日  下午11:39:38
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/check")
	public void login(User user,HttpServletResponse response,HttpServletRequest request) throws IOException{
		User u = userService.findLogin(user) ;
		if(u== null){
			response.setContentType("html/text");
			response.getWriter().print("false") ;
		}else{
			Date date = new Date() ;
			u.setLogin_date(new Date());
			userService.saveOrUpdate(u) ;
			if(u != null){
				request.getSession().setAttribute("user", u) ;
				response.getWriter().print("true") ;
			}else{
				response.getWriter().print("false") ;
			}
		}
	}
	
	
	/**
	 * 
	 * @Description: 注销
	 * @param @param request
	 * @param @param type
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author work
	 * @date 2016年4月11日  下午8:17:42
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request,String type,Model model){
		request.getSession().removeAttribute("user") ;
//		0代表后台管理注销 1代表普通用户注销
		if(type.equals("0")){
			return "../../../login" ;
		}else{
			return "redirect:../index/turnToIndex/1" ;
		}
	}
	
	
	
	
	
}
