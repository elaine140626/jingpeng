package com.oil.model.contract;

public class ContractIncomingDetailedEntity {
	private int Id;
	private int ContractId; // 合同id
	private int MaterielId; // 授手加工物资名称id
	private int MaterielNameId; // 物料名称id
	private String MaterielName; // 授手加工物资名称
	private String MaterielModel; // 规格型号
	private String SettlementMethod; // 结算方式（数据字典） 0:1T抵1T 1:其他
	private String SettlementMethodName;
	private String SettlementMethodRemarks; // 结算方式备注
	private String CargoBalance; // 货物结算情况（数据字典）
	private String CargoBalanceMethodName;
	private String CargoRemarks; // 货物结算情况备注
	private String TransportBalance; // 运输结算情况（数据字典）
	private String TransportBalanceName;
	private String TransportRemarks; // 运输结算情况备注
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

	public String getSettlementMethod() {
		return SettlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		SettlementMethod = settlementMethod;
	}

	public String getSettlementMethodName() {
		return SettlementMethodName;
	}

	public void setSettlementMethodName(String settlementMethodName) {
		SettlementMethodName = settlementMethodName;
	}

	public String getSettlementMethodRemarks() {
		return SettlementMethodRemarks;
	}

	public void setSettlementMethodRemarks(String settlementMethodRemarks) {
		SettlementMethodRemarks = settlementMethodRemarks;
	}

	public String getCargoBalance() {
		return CargoBalance;
	}

	public void setCargoBalance(String cargoBalance) {
		CargoBalance = cargoBalance;
	}

	public String getCargoBalanceMethodName() {
		return CargoBalanceMethodName;
	}

	public void setCargoBalanceMethodName(String cargoBalanceMethodName) {
		CargoBalanceMethodName = cargoBalanceMethodName;
	}

	public String getCargoRemarks() {
		return CargoRemarks;
	}

	public void setCargoRemarks(String cargoRemarks) {
		CargoRemarks = cargoRemarks;
	}

	public String getTransportBalance() {
		return TransportBalance;
	}

	public void setTransportBalance(String transportBalance) {
		TransportBalance = transportBalance;
	}

	public String getTransportBalanceName() {
		return TransportBalanceName;
	}

	public void setTransportBalanceName(String transportBalanceName) {
		TransportBalanceName = transportBalanceName;
	}

	public String getTransportRemarks() {
		return TransportRemarks;
	}

	public void setTransportRemarks(String transportRemarks) {
		TransportRemarks = transportRemarks;
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

}
