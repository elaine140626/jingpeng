package com.MixStation.model;

public class PlatformCementStableWarningInformationEntity {
	private int serialNumber;
	private String Id;
	private String Org_ID;
	private String Org_Name;//拌合站的名称
	private String TotalCount;//生产的总重量
	private String Unqualified;//预警重量
	private String SNUnqualified;//水泥
	private String GLUnqualified;//骨料
	private String WaterUnqualified;//水剂
	private String WCJUnqualified;//外掺剂
	private String collect_Date;//日期
	private String str_Equipment_Name;//拌和设备
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
	public String getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(String totalCount) {
		TotalCount = totalCount;
	}
	public String getUnqualified() {
		return Unqualified;
	}
	public void setUnqualified(String unqualified) {
		Unqualified = unqualified;
	}
	public String getSNUnqualified() {
		return SNUnqualified;
	}
	public void setSNUnqualified(String sNUnqualified) {
		SNUnqualified = sNUnqualified;
	}
	public String getGLUnqualified() {
		return GLUnqualified;
	}
	public void setGLUnqualified(String gLUnqualified) {
		GLUnqualified = gLUnqualified;
	}
	public String getWaterUnqualified() {
		return WaterUnqualified;
	}
	public void setWaterUnqualified(String waterUnqualified) {
		WaterUnqualified = waterUnqualified;
	}
	public String getWCJUnqualified() {
		return WCJUnqualified;
	}
	public void setWCJUnqualified(String wCJUnqualified) {
		WCJUnqualified = wCJUnqualified;
	}
	public String getStr_Equipment_Name() {
		return str_Equipment_Name;
	}
	public void setStr_Equipment_Name(String str_Equipment_Name) {
		this.str_Equipment_Name = str_Equipment_Name;
	}
	public String getCollect_Date() {
		return collect_Date;
	}
	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}
	
}
