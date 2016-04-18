package com.work.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.work.entity.Achat;
import com.work.entity.Category;
import com.work.entity.Role;
import com.work.entity.User;
import com.work.service.AchatService;
import com.work.service.CategoryService;
import com.work.service.RoleService;
import com.work.service.UserService;
import com.work.util.Page;


@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService ;

	@Autowired
	private AchatService achatService;
	
	@Autowired
	private CategoryService categoryService ;
	
	@RequestMapping("/showUser/{id}")
	public String showUserInfo(Model model,@PathVariable int id){
		LOGGER.info("查询用户"+id) ;
		User user = userService.get(id) ;
		model.addAttribute("user", user) ;
		return "/user/showUser" ;
	}
	
	@RequestMapping("/turnToIndex")
	public String turnToIndex(){
		return "/index" ;
	}
	/**
	 * 
	 * @Description: 显示通知
	 * @param @param model
	 * @param @param session
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月17日  下午8:43:56
	 */
	@RequestMapping("/turnToNotification")
	public String turnToNotification(Model model,HttpSession session,String st){
		User user = (User) session.getAttribute("user") ;
		Achat at = new Achat() ;
		if(st!=null){
			at.setState(st);
		}
		at.setSupport_id(user.getId()+"");
		List<Achat> list = achatService.findAll(at);
		for(Achat a : list){
			String category_id = a.getCategory_id() ;
			Category c = categoryService.get(Integer.parseInt(category_id)) ;
			a.setCategory_name(c.getName());
			String state = a.getState();
			if("0".equals(state)){
				a.setState("管理员处理中");
			}else if("1".equals(state)){
				a.setState("供应商报价中");
			}else if("2".equals(state)){
				a.setState("会员处理中");
			}else if("3".equals(state)){
				a.setState("供应商上架中");
			}else if("4".equals(state)){
				a.setState("会员拒绝报价");
			}
				
		}
		model.addAttribute("list", list) ;
		
		return "/notification" ;
	}
	
	/**
	 * 
	 * @Description: 删除方法
	 * @param @param ids
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author work
	 * @date 2016年4月11日  下午10:16:53
	 */
	@RequestMapping("/delete")
	public void delete(String ids,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			userService.delete(Integer.parseInt(id)) ;
			response.getWriter().print("true") ;
		}
	}
	
	@RequestMapping("/turnToUserList")
	public String turnToUserList(String type){
		if("1".equals(type)){
			return "/user/userList" ;
		}else if("2".equals(type)){
			return "/user/userList_support" ;
		}else{
			return "/user/userList_manager" ;
		}
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @param user
	 * @param @param response
	 * @param @param page 第几页
	 * @param @param rows 一页显示多少条  
	 * @return void  
	 * @throws
	 * @author work
	 * @date 2016年4月11日  上午12:58:32
	 */
	@RequestMapping("/getUserList")
	public void getUserList(@ModelAttribute User user,String type,HttpServletResponse response ,int page,int rows){
		response.setCharacterEncoding("utf-8");
		if("1".equals(type)){
			user.setRole_id("2");
		}else if("2".equals(type)){
			user.setRole_id("3");
		}else{
			user.setRole_id("4");
		}
		Page<User> users = userService.findByParams(user,page,rows) ;
		for(User u :users.getRows()){
			Date date = u.getLogin_date();
			String parseDate ="";
					
			if(date != null){
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
				parseDate = sm.format(date) ;
				u.setParseDate(parseDate); 
			}
			String role_id = u.getRole_id() ;
			String role_name ="" ;
			if(role_id != null){
				role_name = roleService.get(Integer.parseInt(role_id)).getRole_name() ;
			}
			u.setRole_name(role_name);
		}
		
		String json = JSON.toJSONString(users) ;
		System.out.println(json);
		try {
			response.getWriter().print(json) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @Description: 修改
	 * @param @param user
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author work
	 * @date 2016年4月11日  下午8:14:50
	 */
	@RequestMapping("/edit") 
	public void edit(User user,HttpServletResponse response){
		try {
			userService.update(user) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	/**
	 * 
	 * @Description: 前台页面用户详情设置
	 * @param @param id
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author work
	 * @date 2016年4月12日  下午7:28:14
	 */
	@RequestMapping("/frontUserSet/{id}")
	public String frontUserSet(@PathVariable int id,Model model){
		User user = userService.get(id) ;
		model.addAttribute("currentUser", user) ;
		return "/front/user/userSet" ;
	}
	
	
	@RequestMapping("/achatDetail/{userid}")
	public String achatDetail(@PathVariable int userid,Model model,Achat ac){
		ac.setCreate_user(userid+"");
		List<Achat> list = achatService.findAll(ac) ;
		for(Achat a : list){
			String category_id = a.getCategory_id() ;
			Category c = categoryService.get(Integer.parseInt(category_id)) ;
			a.setCategory_name(c.getName());
			String state = a.getState();
			if("0".equals(state)){
				a.setState("管理员处理中");
			}else if("1".equals(state)){
				a.setState("供应商报价中");
			}else if("2".equals(state)){
				a.setState("会员处理中");
			}else if("3".equals(state)){
				a.setState("供应商上架中");
			}else if("4".equals(state)){
				a.setState("会员拒绝报价");
			}
			String uid = a.getCreate_user() ;
			a.setCreate_username(userService.get(Integer.parseInt(uid)).getUsername());
			String sid = a.getSupport_id() ;
			if(sid != null){
				a.setSupport_name(userService.get(Integer.parseInt(sid)).getUsername());
			}else{
				a.setSupport_name("暂无供应商报价");
				
			}
				
		}
		
		model.addAttribute("achatlist", list) ;
		return "/front/user/achatDetail" ;
	}
	
	@RequestMapping("/checkPrice")
	public String checkPrice(Model model,Achat ac,HttpSession session){
		User user = (User) session.getAttribute("user");
		achatService.update(ac);
		
		return "redirect:/user/achatDetail/"+user.getId() ;
	}
	
	@RequestMapping("/getAllRoles")
	public void getAllRoles(HttpServletResponse response){
		response.setCharacterEncoding("utf-8") ;
		List<Role> roles = roleService.findAll() ;
		String json = JSON.toJSONString(roles) ;
		try {
			response.getWriter().print(json) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
