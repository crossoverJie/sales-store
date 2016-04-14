package com.work.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * ClassName: Category 
 * @Description: 产品类别
 * @author crossoverJie
 * @date 2016年4月13日 下午10:58:44
 */
@Entity
public class Category {
	private int id ;
	private int parent_id ;
	private String name ;
	private String level ;//产品等级 1：一级   2：二级    3：三级
	private String user_id ;
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
