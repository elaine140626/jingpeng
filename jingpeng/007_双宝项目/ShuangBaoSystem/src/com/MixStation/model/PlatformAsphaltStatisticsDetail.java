package com.MixStation.model;

public class PlatformAsphaltStatisticsDetail {
	private int serialNumber;// 序号
	private String Org_ID;
	private String Org_Name;// 拌合站名称
	private String Equipment_Name;// 拌合设备
	private String collect_Date;//采集日期
	private String ZongChanLiang;// 生产总量(kg)
	private String LQYongLiang;// 沥青用量(kg)
	private String TotalCount;// 生产总盘数
	private String HeGePanShu;// 合格盘数
	private String BuHeGePanShu;// 不合格盘数
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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
	public String getCollect_Date() {
		return collect_Date;
	}
	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}
	public String getZongChanLiang() {
		return ZongChanLiang;
	}
	public void setZongChanLiang(String zongChanLiang) {
		ZongChanLiang = zongChanLiang;
	}
	public String getLQYongLiang() {
		return LQYongLiang;
	}
	public void setLQYongLiang(String lQYongLiang) {
		LQYongLiang = lQYongLiang;
	}
	public String getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(String totalCount) {
		TotalCount = totalCount;
	}
	public String getHeGePanShu() {
		return HeGePanShu;
	}
	public void setHeGePanShu(String heGePanShu) {
		HeGePanShu = heGePanShu;
	}
	public String getBuHeGePanShu() {
		return BuHeGePanShu;
	}
	public void setBuHeGePanShu(String buHeGePanShu) {
		BuHeGePanShu = buHeGePanShu;
	}	
}
