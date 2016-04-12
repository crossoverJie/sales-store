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
 * ClassName: User 
 * @Description: 会员实体类
 * @author work
 * @date 2016年4月10日 下午10:22:35
 */
@Entity
@Table(name = "user", catalog = "salesstore")
//会员信息要及时更新所以不用缓存 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 6980093847795726310L;
	private int id;
	private String username;
	private String realname ;
	private String password;
	private Date regester_date ;
	private Date login_date ;
	private String province ;
	private String parseDate ;
	
//	private Date registerTime;
	//private Set<AcctRole> acctRoles = new HashSet<AcctRole>(0);

	public User() {

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


	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="realname",length=50)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", length = 19)
	public Date getRegester_date() {
		
		return regester_date;
	}

	public void setRegester_date(Date regester_date) {
		
		this.regester_date = regester_date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_date", length = 19)
	public Date getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	
	@Column(name="province",length=50)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getParseDate() {
		return parseDate;
	}

	public void setParseDate(String parseDate) {
		this.parseDate = parseDate;
	}


//	@JsonIgnoreProperties(value={"acctUsers", "acctAuthorities"})
//	@ManyToMany(fetch = FetchType.LAZY)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	@JoinTable(name = "acct_user_role", catalog = "work", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
//	public Set<AcctRole> getAcctRoles() {
//		return this.acctRoles;
//	}
//
//	public void setAcctRoles(Set<AcctRole> acctRoles) {
//		this.acctRoles = acctRoles;
//	}
}
