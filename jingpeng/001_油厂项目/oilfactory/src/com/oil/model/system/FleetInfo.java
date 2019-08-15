package com.oil.model.system;


public class FleetInfo {

	private int id;
	private String fleetNumber; //车队编号
	private String fleetName; //车队名称
	private String contacts; //车队联系人
	private String telephone; //联系电话
	private String remarks; //备注
	private int isDel; //删除标记
	private String creater; //创建人
	private String createrDate; //创建日期
	private String reviser; //修改人
	private String reviserDate; //修改日期
	private String operation;
	private int rownumber;
	
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getRownumber() {
		return rownumber;
	}
	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFleetNumber() {
		return fleetNumber;
	}
	public void setFleetNumber(String fleetNumber) {
		this.fleetNumber = fleetNumber;
	}
	public String getFleetName() {
		return fleetName;
	}
	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
