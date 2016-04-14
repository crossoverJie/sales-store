package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.work.entity.Category;
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
	
	@RequestMapping("/turnToCategoryList")
	public String turnToCategoryList(){
		return "/category/categoryList" ;
	}
	
	@RequestMapping("/getCategoryList")
	public void getCategoryList(Category category,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		List<TreeGridUtil> trees = new ArrayList<TreeGridUtil>() ;
		//只显示一级菜单
		category.setParent_id(-1) ;
		List<Category> categorys = categoryService.findAll(category);
		for(Category f:categorys){
//			TreeGridUtil tree = new TreeGridUtil() ;
//			tree.setId(f.getId()) ;
//			tree.setCategory_url(f.getCategory_url());
//			tree.setName(f.getCategory_name());
//			tree.setRemark(f.getRemark()) ;
//			tree.setParent_id(f.getParent_id()) ;
////			tree.setState("closed") ;
//			if(f.getParent_id() == -1){
//				int parent_id = f.getId() ;
//				Category parent = new Category() ;
//				parent.setParent_id(parent_id) ;
//				List<Category> lists = categoryService.findAll(parent) ;
//				List<TreeGridUtil> t2 = new ArrayList<TreeGridUtil>() ;
//				for(Category f_son : lists){
//					TreeGridUtil t_son = new TreeGridUtil() ;
//					t_son.setId(f_son.getId());
//					t_son.setCategory_url(f_son.getCategory_url()) ;
//					t_son.setName(f_son.getCategory_name()) ;
//					t_son.setRemark(f_son.getRemark());
//					t_son.setParent_id(f_son.getParent_id()) ;
//					t2.add(t_son) ;
//				}
//				tree.setChildren(t2) ;
//			}
//			trees.add(tree) ;
		}
		
		String json = JSON.toJSONString(trees) ;
		System.out.println(json);
		response.getWriter().print(json) ;
	}
	
	@RequestMapping("/create")
	public void create(Category category,HttpServletResponse response) throws IOException{
		response.setContentType("html/text") ;
		try {
			categoryService.save(category) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			response.getWriter().print("false") ;
			e.printStackTrace() ;
		}
	}
	
	/**
	 * 
	 * @Description: 获得所有一级菜单
	 * @param    
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author chj
	 * @date 2016-1-12  下午1:21:43
	 */
	@RequestMapping("/getAllone")
	public void getAllone(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Category f = new Category() ;
		f.setParent_id(-1) ;
		List<Category> fs = categoryService.findAll(f) ;
		String json = JSON.toJSONString(fs) ;
		response.getWriter().print(json) ;
		
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,String parent_id,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			//当parent_id为-1时候 表示该节点为一级菜单 所以要删除下边所有的记录。
			if(parent_id.equals("-1")){
				//首先删除所有父节点为id的
				categoryService.deleteByPrentId(Integer.parseInt(id)) ;
				
				//然后删除该父节点
				categoryService.delete(Integer.parseInt(id)) ;
			}else{
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
