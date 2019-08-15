package com.MixStation.model;

public class PlatformCementWarningDeviationEntity {
	private int serialNumber;// 序号
	private int Org_ID;// 组织机构编码
	private String Org_Name;// 机构名称
	private Double Aggregate_Deviation;// 骨料仓偏差
	private Double Cement_Deviation;// 水泥仓偏差
	private Double Water_Deviation;// 水仓偏差
	private Double Admixture_Deviation;// 外掺剂仓偏差
	private String Operator;// 创建人
	
	private Double Aggregate_DeviationDown;// 骨料仓偏差下限
	private Double Cement_DeviationDown;//水泥仓偏差下限
	private Double Water_DeviationDown;// 水仓偏差下限
	private Double Admixture_DeviationDown;// 外掺剂仓偏差下限
	private Double Min_MixTime;// 外掺剂仓偏差下限
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getOrg_ID() {
		return Org_ID;
	}
	public void setOrg_ID(int org_ID) {
		Org_ID = org_ID;
	}
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	public Double getAggregate_Deviation() {
		return Aggregate_Deviation;
	}
	public void setAggregate_Deviation(Double aggregate_Deviation) {
		Aggregate_Deviation = aggregate_Deviation;
	}
	public Double getCement_Deviation() {
		return Cement_Deviation;
	}
	public void setCement_Deviation(Double cement_Deviation) {
		Cement_Deviation = cement_Deviation;
	}
	public Double getWater_Deviation() {
		return Water_Deviation;
	}
	public void setWater_Deviation(Double water_Deviation) {
		Water_Deviation = water_Deviation;
	}
	public Double getAdmixture_Deviation() {
		return Admixture_Deviation;
	}
	public void setAdmixture_Deviation(Double admixture_Deviation) {
		Admixture_Deviation = admixture_Deviation;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public Double getAggregate_DeviationDown() {
		return Aggregate_DeviationDown;
	}
	public void setAggregate_DeviationDown(Double aggregate_DeviationDown) {
		Aggregate_DeviationDown = aggregate_DeviationDown;
	}
	public Double getCement_DeviationDown() {
		return Cement_DeviationDown;
	}
	public void setCement_DeviationDown(Double cement_DeviationDown) {
		Cement_DeviationDown = cement_DeviationDown;
	}
	public Double getWater_DeviationDown() {
		return Water_DeviationDown;
	}
	public void setWater_DeviationDown(Double water_DeviationDown) {
		Water_DeviationDown = water_DeviationDown;
	}
	public Double getAdmixture_DeviationDown() {
		return Admixture_DeviationDown;
	}
	public void setAdmixture_DeviationDown(Double admixture_DeviationDown) {
		Admixture_DeviationDown = admixture_DeviationDown;
	}
	public Double getMin_MixTime() {
		return Min_MixTime;
	}
	public void setMin_MixTime(Double min_MixTime) {
		Min_MixTime = min_MixTime;
	}


}
