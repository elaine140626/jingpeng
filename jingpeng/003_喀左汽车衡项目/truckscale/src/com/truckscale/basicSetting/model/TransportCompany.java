package com.truckscale.basicSetting.model;
/**
 * 
 * @Title TransportCompany(运输单位表)
 * @author Administrator
 * @date 2019年4月9日
 */
public class TransportCompany {
	private int id;//自增长id
	private String transportCompanyNumber;//运输单位编号
	private String transportCompanyName;//运输单位
	private String carOwnerName;//车主姓名
	private String carOwnerTelephone;//车主电话
	private String remarks;//备注
	private int isDel;//删除标记(0未删除， 1删除)
	private String creater;//创建人
	private String createrDate;//创建日期
	private String reviser;//修改人
	private String reviserDate;//修改日期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransportCompanyNumber() {
		return transportCompanyNumber;
	}
	public void setTransportCompanyNumber(String transportCompanyNumber) {
		this.transportCompanyNumber = transportCompanyNumber;
	}
	public String getTransportCompanyName() {
		return transportCompanyName;
	}
	public void setTransportCompanyName(String transportCompanyName) {
		this.transportCompanyName = transportCompanyName;
	}
	public String getCarOwnerName() {
		return carOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}
	public String getCarOwnerTelephone() {
		return carOwnerTelephone;
	}
	public void setCarOwnerTelephone(String carOwnerTelephone) {
		this.carOwnerTelephone = carOwnerTelephone;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterDate() {
		return createrDate;
	}
	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviserDate() {
		return reviserDate;
	}
	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}
	
}
