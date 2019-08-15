package com.MixStation.model;

public class PlatformCementWarningInformationEntity {
	private int serialNumber;// 序号
	private String Id;
	private String Org_ID;
	private String Org_Name;// 拌合站名称
	private String Equipment_Name;// 设备名称
	private String TotalCount;// 生产总盘数
	private String Unqualified;// 预警盘数
	private String GLUnqualified;// 骨料
	private String FLUnqualified;// 粉料
	private String SNUnqualified;// 水泥
	private String WCJUnqualified;// 外掺剂
	private String JPUnqualified;// 级配
	private String collect_Date;// 日期

	public String getSNUnqualified() {
		return SNUnqualified;
	}

	public void setSNUnqualified(String sNUnqualified) {
		SNUnqualified = sNUnqualified;
	}

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

	public String getEquipment_Name() {
		return Equipment_Name;
	}

	public void setEquipment_Name(String equipment_Name) {
		Equipment_Name = equipment_Name;
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

	public String getGLUnqualified() {
		return GLUnqualified;
	}

	public void setGLUnqualified(String gLUnqualified) {
		GLUnqualified = gLUnqualified;
	}

	public String getFLUnqualified() {
		return FLUnqualified;
	}

	public void setFLUnqualified(String fLUnqualified) {
		FLUnqualified = fLUnqualified;
	}


	public String getWCJUnqualified() {
		return WCJUnqualified;
	}

	public void setWCJUnqualified(String wCJUnqualified) {
		WCJUnqualified = wCJUnqualified;
	}

	public String getJPUnqualified() {
		return JPUnqualified;
	}

	public void setJPUnqualified(String jPUnqualified) {
		JPUnqualified = jPUnqualified;
	}

	public String getCollect_Date() {
		return collect_Date;
	}

	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}

}
