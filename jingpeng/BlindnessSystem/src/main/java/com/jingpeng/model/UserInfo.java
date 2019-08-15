package com.jingpeng.model;


import java.util.Date;

import com.kdt.base.support.model.ModelSupport;

/*
 * 用户登录信息表数据库实体 + remote返回实体
 * tongn
 * 2018.7.16
 */
public class UserInfo extends ModelSupport {
	
	private static final long serialVersionUID = -7574269001354682642L;

    /* 用户登录信息表  tongn 2018.7.16*/	
	private  Integer id; 	
	private  Integer orgId; 
	private  String userCode; 
	private  String password;
	private  String name; 
	private  Integer roleType; 
	private  Integer isSystemUser; 
	private  String operator; 
	private  Date createDate; 
	private  String modifier; 
	private  Date modifyDate; 
	private  Integer isValidData;
	private  String  orgName; //单位名称
	
	
	private  String remoteuserCode; 
	private  String remotepassword;
	private  String remotename;
	private  String remoteoperator;
	private  String remotemodifier; 
	private  String remoteId; 	
	private  String remoteOrgId; 
	private  String remoteRoleType; 
	private  String remoteIsSystemUser; 
	private  String remoteIsValidData;
	private  String remoteOrgName; //单位名称
	private  String remotecreateDate; 
	private  String remotemodifyDate; 
	private  String remoteisValidData;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRemoteuserCode() {
		return remoteuserCode;
	}
	public void setRemoteuserCode(String remoteuserCode) {
		this.remoteuserCode = remoteuserCode;
	}
	public String getRemotepassword() {
		return remotepassword;
	}
	public void setRemotepassword(String remotepassword) {
		this.remotepassword = remotepassword;
	}
	public String getRemotename() {
		return remotename;
	}
	public void setRemotename(String remotename) {
		this.remotename = remotename;
	}
	public String getRemoteoperator() {
		return remoteoperator;
	}
	public void setRemoteoperator(String remoteoperator) {
		this.remoteoperator = remoteoperator;
	}
	public String getRemotemodifier() {
		return remotemodifier;
	}
	public void setRemotemodifier(String remotemodifier) {
		this.remotemodifier = remotemodifier;
	}
	public String getRemoteId() {
		return remoteId;
	}
	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}
	public String getRemoteOrgId() {
		return remoteOrgId;
	}
	public void setRemoteOrgId(String remoteOrgId) {
		this.remoteOrgId = remoteOrgId;
	}
	public String getRemoteRoleType() {
		return remoteRoleType;
	}
	public void setRemoteRoleType(String remoteRoleType) {
		this.remoteRoleType = remoteRoleType;
	}
	public String getRemoteIsSystemUser() {
		return remoteIsSystemUser;
	}
	public void setRemoteIsSystemUser(String remoteIsSystemUser) {
		this.remoteIsSystemUser = remoteIsSystemUser;
	}
	public String getRemoteIsValidData() {
		return remoteIsValidData;
	}
	public void setRemoteIsValidData(String remoteIsValidData) {
		this.remoteIsValidData = remoteIsValidData;
	}
	public String getRemoteOrgName() {
		return remoteOrgName;
	}
	public void setRemoteOrgName(String remoteOrgName) {
		this.remoteOrgName = remoteOrgName;
	}
	public String getRemotecreateDate() {
		return remotecreateDate;
	}
	public void setRemotecreateDate(String remotecreateDate) {
		this.remotecreateDate = remotecreateDate;
	}
	public String getRemotemodifyDate() {
		return remotemodifyDate;
	}
	public void setRemotemodifyDate(String remotemodifyDate) {
		this.remotemodifyDate = remotemodifyDate;
	}
	public String getRemoteisValidData() {
		return remoteisValidData;
	}
	public void setRemoteisValidData(String remoteisValidData) {
		this.remoteisValidData = remoteisValidData;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public Integer getIsSystemUser() {
		return isSystemUser;
	}
	public void setIsSystemUser(Integer isSystemUser) {
		this.isSystemUser = isSystemUser;
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
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getIsValidData() {
		return isValidData;
	}
	public void setIsValidData(Integer isValidData) {
		this.isValidData = isValidData;
	}
	
	
	
	
}
