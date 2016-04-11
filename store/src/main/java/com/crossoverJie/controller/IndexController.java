package com.crossoverJie.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crossoverJie.entity.User;
import com.crossoverJie.service.UserService;
import com.crossoverJie.util.AbstractController;
import com.mysql.fabric.Response;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;

@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request ;
	
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping("/turnToIndex/{pageNum}")
	public String trunToIndex(@PathVariable int pageNum,Model model,HttpSession session){
		return "../../../index" ;
	}
	
	
	@RequestMapping("/register")
	public String register(User u,HttpServletRequest request) throws IOException{
		//系统初始化的时候要上传一张默认头像
//		u.setImg_id("1001");//设置默认头像
		u.setLogin_date(new Date());
		u.setRegester_date(new Date());
		userService.save(u) ;
//		注册完之后直接登录
		request.getSession().setAttribute("user", u) ;
//		重定向到首页
		return "redirect:../index/turnToIndex/1" ;
	}
	
	@RequestMapping("/login")
	public void login(User u,HttpServletRequest request,HttpServletResponse response) throws IOException{
		User user = userService.findLogin(u) ;
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
		if(user != null){
			Date date = new Date() ;
			user.setLogin_date(date) ;
			userService.saveOrUpdate(user) ;
			request.getSession().setAttribute("user", user) ;
			response.getWriter().print("true") ;
//			return "redirect:../index/turnToIndex/1" ;
		}else {
		    response.getWriter().print("false") ;
		}
	}
	
	
	/**
	 * 
	 * @Description: 修改密码
	 * @param @param user
	 * @param @param session
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author chj
	 * @date 2016-1-26  下午7:41:56
	 */
	@RequestMapping("/updateUserInfo")
	public String updateUserInfo(User user,String flag ,HttpSession session){
		userService.saveOrUpdate(user) ;
		if(flag.equals("0")){//为0就是修改密码
			session.removeAttribute("user") ;
			return "redirect:../index/turnToIndex/1" ;
			
		}else{//为1的话表示更新基本设置 刷新界面就可以了 不需要去掉session
			return null ;
		}
	}
	
	 /* @Description: 检查用户名或者是邮箱是否重复
	 * @param @param u
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-24  下午1:08:17
	 */
	@RequestMapping("/checkIsRepeat")
	public void checkIsRepeat(User u,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		response.setContentType("html/text") ;
		int count = userService.findAllCount(u) ;
		if(count ==0){
			response.getWriter().print("true") ;
		}else {
			response.getWriter().print("false") ;
		}
	}
	
	
	
	/**
	 * 
	 * @Description: 验证当前密码是否错误
	 * @param @param user
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-26  下午7:47:52
	 */
	@RequestMapping("/checkCurrentPwd")
	public void checkCurrentPwd(User user,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
//	    int count = userService.find(user) ;
//	    if(count ==0){
//	    	response.getWriter().print("false") ;
//	    }else {
//	    	response.getWriter().print("true") ;
//	    }
	}
	
	
}
