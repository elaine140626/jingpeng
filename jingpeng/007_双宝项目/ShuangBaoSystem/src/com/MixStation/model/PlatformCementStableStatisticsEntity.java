package com.MixStation.model;

public class PlatformCementStableStatisticsEntity {
	private int serialNumber;//序号
	private String org_Name;//拌合站的名称
	private String org_ID;
	private String id;
	private String zongChanLiang;//生产总量(kg)
	private String sNYongLiang;//水泥用量(kg)
	private String totalCount;//生产总重量(kg)
	private String heGePanShu;//合格重量(kg)
	private String buHeGePanShu;//不合格重量(kg)
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOrg_Name() {
		return org_Name;
	}
	public void setOrg_Name(String org_Name) {
		this.org_Name = org_Name;
	}
	public String getOrg_ID() {
		return org_ID;
	}
	public void setOrg_ID(String org_ID) {
		this.org_ID = org_ID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZongChanLiang() {
		return zongChanLiang;
	}
	public void setZongChanLiang(String zongChanLiang) {
		this.zongChanLiang = zongChanLiang;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getHeGePanShu() {
		return heGePanShu;
	}
	public void setHeGePanShu(String heGePanShu) {
		this.heGePanShu = heGePanShu;
	}
	public String getBuHeGePanShu() {
		return buHeGePanShu;
	}
	public void setBuHeGePanShu(String buHeGePanShu) {
		this.buHeGePanShu = buHeGePanShu;
	}
	public String getsNYongLiang() {
		return sNYongLiang;
	}
	public void setsNYongLiang(String sNYongLiang) {
		this.sNYongLiang = sNYongLiang;
	}
	
}
