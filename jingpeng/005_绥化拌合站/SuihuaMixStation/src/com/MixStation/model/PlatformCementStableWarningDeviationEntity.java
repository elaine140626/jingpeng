package com.MixStation.model;

public class PlatformCementStableWarningDeviationEntity {
	private int serialNumber;//序号
	private int id;
	private int org_ID;
	private String org_Name;//拌合站的名称
	private Double aggregate_Deviation;//骨料仓偏差
	private Double cement_Deviation;//水泥仓偏差
	private Double water_Deviation;//水仓偏差
	private Double admixture_Deviation;//外掺剂仓偏差
	private String mixing_Time;//拌合时间
	private String effective_Time;//生效时间
	private String create_Date;//创建日期
	private String operator;//创建人
	private String valid_Flag;//有效标识
	private String upload;//上传标识
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrg_ID() {
		return org_ID;
	}
	public void setOrg_ID(int org_ID) {
		this.org_ID = org_ID;
	}
	public Double getAggregate_Deviation() {
		return aggregate_Deviation;
	}
	public void setAggregate_Deviation(Double aggregate_Deviation) {
		this.aggregate_Deviation = aggregate_Deviation;
	}
	public Double getCement_Deviation() {
		return cement_Deviation;
	}
	public void setCement_Deviation(Double cement_Deviation) {
		this.cement_Deviation = cement_Deviation;
	}
	public Double getWater_Deviation() {
		return water_Deviation;
	}
	public void setWater_Deviation(Double water_Deviation) {
		this.water_Deviation = water_Deviation;
	}
	public Double getAdmixture_Deviation() {
		return admixture_Deviation;
	}
	public void setAdmixture_Deviation(Double admixture_Deviation) {
		this.admixture_Deviation = admixture_Deviation;
	}
	public String getMixing_Time() {
		return mixing_Time;
	}
	public void setMixing_Time(String mixing_Time) {
		this.mixing_Time = mixing_Time;
	}
	public String getEffective_Time() {
		return effective_Time;
	}
	public void setEffective_Time(String effective_Time) {
		this.effective_Time = effective_Time;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValid_Flag() {
		return valid_Flag;
	}
	public void setValid_Flag(String valid_Flag) {
		this.valid_Flag = valid_Flag;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getOrg_Name() {
		return org_Name;
	}
	public void setOrg_Name(String org_Name) {
		this.org_Name = org_Name;
	}
	
}