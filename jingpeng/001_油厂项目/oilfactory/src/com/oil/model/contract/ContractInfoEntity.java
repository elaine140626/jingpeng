package com.oil.model.contract;

public class ContractInfoEntity {
	private int Id;
	private String ContractNumber; // 合同编号
	private String ContractName; // 合同名称
	private int CustomerId; // 客户id
	private String CustomerName; // 客户名称
	private String CustomerCode; // 客户编号
	private Double SaleNumber; // 销售数量
	private int UserInfoId; // 销售员id
	private String Name; // 销售员
	private String ContractState; // 合同状态
	private String ContractStateName; // 合同状态（数据字典） 0:执行中 1:未执行 2:完成 3:作废 4:未鉴定 5:其他
	private String SalesCompanyName; // 销售公司名称
	private String ContractDate; // 合同日期
	private String Remarks; // 备注
	private String ContractRoute; // 附件
	private int IsIncoming; // 是否来料加工 0:是 1:否
	private int IsApproval;// 出库记录是否需要审批
	private String Creater;
	private String Reviser;
	private String Address; // 地址
	private String Contacts; // 联系人
	private String Telephone; // 联系电话
	private String CancellationCause;// 作废原因
	private int SalesCompanyId;// 销售公司id
	private int IsRelation;// 是否关联其它公司
	private int RelationCompanyId;// 关联其它公司id
	private int IsModify;// 是否可修改 0:可修改 1:不可修改

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getContractNumber() {
		return ContractNumber;
	}

	public void setContractNumber(String contractNumber) {
		ContractNumber = contractNumber;
	}

	public String getContractName() {
		return ContractName;
	}

	public void setContractName(String contractName) {
		ContractName = contractName;
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

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public Double getSaleNumber() {
		return SaleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		SaleNumber = saleNumber;
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

	public String getContractState() {
		return ContractState;
	}

	public void setContractState(String contractState) {
		ContractState = contractState;
	}

	public String getContractStateName() {
		return ContractStateName;
	}

	public void setContractStateName(String contractStateName) {
		ContractStateName = contractStateName;
	}

	public String getSalesCompanyName() {
		return SalesCompanyName;
	}

	public void setSalesCompanyName(String salesCompanyName) {
		SalesCompanyName = salesCompanyName;
	}

	public String getContractDate() {
		return ContractDate;
	}

	public void setContractDate(String contractDate) {
		ContractDate = contractDate;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getContractRoute() {
		return ContractRoute;
	}

	public void setContractRoute(String contractRoute) {
		ContractRoute = contractRoute;
	}

	public int getIsIncoming() {
		return IsIncoming;
	}

	public void setIsIncoming(int isIncoming) {
		IsIncoming = isIncoming;
	}

	public int getIsApproval() {
		return IsApproval;
	}

	public void setIsApproval(int isApproval) {
		IsApproval = isApproval;
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

	public String getCancellationCause() {
		return CancellationCause;
	}

	public void setCancellationCause(String cancellationCause) {
		CancellationCause = cancellationCause;
	}

	public int getSalesCompanyId() {
		return SalesCompanyId;
	}

	public void setSalesCompanyId(int salesCompanyId) {
		SalesCompanyId = salesCompanyId;
	}

	public int getIsRelation() {
		return IsRelation;
	}

	public void setIsRelation(int isRelation) {
		IsRelation = isRelation;
	}

	public int getRelationCompanyId() {
		return RelationCompanyId;
	}

	public void setRelationCompanyId(int relationCompanyId) {
		RelationCompanyId = relationCompanyId;
	}

	public int getIsModify() {
		return IsModify;
	}

	public void setIsModify(int isModify) {
		IsModify = isModify;
	}

}
