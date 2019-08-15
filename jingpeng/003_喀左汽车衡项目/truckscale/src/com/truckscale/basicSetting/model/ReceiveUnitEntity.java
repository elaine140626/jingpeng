package com.truckscale.basicSetting.model;

public class ReceiveUnitEntity {
	
	//主键id
	private int id;
	
	//收料单位编号
	private String receiveUnitNumber;
	
	//收料单位名称
	private String receiveUnitName;
	
	//用途属性 0:用于入库 1:用于出库
	private String purpose;
	
	//联系人
	private String contacts;
	
	//联系电话
	private String telephone;
	
	//备注
	private String remarks;
	
	//删除标志
	private int isDel;
	
	//创建人
	private String creater;
	
	//创建时间
	private String createrDate;
	
	//修改人
    private String reviser;
	
    //修改时间
	private String reviserDate;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getReceiveUnitNumber() {
		return receiveUnitNumber;
	}


	public void setReceiveUnitNumber(String receiveUnitNumber) {
		this.receiveUnitNumber = receiveUnitNumber;
	}


	public String getReceiveUnitName() {
		return receiveUnitName;
	}


	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
