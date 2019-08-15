package com.oil.model.sales;

public class SalesOrdersInfoEntity {
	private int Id; // 订单id
	private String OrderNumber; // 订单编号
	private int ContractId; // 合同id
	private String ContractNumber; // 合同编号
	private int CustomerId; // 客户id
	private String CustomerName; // 客户名称
	private String CustomerCode; // 客户编号
	private String CustomerAlias; // 客户别称
	private String OrderState; // 订单状态
	private String OrderStateName; // 订单状态（数据字典） 0:执行中 1:未执行 2:完成 3:作废 4:其他
	private int UserInfoId; // 销售员id
	private String Name; // 销售员
	private String Address; // 送货地址
	private String Contacts; // 联系人
	private String Telephone; // 联系方式
	private String Remarks; // 备注
	private String SalesCompanyName; // 销售公司名称
	private int IsActualDelivery; // 是否实际发货
	private String CancellationCause;//作废原因
	private String Creater;
	private String Reviser;
	private String CreaterDate;
	private int SalesCompanyId;//销售公司Id

	public String getCancellationCause() {
		return CancellationCause;
	}

	public void setCancellationCause(String cancellationCause) {
		CancellationCause = cancellationCause;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public int getContractId() {
		return ContractId;
	}

	public void setContractId(int contractId) {
		ContractId = contractId;
	}

	public String getContractNumber() {
		return ContractNumber;
	}

	public void setContractNumber(String contractNumber) {
		ContractNumber = contractNumber;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerAlias() {
		return CustomerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		CustomerAlias = customerAlias;
	}

	public String getOrderState() {
		return OrderState;
	}

	public void setOrderState(String orderState) {
		OrderState = orderState;
	}

	public String getOrderStateName() {
		return OrderStateName;
	}

	public void setOrderStateName(String orderStateName) {
		OrderStateName = orderStateName;
	}

	public int getUserInfoId() {
		return UserInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		UserInfoId = userInfoId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getContacts() {
		return Contacts;
	}

	public void setContacts(String contacts) {
		Contacts = contacts;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getSalesCompanyName() {
		return SalesCompanyName;
	}

	public void setSalesCompanyName(String salesCompanyName) {
		SalesCompanyName = salesCompanyName;
	}

	public int getIsActualDelivery() {
		return IsActualDelivery;
	}

	public void setIsActualDelivery(int isActualDelivery) {
		IsActualDelivery = isActualDelivery;
	}

	public String getCreater() {
		return Creater;
	}

	public void setCreater(String creater) {
		Creater = creater;
	}

	public String getReviser() {
		return Reviser;
	}

	public void setReviser(String reviser) {
		Reviser = reviser;
	}

	public int getSalesCompanyId() {
		return SalesCompanyId;
	}

	public void setSalesCompanyId(int salesCompanyId) {
		SalesCompanyId = salesCompanyId;
	}

	public String getCreaterDate() {
		return CreaterDate;
	}

	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	
}
