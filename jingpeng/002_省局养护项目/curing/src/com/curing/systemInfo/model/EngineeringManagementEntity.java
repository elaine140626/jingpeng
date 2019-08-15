package com.curing.systemInfo.model;

public class EngineeringManagementEntity {

	private String Id; // id
	private String CityId; // 上级ID
	private String CityName; //市名字
	private String County; //县名称
	private String ProjectNumber; //项目编号
	private String ProjectName; //项目名称
	private String ContractSection; //合同段
	private String ContractingUnit; //承包单位
	private String Mileage; //里程（km)
	private int IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	private Integer CityPid; //爷爷
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String county) {
		County = county;
	}
	public String getProjectNumber() {
		return ProjectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		ProjectNumber = projectNumber;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getContractSection() {
		return ContractSection;
	}
	public void setContractSection(String contractSection) {
		ContractSection = contractSection;
	}
	public String getContractingUnit() {
		return ContractingUnit;
	}
	public void setContractingUnit(String contractingUnit) {
		ContractingUnit = contractingUnit;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
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
	public Integer getCityPid() {
		return CityPid;
	}
	public void setCityPid(Integer cityPid) {
		CityPid = cityPid;
	}
	
	
	
	
}
