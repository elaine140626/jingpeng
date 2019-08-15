package com.curing.login.model;

public class UserInfo {
	private String Id;
	private String UserName;
	private String PassWord;
	private String PassWordAlign;
	private Integer RoleCode; // 角色Code
	private Integer CityId; // 市ID
	private Integer CountyId; // 县Id
	private String Name; // 姓名
	private Integer Age; // 年龄
	private String Sex; // 性别
	private String Birthday; // 出生日期
	private String Address; // 地址
	private String Telephone; // 电话
	private String OtherNumbers; // 其他联系方式
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
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

	public String getPassWordAlign() {
		return PassWordAlign;
	}

	public void setPassWordAlign(String passWordAlign) {
		PassWordAlign = passWordAlign;
	}

	public Integer getRoleCode() {
		return RoleCode;
	}

	public void setRoleCode(Integer roleCode) {
		RoleCode = roleCode;
	}

	public Integer getCityId() {
		return CityId;
	}

	public void setCityId(Integer cityId) {
		CityId = cityId;
	}

	public Integer getCountyId() {
		return CountyId;
	}

	public void setCountyId(Integer countyId) {
		CountyId = countyId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
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