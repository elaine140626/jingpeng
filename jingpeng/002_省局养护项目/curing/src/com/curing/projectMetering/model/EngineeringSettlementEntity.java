package com.curing.projectMetering.model;

public class EngineeringSettlementEntity {
	private String Id; // 主键
	private String ProjectId; // 项目id
	private String ProjectName; // 项目名称
	private String Content; // 内容
	private Integer RowNumber; // 行次
	private Double CurrentAmount; // 本期金额
	private Double YearAmount; // 本年累计金额
	private Double StartAmount; // 自开工累计金额
	private String DailyYear; // 年
	private String DailyMonth; // 月
	private String Remarks; // 备注
	private int IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	
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
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	
	
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Integer getRowNumber() {
		return RowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		RowNumber = rowNumber;
	}
	public Double getCurrentAmount() {
		return CurrentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		CurrentAmount = currentAmount;
	}
	public Double getYearAmount() {
		return YearAmount;
	}
	public void setYearAmount(Double yearAmount) {
		YearAmount = yearAmount;
	}
	public Double getStartAmount() {
		return StartAmount;
	}
	public void setStartAmount(Double startAmount) {
		StartAmount = startAmount;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
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
}
