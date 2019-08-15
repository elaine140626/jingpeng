package com.MixStation.model;

public class PlatformCementStableMaterialsConsumptionEntity {
	private int serialNumber;//序号
	private String id;
	private String org_ID;
	private String org_Name;//拌合站的名称
	private String material;//原材料名称
	private String materialConsumption;//消耗量
	private String collect_Date;//日期
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMaterialConsumption() {
		return materialConsumption;
	}
	public void setMaterialConsumption(String materialConsumption) {
		this.materialConsumption = materialConsumption;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrg_ID() {
		return org_ID;
	}
	public void setOrg_ID(String org_ID) {
		this.org_ID = org_ID;
	}
	public String getOrg_Name() {
		return org_Name;
	}
	public void setOrg_Name(String org_Name) {
		this.org_Name = org_Name;
	}
	public String getCollect_Date() {
		return collect_Date;
	}
	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}
	
}
