package com.work.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
/**
 * 
 * ClassName: Produce 
 * @Description: 商品实体
 * @author crossoverJie
 * @date 2016年4月18日 上午9:02:37
 */
@Entity
public class Produce {
	private int id ;
	private String category_id ;//产品分类ID
	private String category_name ;
	private String user_id ;
	private String username;
	
	private int kucun_number ;//库存数量
	private String name ;//商品名称
	
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getKucun_number() {
		return kucun_number;
	}
	public void setKucun_number(int kucun_number) {
		this.kucun_number = kucun_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	@Transient
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
