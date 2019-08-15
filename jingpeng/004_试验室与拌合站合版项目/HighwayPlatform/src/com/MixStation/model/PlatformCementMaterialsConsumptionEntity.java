package com.MixStation.model;

public class PlatformCementMaterialsConsumptionEntity {
	private int serialNumber;// 序号
	private String Id;
	private String Org_ID;
	private String Org_Name;// 拌合站名称
	private String Material;// 原材料名称
	private String MaterialConsumption;// 消耗量
	private String collect_Date;// 日期

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOrg_ID() {
		return Org_ID;
	}

	public void setOrg_ID(String org_ID) {
		Org_ID = org_ID;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public String getMaterialConsumption() {
		return MaterialConsumption;
	}

	public void setMaterialConsumption(String materialConsumption) {
		MaterialConsumption = materialConsumption;
	}

	public String getCollect_Date() {
		return collect_Date;
	}

	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}

}
