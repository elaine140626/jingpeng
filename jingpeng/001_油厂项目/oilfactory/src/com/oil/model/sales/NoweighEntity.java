package com.oil.model.sales;

public class NoweighEntity {
	private int id; // id
	private int contractId; // 合同id
	private String contractNumber; // 合同编号
	private String orderNumber; // 订单编号
	private String orderNumbers;
	private int salesOrderId; // 销售订单id
	private String serialId; // 流水号
	private String saleNumber; // 销售数量
	private String salePrice; // 销售单价
	private String saleMoney; // 销售金额
	private String customerCode; // 客户编号
	private String customerName; // 客户名称
	private String customerAlias; // 客户别称
	private String taxRate; // 税率
	private String taxRateData;
	private String address; // 送货地址
	private String consignee; // 收货人
	private String consigneePhone; // 收货电话
	private String consigneeAddress; // 收货地址
	private int materielInfoId; // 物料id
	private int materielNameId; // 物料名称id
	private int materielModelId; // 规格型号id
	private String materielName; // 物料名称
	private String materielModel; // 规格型号
	private int transportBalance; // 运输结算情况（数据字典）0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他
	private String transportBalanceName;
	private int isCarryOff; // 是否随车带走
	private String carName; // 车牌号码
	private String deliveryMan; // 送货人
	private String deliveryManPhone; // 送货电话
	private String carOwner; // 车主
	private String carOwnerTelephone; // 车主电话
	private String remarks; // 备注
	private int isTesting; // 是否检测
	private int dispatchOutWarehouseId; // 出库单id
	private String testReportNumber; // 检测报告编号
	private String isSelfLifting; // 是否客户自提
	private int otherDelivery; // 其他发货方式（数据字典） 0：邮寄 1：其他
	private String postNumber; // 邮单编号
	private String otherRemarks; // 其他备注
	private int state; // 是否空发 0:是 1:否
	private int type; // 类型（数据字典） 0:正常 1:调拨
	private Double allotWeight;
	private int outType;
	private String proportion;
	private String materielName2;
	private String materielModel2;
	private String isExchange;
	private Double settlementWeight;

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(String saleMoney) {
		this.saleMoney = saleMoney;
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

	public String getCustomerAlias() {
		return customerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxRateData() {
		return taxRateData;
	}

	public void setTaxRateData(String taxRateData) {
		this.taxRateData = taxRateData;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public int getMaterielInfoId() {
		return materielInfoId;
	}

	public void setMaterielInfoId(int materielInfoId) {
		this.materielInfoId = materielInfoId;
	}

	public int getMaterielNameId() {
		return materielNameId;
	}

	public void setMaterielNameId(int materielNameId) {
		this.materielNameId = materielNameId;
	}

	public int getMaterielModelId() {
		return materielModelId;
	}

	public void setMaterielModelId(int materielModelId) {
		this.materielModelId = materielModelId;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getMaterielModel() {
		return materielModel;
	}

	public void setMaterielModel(String materielModel) {
		this.materielModel = materielModel;
	}

	public int getTransportBalance() {
		return transportBalance;
	}

	public void setTransportBalance(int transportBalance) {
		this.transportBalance = transportBalance;
	}

	public String getTransportBalanceName() {
		return transportBalanceName;
	}

	public void setTransportBalanceName(String transportBalanceName) {
		this.transportBalanceName = transportBalanceName;
	}

	public int getIsCarryOff() {
		return isCarryOff;
	}

	public void setIsCarryOff(int isCarryOff) {
		this.isCarryOff = isCarryOff;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public String getDeliveryManPhone() {
		return deliveryManPhone;
	}

	public void setDeliveryManPhone(String deliveryManPhone) {
		this.deliveryManPhone = deliveryManPhone;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getCarOwnerTelephone() {
		return carOwnerTelephone;
	}

	public void setCarOwnerTelephone(String carOwnerTelephone) {
		this.carOwnerTelephone = carOwnerTelephone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsTesting() {
		return isTesting;
	}

	public void setIsTesting(int isTesting) {
		this.isTesting = isTesting;
	}

	public int getDispatchOutWarehouseId() {
		return dispatchOutWarehouseId;
	}

	public void setDispatchOutWarehouseId(int dispatchOutWarehouseId) {
		this.dispatchOutWarehouseId = dispatchOutWarehouseId;
	}

	public String getTestReportNumber() {
		return testReportNumber;
	}

	public void setTestReportNumber(String testReportNumber) {
		this.testReportNumber = testReportNumber;
	}

	public String getIsSelfLifting() {
		return isSelfLifting;
	}

	public void setIsSelfLifting(String isSelfLifting) {
		this.isSelfLifting = isSelfLifting;
	}

	public int getOtherDelivery() {
		return otherDelivery;
	}

	public void setOtherDelivery(int otherDelivery) {
		this.otherDelivery = otherDelivery;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public String getOtherRemarks() {
		return otherRemarks;
	}

	public void setOtherRemarks(String otherRemarks) {
		this.otherRemarks = otherRemarks;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Double getAllotWeight() {
		return allotWeight;
	}

	public void setAllotWeight(Double allotWeight) {
		this.allotWeight = allotWeight;
	}

	public int getOutType() {
		return outType;
	}

	public void setOutType(int outType) {
		this.outType = outType;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getMaterielName2() {
		return materielName2;
	}

	public void setMaterielName2(String materielName2) {
		this.materielName2 = materielName2;
	}

	public String getMaterielModel2() {
		return materielModel2;
	}

	public void setMaterielModel2(String materielModel2) {
		this.materielModel2 = materielModel2;
	}

	public String getIsExchange() {
		return isExchange;
	}

	public void setIsExchange(String isExchange) {
		this.isExchange = isExchange;
	}

	public Double getSettlementWeight() {
		return settlementWeight;
	}

	public void setSettlementWeight(Double settlementWeight) {
		this.settlementWeight = settlementWeight;
	}

}
