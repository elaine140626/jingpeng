package com.curing.statisticsForms.model;

public class DangerousBridge {
	private String Id;
	private String CityId; // 项目id
	private Double ImplementM; // 今年实施量（m）
	private Double ImplementSeat; // 今年实施量（座）
	private Double LeadM; // 超前完成（m）
	private Double LeadSeat; // 超前完成（座）
	private Double FinishInvest; // 完成投资（万元）
	private String ProjectProgress; // 项目进展情况
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

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public Double getImplementM() {
		return ImplementM;
	}

	public void setImplementM(Double implementM) {
		ImplementM = implementM;
	}

	public Double getImplementSeat() {
		return ImplementSeat;
	}

	public void setImplementSeat(Double implementSeat) {
		ImplementSeat = implementSeat;
	}

	public Double getLeadM() {
		return LeadM;
	}

	public void setLeadM(Double leadM) {
		LeadM = leadM;
	}

	public Double getLeadSeat() {
		return LeadSeat;
	}

	public void setLeadSeat(Double leadSeat) {
		LeadSeat = leadSeat;
	}

	public Double getFinishInvest() {
		return FinishInvest;
	}

	public void setFinishInvest(Double finishInvest) {
		FinishInvest = finishInvest;
	}

	public String getProjectProgress() {
		return ProjectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		ProjectProgress = projectProgress;
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
