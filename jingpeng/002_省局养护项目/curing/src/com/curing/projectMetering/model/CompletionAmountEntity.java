package com.curing.projectMetering.model;

public class CompletionAmountEntity {
	private Integer ProjectId; // 项目id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String ContractSection; // 合同段
	private String ContractingUnit; // 承包单位
	private Double Mileage; // 里程
	private Integer SubheadNumberId; // 第100章-第700章子目号id
	private String SubheadNumber; // 第100章-第700章子目号
	private String SubheadName; // 第100章-第700章子目号名称
	private Double Total; // 总量
	private Double ContractAmount; // 合同金额
	private Double MonthCompletion; // 本月完成金额
	private Double YearCompletion; // 本年累计完成金额
	private Double StartCompletion; // 自开工起累计完成金额
	private Double MonthThisComplete; // 本月完成工程量
	private Double YearThisComplete; // 本年累计完成工程量
	private Double StartThisComplete; // 自开工起累计完成工程量
	
	public Double getTotal() {
		return Total;
	}
	public void setTotal(Double total) {
		Total = total;
	}
	public Double getMonthThisComplete() {
		return MonthThisComplete;
	}
	public void setMonthThisComplete(Double monthThisComplete) {
		MonthThisComplete = monthThisComplete;
	}
	public Double getYearThisComplete() {
		return YearThisComplete;
	}
	public void setYearThisComplete(Double yearThisComplete) {
		YearThisComplete = yearThisComplete;
	}
	public Double getStartThisComplete() {
		return StartThisComplete;
	}
	public void setStartThisComplete(Double startThisComplete) {
		StartThisComplete = startThisComplete;
	}
	public Integer getProjectId() {
		return ProjectId;
	}
	public void setProjectId(Integer projectId) {
		ProjectId = projectId;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public Integer getSubheadNumberId() {
		return SubheadNumberId;
	}
	public void setSubheadNumberId(Integer subheadNumberId) {
		SubheadNumberId = subheadNumberId;
	}
	public String getSubheadName() {
		return SubheadName;
	}
	public void setSubheadName(String subheadName) {
		SubheadName = subheadName;
	}
	public Double getContractAmount() {
		return ContractAmount;
	}
	public void setContractAmount(Double contractAmount) {
		ContractAmount = contractAmount;
	}
	public Double getMonthCompletion() {
		return MonthCompletion;
	}
	public void setMonthCompletion(Double monthCompletion) {
		MonthCompletion = monthCompletion;
	}
	public Double getYearCompletion() {
		return YearCompletion;
	}
	public void setYearCompletion(Double yearCompletion) {
		YearCompletion = yearCompletion;
	}
	public Double getStartCompletion() {
		return StartCompletion;
	}
	public void setStartCompletion(Double startCompletion) {
		StartCompletion = startCompletion;
	}
	public String getProjectNumber() {
		return ProjectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		ProjectNumber = projectNumber;
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
	public String getSubheadNumber() {
		return SubheadNumber;
	}
	public void setSubheadNumber(String subheadNumber) {
		SubheadNumber = subheadNumber;
	}
}
