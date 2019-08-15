package com.curing.statisticsForms.model;

public class DangerousBridgeEntity {
	private String CityId; // 市id
	private String CityName; // 市名字
	private Double EngineeringAmount; // 桥梁工程计划量（m）
	private Double EngineeringCount; // 桥梁工程计划量（座）
	private Double ImplementM; // 今年实施量（m)
	private Double ImplementSeat; // 今年实施量（座)
	private Double LeadM; // 超前完成（m)
	private Double LeadSeat; // 超前完成（座)
	private Double StartNumber; // 桥梁工程开工数目（座）
	private Double StartRate; // 桥梁工程开工率（%）
	private Double InvestmentProvince; // 桥梁工程省补总投资（万元）
	private Double FinishInvest; // 完成投资/万元
	private Double TunnelSchedule; // 桥梁工程工程进度（%）
	private String ProjectProgress; // 项目进展情况

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

	public Double getEngineeringAmount() {
		return EngineeringAmount;
	}

	public void setEngineeringAmount(Double engineeringAmount) {
		EngineeringAmount = engineeringAmount;
	}

	public Double getEngineeringCount() {
		return EngineeringCount;
	}

	public void setEngineeringCount(Double engineeringCount) {
		EngineeringCount = engineeringCount;
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

	public Double getStartNumber() {
		return StartNumber;
	}

	public void setStartNumber(Double startNumber) {
		StartNumber = startNumber;
	}

	public Double getStartRate() {
		return StartRate;
	}

	public void setStartRate(Double startRate) {
		StartRate = startRate;
	}

	public Double getInvestmentProvince() {
		return InvestmentProvince;
	}

	public void setInvestmentProvince(Double investmentProvince) {
		InvestmentProvince = investmentProvince;
	}

	public Double getFinishInvest() {
		return FinishInvest;
	}

	public void setFinishInvest(Double finishInvest) {
		FinishInvest = finishInvest;
	}

	public Double getTunnelSchedule() {
		return TunnelSchedule;
	}

	public void setTunnelSchedule(Double tunnelSchedule) {
		TunnelSchedule = tunnelSchedule;
	}

	public String getProjectProgress() {
		return ProjectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		ProjectProgress = projectProgress;
	}

}
