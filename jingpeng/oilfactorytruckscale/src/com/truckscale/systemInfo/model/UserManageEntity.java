package com.truckscale.systemInfo.model;

public class UserManageEntity {

	private int Id;
	private String UserName;
	private String PassWord;
	private int RoleCode;  //角色Code
	private String Name; //姓名
	private int Age; //年龄
	private String Sex; //性别
	private String Birthday; //出生日期
	private String Address; //地址
	private String Telephone; //电话
	private String UserPost;//职务
	private int UserState;//状态 启用?
	private String OtherNumbers; //其他联系方式
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Remarks;
	private String Reviser;
	private String ReviserDate;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public int getRoleCode() {
		return RoleCode;
	}
	public void setRoleCode(int roleCode) {
		RoleCode = roleCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getUserPost() {
		return UserPost;
	}
	public void setUserPost(String userPost) {
		UserPost = userPost;
	}
	public int getUserState() {
		return UserState;
	}
	public void setUserState(int userState) {
		UserState = userState;
	}
	public String getOtherNumbers() {
		return OtherNumbers;
	}
	public void setOtherNumbers(String otherNumbers) {
		OtherNumbers = otherNumbers;
	}
	public Integer getIsDel() {
		return IsDel;
	}
	public void setIsDel(Integer isDel) {
		IsDel = isDel;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public String getCreaterDate() {
		return CreaterDate;
	}
	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}
	
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getReviser() {
		return Reviser;
	}
	public void setReviser(String reviser) {
		Reviser = reviser;
	}
	public String getReviserDate() {
		return ReviserDate;
	}
	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}
	
	
	
}
