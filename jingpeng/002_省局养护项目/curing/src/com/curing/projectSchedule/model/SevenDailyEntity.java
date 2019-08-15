package com.curing.projectSchedule.model;

public class SevenDailyEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String ContractSection; // 合同段
	private String ContractingUnit; // 承包单位
	private Double Mileage; // 里程（km)
	private String SubheadNumberId; // 第100章-第700章子目号id
	private String SubheadNumber; // 第100章-第700章子目号
	private String SubheadName; // 第100章-第700章子目名称
	private Double Total; // 总量
	private Double UnitPrice; // 单价
	private Double TotalPrice; // 总价
	private Double ThisComplete; // 本期完成（平方米）
	private Double ThisCapital; // 本期完成资金
	private Double CumulativeComplete; // 累计完成（平方米）
	private Double CumulativeCapital; // 累计完成资金
	private Double CumulativeSchedule; // 累计完成进度（%）
	private String DailyYear; // 年
	private String DailyMonth; // 月
	private String DailyDay; // 周
	private String Remarks; // 备注
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

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
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

	public String getSubheadNumberId() {
		return SubheadNumberId;
	}

	public void setSubheadNumberId(String subheadNumberId) {
		SubheadNumberId = subheadNumberId;
	}

	public String getSubheadNumber() {
		return SubheadNumber;
	}

	public void setSubheadNumber(String subheadNumber) {
		SubheadNumber = subheadNumber;
	}

	public String getSubheadName() {
		return SubheadName;
	}

	public void setSubheadName(String subheadName) {
		SubheadName = subheadName;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}

	public Double getThisComplete() {
		return ThisComplete;
	}

	public void setThisComplete(Double thisComplete) {
		ThisComplete = thisComplete;
	}

	public Double getThisCapital() {
		return ThisCapital;
	}

	public void setThisCapital(Double thisCapital) {
		ThisCapital = thisCapital;
	}

	public Double getCumulativeComplete() {
		return CumulativeComplete;
	}

	public void setCumulativeComplete(Double cumulativeComplete) {
		CumulativeComplete = cumulativeComplete;
	}

	public Double getCumulativeCapital() {
		return CumulativeCapital;
	}

	public void setCumulativeCapital(Double cumulativeCapital) {
		CumulativeCapital = cumulativeCapital;
	}

	public Double getCumulativeSchedule() {
		return CumulativeSchedule;
	}

	public void setCumulativeSchedule(Double cumulativeSchedule) {
		CumulativeSchedule = cumulativeSchedule;
	}

	public String getDailyYear() {
		return DailyYear;
	}

	public void setDailyYear(String dailyYear) {
		DailyYear = dailyYear;
	}

	public String getDailyMonth() {
		return DailyMonth;
	}

	public void setDailyMonth(String dailyMonth) {
		DailyMonth = dailyMonth;
	}

	public String getDailyDay() {
		return DailyDay;
	}

	public void setDailyDay(String dailyDay) {
		DailyDay = dailyDay;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
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
