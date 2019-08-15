package com.oil.model.system;

public class Purchasecontract {
	private int id;
	// 单据编号
	private String billNumber;
	// 日期
	private String date;
	// 采购申请单编号
	private String purchaseRequisitionId;
	// 供应商id
	private int supplierId;
	// 合同状态（数据字典） 0:执行中 1:未执行 2:完成 3:作废 4:未鉴定 5:其他
	private String contractState;
	// 合同附件路径
	private String contractRoute;
	// 运费结算情况（数据字典）0:我方 1:对方 2:我方垫付
	private String freightBalance;
	// 预计运费金额
	private Double expectMoney;
	// 创建人
	private String creater;
	// 创建日期
	private String createrDate;
	// 修改人
	private String reviser;
	// 修改日期
	private String reviserDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPurchaseRequisitionId() {
		return purchaseRequisitionId;
	}
	public void setPurchaseRequisitionId(String purchaseRequisitionId) {
		this.purchaseRequisitionId = purchaseRequisitionId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getContractState() {
		return contractState;
	}
	public void setContractState(String contractState) {
		this.contractState = contractState;
	}
	public String getContractRoute() {
		return contractRoute;
	}
	public void setContractRoute(String contractRoute) {
		this.contractRoute = contractRoute;
	}
	public String getFreightBalance() {
		return freightBalance;
	}
	public void setFreightBalance(String freightBalance) {
		this.freightBalance = freightBalance;
	}
	public Double getExpectMoney() {
		return expectMoney;
	}
	public void setExpectMoney(Double expectMoney) {
		this.expectMoney = expectMoney;
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
