package com.highwayPlatform.model;

//试验基本信息表
public class cookieModel {

	private Integer id;
	private String userCode; // 用户名
	private String passWord; // 密码
	private String name; // 姓名
	private Integer issystemuser; // 是否系统用户
	private Integer roletype; // 角色
	private String userTestDetailed;// 试验室权限
	private String userOrgDetailed; // 拌合站权限
	private int IsCollector; // 收样权限

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIssystemuser() {
		return issystemuser;
	}

	public void setIssystemuser(Integer issystemuser) {
		this.issystemuser = issystemuser;
	}

	public Integer getRoletype() {
		return roletype;
	}

	public void setRoletype(Integer roletype) {
		this.roletype = roletype;
	}

	public String getUserTestDetailed() {
		return userTestDetailed;
	}

	public void setUserTestDetailed(String userTestDetailed) {
		this.userTestDetailed = userTestDetailed;
	}

	public String getUserOrgDetailed() {
		return userOrgDetailed;
	}

	public void setUserOrgDetailed(String userOrgDetailed) {
		this.userOrgDetailed = userOrgDetailed;
	}

	public int getIsCollector() {
		return IsCollector;
	}

	public void setIsCollector(int isCollector) {
		IsCollector = isCollector;
	}
}
