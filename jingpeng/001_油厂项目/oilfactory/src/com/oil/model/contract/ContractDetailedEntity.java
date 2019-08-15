package com.oil.model.contract;

public class ContractDetailedEntity {
	private int Id;
	private int ContractId; // 合同id
	private int MaterielId; // 物料id
	private int MaterielNameId; // 物料名称id
	private String MaterielName; // 物料名称
	private String MaterielModel; // 规格型号
	private Double UnitPrice; // 单价
	private Double SaleNumber; // 数量
	private Double Money; // 金额
	private String TaxRate; // 税率
	private int IsModifyPrice; //是否调价
	private String Remarks; // 备注
	private String Creater;
	private String Reviser;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getContractId() {
		return ContractId;
	}

	public void setContractId(int contractId) {
		ContractId = contractId;
	}

	public int getMaterielId() {
		return MaterielId;
	}

	public void setMaterielId(int materielId) {
		MaterielId = materielId;
	}

	public int getMaterielNameId() {
		return MaterielNameId;
	}

	public void setMaterielNameId(int materielNameId) {
		MaterielNameId = materielNameId;
	}

	public String getMaterielName() {
		return MaterielName;
	}

	public void setMaterielName(String materielName) {
		MaterielName = materielName;
	}

	public String getMaterielModel() {
		return MaterielModel;
	}

	public void setMaterielModel(String materielModel) {
		MaterielModel = materielModel;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Double getSaleNumber() {
		return SaleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		SaleNumber = saleNumber;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public String getTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(String taxRate) {
		TaxRate = taxRate;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
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

	public int getIsModifyPrice() {
		return IsModifyPrice;
	}

	public void setIsModifyPrice(int isModifyPrice) {
		IsModifyPrice = isModifyPrice;
	}
	
}
