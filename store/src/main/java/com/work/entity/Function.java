package com.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * ClassName: Function 
 * @Description: 功能实体
 * @author crossoverJie
 * @date 2016年4月12日 下午10:19:08
 */
@Entity
@Table(name = "function", catalog = "salesstore")
public class Function {
	private Integer id ;
	private Integer parent_id ;
	private String function_name ;
	private String function_url ;
	private String remark ;
	public Function() {
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="parent_id")
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	@Column(name="function_name")
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	@Column(name="function_url")
	public String getFunction_url() {
		return function_url;
	}
	public void setFunction_url(String function_url) {
		this.function_url = function_url;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
