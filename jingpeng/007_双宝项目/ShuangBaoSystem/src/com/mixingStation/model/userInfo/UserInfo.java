package com.mixingStation.model.userInfo;

import java.util.Date;

/**
 * @since 用户信息表 
 * @author Administrator
 *
 */
public class UserInfo{
	// id
	private int id;
	
	// 账号
	private String userName;

	// 密码
	private String password;
	
	// 用户名
	private String name;
	
	// 是否是系统用户 0 非系统用户 1 系统用户
	private String isSystem;
	
	// 组织机构ID
	private int powerOrgID;
	
	// 创建人
	private String operator;
	
	// 创建日期
	private Date createDate;
	
	// 修改人
	private String modifier;
	
	// 修改日期
	private String modifDate;
	
	// 删除标识 0已删除(无效用户) 1 有效用户
	private String validFlag;
	
	// 上传标识
	private String upload;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public int getPowerOrgID() {
		return powerOrgID;
	}

	public void setPowerOrgID(int powerOrgID) {
		this.powerOrgID = powerOrgID;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifDate() {
		return modifDate;
	}

	public void setModifDate(String modifDate) {
		this.modifDate = modifDate;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

}
