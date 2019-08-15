package com.truckscale.basicSetting.model;
/**
 * 
 * @Title CarInfo(车辆表)
 * @author Administrator
 * @date 2019年4月10日
 */
public class CarInfo {
	private int id;//自增长id
	private String carNumber;//车辆编码
	private String plateNumber;//车牌号码
	private String carModel;//车辆型号
	private String driverName;//司机姓名
	private String telephone;//联系电话
	private Double tareWeight;//皮重
	private int transportUnitId;//运输单位id
	private int inOrTemporary;//厂内车辆/临时车辆 0:厂内车辆 1:临时车辆
	private String remarks;//备注
	private int isDel;//删除标记
	private String creater;//创建人
	private String createrDate;//创建日期
	private String reviser;//修改人
	private String reviserDate;//修改日期
	
	private String carOwnerName;//车主姓名
	private String transportCompanyName;//运输单位
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Double getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
	}
	public int getTransportUnitId() {
		return transportUnitId;
	}
	public void setTransportUnitId(int transportUnitId) {
		this.transportUnitId = transportUnitId;
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
	public String getCarOwnerName() {
		return carOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}
	public String getTransportCompanyName() {
		return transportCompanyName;
	}
	public void setTransportCompanyName(String transportCompanyName) {
		this.transportCompanyName = transportCompanyName;
	}
	public int getInOrTemporary() {
		return inOrTemporary;
	}
	public void setInOrTemporary(int inOrTemporary) {
		this.inOrTemporary = inOrTemporary;
	}
	
}
