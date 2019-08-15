package com.oil.model.sales;

public class CustomerMessage {

	private String uuid;
	private String customer_number;
	private String customer_name;
	private String parent_nodeId;
	private String province;
	private String address;
	private String contacts;
	private String telephone;
	private String other_number;
	private String remarks;
	private String creater;
	private String creater_date;
	private String reviser;
	private String reviser_date;
	
	private String operate;//����
	private int rownumber; //���
	private int tid;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getParent_nodeId() {
		return parent_nodeId;
	}
	public void setParent_nodeId(String parent_nodeId) {
		this.parent_nodeId = parent_nodeId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getOther_number() {
		return other_number;
	}
	public void setOther_number(String other_number) {
		this.other_number = other_number;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreater_date() {
		return creater_date;
	}
	public void setCreater_date(String creater_date) {
		this.creater_date = creater_date;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviser_date() {
		return reviser_date;
	}
	public void setReviser_date(String reviser_date) {
		this.reviser_date = reviser_date;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public int getRownumber() {
		return rownumber;
	}
	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
	
	
}
