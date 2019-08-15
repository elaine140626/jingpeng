package com.oil.model.dispath;

public class OrderNumberEntity {
	private int id;                        // id                                               
	private String orderNumbers;           // 订单编号                                             
	private int contractId;                // 合同id                                             
	private String contractNumber;         // 合同编号                                             
	private int customerId;                // 客户id 
	private String customerCode;           // 客户编号
	private String customerName;           // 客户名称
	private String customer;               // 客户别称                                             
	private String address;                // 送货地址                                             
	private String contacts;               // 联系人                                              
	private String telephone;              // 联系电话                                             
	private int transportBalance;          // 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他       
	private String orderState;             // 订单状态                                             
	private String salesCompanyName;       // 销售公司名称   
	private String isActualDelivery;       // 是否实际发货
	private String expectedDeliveryDate;   // 预计发货时间                                           
	private int userInfoId;                // 销售员id                                            
	private String remarks;                // 备注    
	private Double unitPrice;              // 单价    
	private Double saleNumber;             // 销售数量    
	private Double salePrice;              // 销售单价    
	private String taxRate;                // 税率
	private Double saleMoney;              // 销售金额
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNumbers() {
		return orderNumbers;
	}
	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	public int getTransportBalance() {
		return transportBalance;
	}
	public void setTransportBalance(int transportBalance) {
		this.transportBalance = transportBalance;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getSalesCompanyName() {
		return salesCompanyName;
	}
	public void setSalesCompanyName(String salesCompanyName) {
		this.salesCompanyName = salesCompanyName;
	}
	public String getIsActualDelivery() {
		return isActualDelivery;
	}
	public void setIsActualDelivery(String isActualDelivery) {
		this.isActualDelivery = isActualDelivery;
	}
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Double saleNumber) {
		this.saleNumber = saleNumber;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public Double getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}
}
