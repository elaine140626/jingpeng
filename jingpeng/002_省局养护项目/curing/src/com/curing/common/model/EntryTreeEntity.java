package com.curing.common.model;

public class EntryTreeEntity {
	private String ciid; // 市id
	private String CityName; // 市名称
	private String couid; // 县id
	private String County; // 县名称
	private String pid; // 项目名称id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String ContractSection; // 合同段
	private String ContractingUnit; // 承包单位
	private Double Mileage; // 里程

	public String getCiid() {
		return ciid;
	}

	public void setCiid(String ciid) {
		this.ciid = ciid;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getCouid() {
		return couid;
	}

	public void setCouid(String couid) {
		this.couid = couid;
	}

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		County = county;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public Double getMileage() {
		return Mileage;
	}

	public void setMileage(Double mileage) {
		Mileage = mileage;
	}

}
