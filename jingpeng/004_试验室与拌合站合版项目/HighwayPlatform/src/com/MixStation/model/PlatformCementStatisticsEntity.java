package com.MixStation.model;

public class PlatformCementStatisticsEntity {
	private int serialNumber;// 序号
	private String Org_Name;// 拌合站名称
	private String Id;
	private String Org_ID;
	private String ZongChanLiang;// 生产总量(kg)
	private String SNYongLiang;// 水泥用量(kg)
	private String TotalCount;// 生产总盘数
	private String HeGePanShu;// 合格盘数
	private String BuHeGePanShu;// 不合格盘数

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
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

	public String getZongChanLiang() {
		return ZongChanLiang;
	}

	public void setZongChanLiang(String zongChanLiang) {
		ZongChanLiang = zongChanLiang;
	}

	public String getSNYongLiang() {
		return SNYongLiang;
	}

	public void setSNYongLiang(String sNYongLiang) {
		SNYongLiang = sNYongLiang;
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
