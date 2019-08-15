package com.MixStation.model;

public class PlatformAsphaltWarningDeviationEntity {
	private int serialNumber;// 序号
	private int Org_ID;// 组织机构编码
	
	private int Product_ID;//混合料名称型号Id
	private String Org_Name;// 机构名称
	private Double Dev_Aggregate;// 骨料仓偏差（高级预警）
	private Double Dev_Powder;// 粉料仓偏差（高级预警）
	private Double Dev_Admixture;// 外掺剂仓偏差（高级预警）
	private Double Dev_Asphalt;// 沥青仓偏差（油石比高级预警）
	private String Operator;// 创建人
	private String Create_Date;//创建时间
	private Double MixTemperatureUp;// 混合料温度上限
	private Double MixTemperatureDown;// 混合料温度下限
	
	private Double Dev_AggregateDown;// 骨料仓偏差下限（初级预警）
	private Double Dev_PowderDown;// 粉料仓偏差下限（初级预警）
	private Double Dev_AdmixtureDown;// 外掺剂仓偏差下限（初级预警）
	private Double Dev_AsphaltDown;// 沥青仓偏差下限（油石比初级预警）
	
	private Double Dev_AggregateCenter;// 骨料仓偏差下限（中级预警）
	private Double Dev_PowderCenter;// 粉料仓偏差下限（中级预警）
	private Double Dev_AdmixtureCenter;// 外掺剂仓偏差下限（中级预警）
	private Double Dev_AsphaltCenter;// 沥青仓偏差下限（油石比中级预警）
	
	private String material;
	

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

	
	public int getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public Double getDev_Aggregate() {
		return Dev_Aggregate;
	}

	public void setDev_Aggregate(Double dev_Aggregate) {
		Dev_Aggregate = dev_Aggregate;
	}

	public Double getDev_Powder() {
		return Dev_Powder;
	}

	public void setDev_Powder(Double dev_Powder) {
		Dev_Powder = dev_Powder;
	}

	public Double getDev_Admixture() {
		return Dev_Admixture;
	}

	public void setDev_Admixture(Double dev_Admixture) {
		Dev_Admixture = dev_Admixture;
	}

	public Double getDev_Asphalt() {
		return Dev_Asphalt;
	}

	public void setDev_Asphalt(Double dev_Asphalt) {
		Dev_Asphalt = dev_Asphalt;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public Double getMixTemperatureUp() {
		return MixTemperatureUp;
	}

	public void setMixTemperatureUp(Double mixTemperatureUp) {
		MixTemperatureUp = mixTemperatureUp;
	}

	public Double getMixTemperatureDown() {
		return MixTemperatureDown;
	}

	public void setMixTemperatureDown(Double mixTemperatureDown) {
		MixTemperatureDown = mixTemperatureDown;
	}

	public Double getDev_AggregateDown() {
		return Dev_AggregateDown;
	}

	public void setDev_AggregateDown(Double dev_AggregateDown) {
		Dev_AggregateDown = dev_AggregateDown;
	}

	public Double getDev_PowderDown() {
		return Dev_PowderDown;
	}

	public void setDev_PowderDown(Double dev_PowderDown) {
		Dev_PowderDown = dev_PowderDown;
	}

	public Double getDev_AdmixtureDown() {
		return Dev_AdmixtureDown;
	}

	public void setDev_AdmixtureDown(Double dev_AdmixtureDown) {
		Dev_AdmixtureDown = dev_AdmixtureDown;
	}

	public Double getDev_AsphaltDown() {
		return Dev_AsphaltDown;
	}

	public void setDev_AsphaltDown(Double dev_AsphaltDown) {
		Dev_AsphaltDown = dev_AsphaltDown;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Double getDev_AggregateCenter() {
		return Dev_AggregateCenter;
	}

	public void setDev_AggregateCenter(Double dev_AggregateCenter) {
		Dev_AggregateCenter = dev_AggregateCenter;
	}

	public Double getDev_AdmixtureCenter() {
		return Dev_AdmixtureCenter;
	}

	public void setDev_AdmixtureCenter(Double dev_AdmixtureCenter) {
		Dev_AdmixtureCenter = dev_AdmixtureCenter;
	}

	public Double getDev_PowderCenter() {
		return Dev_PowderCenter;
	}

	public void setDev_PowderCenter(Double dev_PowderCenter) {
		Dev_PowderCenter = dev_PowderCenter;
	}

	public Double getDev_AsphaltCenter() {
		return Dev_AsphaltCenter;
	}

	public void setDev_AsphaltCenter(Double dev_AsphaltCenter) {
		Dev_AsphaltCenter = dev_AsphaltCenter;
	}

	public String getCreate_Date() {
		return Create_Date;
	}

	public void setCreate_Date(String create_Date) {
		Create_Date = create_Date;
	}

	 
}
