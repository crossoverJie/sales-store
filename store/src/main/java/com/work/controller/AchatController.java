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
import com.work.util.StringUtil;

@Controller
@RequestMapping("/achat")
public class AchatController {
	
	@Autowired
	private AchatService achatService ;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProduceService produceService ;
	
	@Autowired
	private UserService userService ;
	
	@RequestMapping("/turnToAchatList")
	public String turnToAchatList(){
		return "/achat/achatList" ;
	}
	
	@RequestMapping("/turnToAchatList_support")
	public String turnToAchatList_support(){
		return "/achat/achatList_support" ;
	}
	
	@RequestMapping("/getAchatList")
	public void getAchatList(Achat achat, HttpServletResponse response,int page,int rows){
		Page<Achat> achats = achatService.findByParams(achat, page, rows) ;
		for(Achat a : achats.getRows()){
			String category_id = a.getCategory_id() ;
			Category c = categoryService.get(Integer.parseInt(category_id)) ;
			a.setCategory_name(c.getName());
			String state = a.getState();
			a.setState(StringUtil.getState(state));
			
			Date d = a.getCreate_date() ;
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
			String date = sm.format(d);
			a.setParseDate(date);
			
			String uid = a.getCreate_user() ;
			a.setCreate_username(userService.get(Integer.parseInt(uid)).getUsername());
			String sid = a.getSupport_id() ;
			if(sid != null){
				a.setSupport_name(userService.get(Integer.parseInt(sid)).getUsername());
			}
			String produce_id = a.getProduce_id() ;
			if(produce_id != null){
				a.setProduce_name(produceService.get(Integer.parseInt(produce_id)).getName());
			}
				
		}
		String json = JSON.toJSONString(achats) ;
		try {
			System.out.println(json);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/getAchatList_notAdmin")
	public void getAchatList_notAdmin(Achat achat, HttpServletResponse response,
			HttpSession session,int page,int rows){
		User user = (User) session.getAttribute("user") ;
		achat.setSupport_id(user.getId()+"");//只能看自己相关的信息
		Page<Achat> achats = achatService.findByParams(achat, page, rows) ;
		for(Achat a : achats.getRows()){
			String category_id = a.getCategory_id() ;
			Category c = categoryService.get(Integer.parseInt(category_id)) ;
			a.setCategory_name(c.getName());
			String state = a.getState();
			a.setState(StringUtil.getState(state));
			
			Date d = a.getCreate_date() ;
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
			String date = sm.format(d);
			a.setParseDate(date);
			
			String uid = a.getCreate_user() ;
			a.setCreate_username(userService.get(Integer.parseInt(uid)).getUsername());
			String sid = a.getSupport_id() ;
			if(sid != null){
				a.setSupport_name(userService.get(Integer.parseInt(sid)).getUsername());
			}
			
			String produce_id = a.getProduce_id() ;
			if(produce_id != null){
				a.setProduce_name(produceService.get(Integer.parseInt(produce_id)).getName());
			}
				
		}
		String json = JSON.toJSONString(achats) ;
		try {
			System.out.println(json);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			achatService.delete(Integer.parseInt(id)) ;
			response.getWriter().print("true") ;
		}
	}
	
	/**
	 * 
	 * @Description: 分发供应商
	 * @param @param achat
	 * @param @param response   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月17日  下午5:13:45
	 */
	@RequestMapping("/subSupport")
	public void subSupport(Achat achat,HttpServletResponse response) throws IOException{
		achat.setState("1");//供应商报价中
		achatService.update(achat);
		try {
			response.getWriter().print("true") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.getWriter().print("false") ;
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 供应商报价
	 * @param @param achat
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月17日  下午9:46:23
	 */
	@RequestMapping("/subSupportPrice")
	public void subSupportPrice(Achat achat,HttpServletResponse response) throws IOException{
		achat.setState("2");//供应商已经报价，由会员
		achatService.update(achat);
		try {
			response.getWriter().print("true") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.getWriter().print("false") ;
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 供应商上架产品，并加入订单
	 * @param    
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月18日  下午2:16:19
	 */
	@RequestMapping("/subProduce")
	public void subProduce(Achat achat,HttpServletResponse response) throws IOException{
		achat.setState("5");//供应商上架，加入订单中
		achatService.update(achat); 
		
		//将该产品的数据减一保存
		String produce_id = achat.getProduce_id() ;
		Produce pr = produceService.get(Integer.parseInt(produce_id)) ;
		int num = pr.getKucun_number() -1 ;
		pr.setKucun_number(num);
		produceService.update(pr);
		
		response.getWriter().print("true") ;
	}
	
}
