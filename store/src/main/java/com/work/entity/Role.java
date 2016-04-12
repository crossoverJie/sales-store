package com.work.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * ClassName: Role 
 * @Description: 角色类
 * @author crossoverJie
 * @date 2016年4月12日 下午9:08:23
 */
@Entity
@Table(name = "role", catalog = "salesstore")
//会员信息要及时更新所以不用缓存 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = 6980093847795726310L;
	private int id;
	private String role_name ;
	private String remark ;
	
	private String function_id ;
	private String all_function_name;//角色的所有功能名称
	
//	private Date registerTime;
	//private Set<AcctRole> acctRoles = new HashSet<AcctRole>(0);

	public Role() {

	}

//	public AcctUser(String id, String nickName) {
//		this.id = id;
//		this.nickName = nickName;
//	}
//
//	public AcctUser(String id, String nickName, String telephone,
//			Date registerTime, Set<AcctRole> acctRoles) {
//		this.id = id;
//		this.nickName = nickName;
//		this.telephone = telephone;
//		this.registerTime = registerTime;
//		this.acctRoles = acctRoles;
//	}

	@Id
	@Column(name = "id", unique = true, nullable = true)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="role_name")
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="function_id",length=30)
	public String getFunction_id() {
		return function_id;
	}

	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}

	public String getAll_function_name() {
		return all_function_name;
	}

	public void setAll_function_name(String all_function_name) {
		this.all_function_name = all_function_name;
	}


}
