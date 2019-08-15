package com.oil.model.system;

public class CarInfo {
	private Integer id;
	private Integer fleetId; // 车队id
	private String carNumber; // 车辆编码
	private String plateNumber; // 车牌号码
	private String plateNumber2; // 车牌号码
	private Integer isLargeCar; // 是否是大型车
	private String driver1; // 司机1
	private String telephone1; // 联系电话1
	private String driver2; // 司机2
	private String telephone2; // 联系电话2
	private String remarks; // 备注
	private Double loadTon; // 荷载吨数
	private Integer isDel; // 删除标记
	private String creater; // 创建人
	private String createrDate; // 创建日期
	private String reviser; // 修改人
	private String reviserDate; // 修改日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFleetId() {
		return fleetId;
	}

	public void setFleetId(Integer fleetId) {
		this.fleetId = fleetId;
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

	public String getPlateNumber2() {
		return plateNumber2;
	}

	public void setPlateNumber2(String plateNumber2) {
		this.plateNumber2 = plateNumber2;
	}

	public Integer getIsLargeCar() {
		return isLargeCar;
	}

	public void setIsLargeCar(Integer isLargeCar) {
		this.isLargeCar = isLargeCar;
	}

	public String getDriver1() {
		return driver1;
	}

	public void setDriver1(String driver1) {
		this.driver1 = driver1;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getDriver2() {
		return driver2;
	}

	public void setDriver2(String driver2) {
		this.driver2 = driver2;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getLoadTon() {
		return loadTon;
	}

	public void setLoadTon(Double loadTon) {
		this.loadTon = loadTon;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
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
