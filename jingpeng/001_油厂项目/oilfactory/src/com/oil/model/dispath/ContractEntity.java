package com.oil.model.dispath;

public class ContractEntity {
	private int id;                       // 合同id  
	private String contractNumber;        // 合同编号  
	private String contractName;          // 合同名称  
	private int customerId;               // 客户id
	private String customerName;          // 客户名称
	private Double saleNumber;            // 销售数量  
	private String contractState;         // 合同状态  
	private int userInfoId;               // 销售员id 
	private String salesCompanyName;      // 销售公司名称
	private String contractDate;          // 合同日期  
	private String remarks;               // 备注    
	private String contractRoute;         // 合同路径  
	private String isIncoming;               // 是否来料加工
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Double saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getContractState() {
		return contractState;
	}
	public void setContractState(String contractState) {
		this.contractState = contractState;
	}
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getSalesCompanyName() {
		return salesCompanyName;
	}
	public void setSalesCompanyName(String salesCompanyName) {
		this.salesCompanyName = salesCompanyName;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContractRoute() {
		return contractRoute;
	}
	public void setContractRoute(String contractRoute) {
		this.contractRoute = contractRoute;
	}
	public String getIsIncoming() {
		return isIncoming;
	}
	public void setIsIncoming(String isIncoming) {
		this.isIncoming = isIncoming;
	}
}
