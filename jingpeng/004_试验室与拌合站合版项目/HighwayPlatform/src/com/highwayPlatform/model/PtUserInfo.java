package com.highwayPlatform.model;

public class PtUserInfo {
	 private int Id;                    // 用户id
	 private String UserCode;           // 用户名
	 private String PassWord;           // 密码
	 private String Name;               // 姓名
	 private int IsSystemUser;          // 用户权限
	 private int RoleType;              // 试验室权限(0:试验室  1:指挥部)
	 private int IsCollector;           // 试验室收样权限
	 private int IsSamping;             // 试验室抽样权限
	 private int DelFlag;               // 删除标识
	 private String Operator;           // 创建人
	 private String CreateDate;         // 创建时间
	 private String Modifier;           // 更新人
	 private String ModifyDate;         // 更新时间
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getIsSystemUser() {
		return IsSystemUser;
	}
	public void setIsSystemUser(int isSystemUser) {
		IsSystemUser = isSystemUser;
	}
	public int getRoleType() {
		return RoleType;
	}
	public void setRoleType(int roleType) {
		RoleType = roleType;
	}
	public int getIsCollector() {
		return IsCollector;
	}
	public void setIsCollector(int isCollector) {
		IsCollector = isCollector;
	}
	public int getIsSamping() {
		return IsSamping;
	}
	public void setIsSamping(int isSamping) {
		IsSamping = isSamping;
	}
	public int getDelFlag() {
		return DelFlag;
	}
	public void setDelFlag(int delFlag) {
		DelFlag = delFlag;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getModifier() {
		return Modifier;
	}
	public void setModifier(String modifier) {
		Modifier = modifier;
	}
	public String getModifyDate() {
		return ModifyDate;
	}
	public void setModifyDate(String modifyDate) {
		ModifyDate = modifyDate;
	}
}
