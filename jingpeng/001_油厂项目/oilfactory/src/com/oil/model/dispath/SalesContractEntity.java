package com.oil.model.dispath;

public class SalesContractEntity {
	private int id;                        //  入库单id 
	private int contractId;              //  销售合同id
	private String contractNumber;         //  合同编号  
	private String customerName;           //  客户名称  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
}
