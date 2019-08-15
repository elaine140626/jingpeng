package com.highwayPlatform.model;

//平台用户表
public class PlatformUserInfo {

	private Long id;	//自增长ID
	private String usercode;	//用户名
	private String password;	//用户密码
	private String name;	//姓名
	private boolean issystemuser;	//是否系统级用户
	private Integer roletype;	//权限类型
	private boolean iscollector;	//是否收样
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private boolean isvaliddata;	//有效标识
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIssystemuser() {
		return issystemuser;
	}
	public void setIssystemuser(boolean issystemuser) {
		this.issystemuser = issystemuser;
	}
	public Integer getRoletype() {
		return roletype;
	}
	public void setRoletype(Integer roletype) {
		this.roletype = roletype;
	}
	public boolean isIscollector() {
		return iscollector;
	}
	public void setIscollector(boolean iscollector) {
		this.iscollector = iscollector;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	
	
}
