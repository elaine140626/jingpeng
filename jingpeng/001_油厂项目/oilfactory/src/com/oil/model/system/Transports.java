package com.oil.model.system;

public class Transports {
	private int id;
	private int CustomerId;//客户Id
	private String CustomerCode;//客户编号
	private String StartAddress;//起运地点
	private String Transports;//止运地点
	private String Distance;//运输距离
	private String CoordinateX;//X
	private String CoordinateY;//Y
	private String Remarks;//备注
	private int IsDel;//删除标记
	private String Creater;//创建人
	private String CreaterDate;//创建日期
	private String Reviser;//修改人
	private String ReviserDate;//修改日期
	private int Serialnumber;// 序号
	private String Operate;//操作
	private String CustomerName; //客户名称
	private int cisDel; //客户删除标记
	
	
	public String getCustomerCode() {
		return CustomerCode;
	}
	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public int getCisDel() {
		return cisDel;
	}
	public void setCisDel(int cisDel) {
		this.cisDel = cisDel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public String getCoordinateX() {
		return CoordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		CoordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return CoordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		CoordinateY = coordinateY;
	}
	public String getTransports() {
		return Transports;
	}
	public void setTransports(String transports) {
		Transports = transports;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
		IsDel = isDel;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public String getCreaterDate() {
		return CreaterDate;
	}
	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}
	public String getReviser() {
		return Reviser;
	}
	public void setReviser(String reviser) {
		Reviser = reviser;
	}
	public String getReviserDate() {
		return ReviserDate;
	}
	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}
	public int getSerialnumber() {
		return Serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		Serialnumber = serialnumber;
	}
	public String getOperate() {
		return Operate;
	}
	public void setOperate(String operate) {
		Operate = operate;
	}
	public String getStartAddress() {
		return StartAddress;
	}
	public void setStartAddress(String startAddress) {
		StartAddress = startAddress;
	}
	
}
