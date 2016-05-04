package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.work.entity.Category;
import com.work.entity.User;
import com.work.service.CategoryService;
import com.work.util.TreeGridUtil;

/**
 * 
 * ClassName: CategoryController 
 * @Description: 产品分类管理
 * @author crossoverJie
 * @date 2016年4月13日 下午11:18:59
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService ;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/turnToCategoryList")
	public String turnToCategoryList(){
		return "/category/categoryList" ;
	}
	
	@RequestMapping("/getCategoryList")
	public void getCategoryList(Category category,HttpServletResponse response,HttpSession session) throws IOException{
		User user = (User) session.getAttribute("user") ;
//		category.setUser_id(user.getId()+"");
		response.setCharacterEncoding("utf-8") ;
		List<TreeGridUtil> trees = new ArrayList<TreeGridUtil>() ;
		//只显示一级菜单
		category.setParent_id(-1) ;
		List<Category> categorys = categoryService.findAll(category);
		for(Category f:categorys){
			TreeGridUtil tree = new TreeGridUtil() ;
			tree.setId(f.getId()) ;
			tree.setName(f.getName());
			tree.setParent_id(f.getParent_id()) ;
//			tree.setState("closed") ;
			if(f.getLevel().equals("1")){
				int parent_id = f.getId() ;
				Category parent = new Category() ;
				parent.setParent_id(parent_id) ;
				List<Category> lists = categoryService.findAll(parent) ;
				List<TreeGridUtil> t2 = new ArrayList<TreeGridUtil>() ;
				for(Category f_son :lists){
					TreeGridUtil t_son = new TreeGridUtil() ;
					t_son.setId(f_son.getId());
					t_son.setName(f_son.getName()) ;
					t_son.setParent_id(f_son.getParent_id()) ;
					t2.add(t_son) ;
					
					List<TreeGridUtil> t3 = new ArrayList<TreeGridUtil>() ;
					if(f_son.getLevel().equals("2")){
						int pid = f_son.getId() ;
						Category pr = new Category() ;
						pr.setParent_id(pid) ;
						List<Category> listss = categoryService.findAll(pr) ;
						for(Category c :listss){
							TreeGridUtil t_sons = new TreeGridUtil() ;
							t_sons.setId(c.getId());
							t_sons.setName(c.getName()) ;
							t_sons.setParent_id(c.getParent_id()) ;
							t3.add(t_sons) ;
						}
						t_son.setChildren(t3);
					}
					tree.setChildren(t2);
				}
			}
			
			
			
			trees.add(tree) ;
		}
		
		String json = JSON.toJSONString(trees) ;
		System.out.println(json);
		response.getWriter().print(json) ;
	}
	
	@RequestMapping("/create")
	public void create(Category category,HttpServletResponse response) throws IOException{
		response.setContentType("html/text") ;
		try {
			User user = (User) request.getSession().getAttribute("user") ;
			category.setUser_id(user.getId()+"");
			categoryService.save(category) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			response.getWriter().print("false") ;
			e.printStackTrace() ;
		}
	}
	
	/**
	 * 
	 * @Description: 获得所有一级类别
	 * @param    
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author chj
	 * @date 2016-1-12  下午1:21:43
	 */
	@RequestMapping("/getAllone")
	public void getAllone(HttpServletResponse response,HttpSession session) throws IOException{
		User user = (User) session.getAttribute("user") ;
		response.setCharacterEncoding("utf-8") ;
		Category f = new Category() ;
		f.setLevel("1") ;
//		f.setUser_id(user.getId()+"");
		List<Category> fs = categoryService.findAll(f) ;
		String json = JSON.toJSONString(fs) ;
		response.getWriter().print(json) ;
		
	}
	
	/**
	 * 
	 * @Description: 查找所有的二级类别
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月14日  下午11:28:48
	 */
	@RequestMapping("/getAlltwo")
	public void getAlltwo(HttpServletResponse response,HttpSession session) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Category f = new Category() ;
//		f.setUser_id(user.getId()+"");
		f.setLevel("2") ;//所有二级分类
		List<Category> fs = categoryService.findAll(f) ;
		String json = JSON.toJSONString(fs) ;
		response.getWriter().print(json) ;
		
	}
	
	@RequestMapping("/getAllThree")
	public void getAllThree(HttpServletResponse response,HttpSession session) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Category f = new Category() ;
//		f.setUser_id(user.getId()+"");
		f.setLevel("3") ;//所有二级分类
		List<Category> fs = categoryService.findAll(f) ;
		String json = JSON.toJSONString(fs) ;
		response.getWriter().print(json) ;
		
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,String level,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			//当parent_id为-1时候 表示该节点为一级菜单 所以要删除下边所有的记录。
			if(level.equals("1")){
				//删除所有三级菜单，也就是三级分类的parent_id为二级分类的ID的数据
				Category c = new Category() ;
				c.setParent_id(Integer.parseInt(id));
				List<Category> list_tow = categoryService.findAll(c) ;
				for(Category c2 : list_tow){
					int c2_id = c2.getId() ;
					Category c3 = new Category() ;
					c3.setParent_id(c2_id);
					
					//通过c2的ID找到所有的三级分类
					List<Category> list_three = categoryService.findAll(c3) ;
					for(Category c4: list_three){
						int c4_id = c4.getId() ;
						//将所有的三级类别删除
						categoryService.delete(c4_id);
					}
					
					
				}
				
				
				//删除所有二级分类中的parent_id为id的数据
				categoryService.deleteByPrentId(Integer.parseInt(id)) ;
				
				//然后删除该父节点
				categoryService.delete(Integer.parseInt(id)) ;
			}else if("2".equals(level)){//如果选中的是二级分类 就要先删除所有二级分类下的所有二级菜单
				Category c = new Category() ;
				c.setParent_id(Integer.parseInt(id));
				List<Category> list_three = categoryService.findAll(c) ;
				for(Category c_three : list_three){
					int c3id = c_three.getId();
					categoryService.delete(c3id);//删除所有的三级类别
				}
				
				//最后删除二级类别
				categoryService.delete(Integer.parseInt(id));
			}else{//就是删除三级类别了
				categoryService.delete(Integer.parseInt(id)) ;
			}
			response.getWriter().print("true") ;
		}
	}
	@RequestMapping("/edit")
	public void edit(Category f,HttpServletResponse response) throws IOException{
		try {
			response.setContentType("html/text") ;
			categoryService.update(f) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			response.getWriter().print("false") ;
			e.printStackTrace() ;
		}
	}
	
}
