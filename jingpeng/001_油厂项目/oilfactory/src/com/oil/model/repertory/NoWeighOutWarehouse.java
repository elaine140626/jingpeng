package com.oil.model.repertory;

//未称重出库单表
public class NoWeighOutWarehouse {
	private int id;// 未称重出库单id
	private String serialID; // 流水号
	private int salesOrderId;// 销售订单id
	private int orderDetailedNumber;// 销售订单明细流水号
	private Double allotWeight;// 调拨数量
	private Double saleNumber;// 销售数量
	private Double salePrice;// 销售单价
	private Double saleMoney;// 销售金额
	private String customerCode;// 客户编号
	private String client; // 客户名称
	private String customerAlias;// 客户别称
	private String taxRate;// 税率（数据字典）0:5 1:10 2:15 3:20 4:30
	private String address;// 送货地址
	private String consignee;// 收货人
	private String consigneePhone;// 收货电话
	private String consigneeAddress;// 收货地址
	private int materielInfoId;// 物料id
	private int materielNameId;// 物料名称id
	private int materielModelId;// 规格型号id
	private String transportBalance;// 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他
	private int isCarryOff;// 是否随车带走 0:是 1否
	private String carName;// 车牌号码
	private String deliverer;// 送货人
	private String deliveryPhone;// 送货电话
	private String carOwner;// 车主
	private String carOwnerTelephone;// 车主电话
	private String customerCarName;// 客户车辆信息
	private String remarks;// 备注
	private int isTesting;// 是否检测 0：是 1：否
	private int dispatchOutWarehouseId;// 调度出库单id
	private int isSelfLifting;// 是否客户自提 0：是 1：否
	private String otherDelivery;// 其他发货方式（数据字典） 0：邮寄 1：其他
	private String postNumber;// 邮单编号
	private String otherRemarks;// 其他备注
	private int state;// 是否空发 0:是 1:否
	private String type;// 类型（数据字典） 0:正常 1:调拨
	private int validFlag; // 有效标识
	private int isDel;// 删除标记
	private String creater;// 创建人
	private String createrDate;// 创建日期
	private String reviser;// 修改人
	private String reviserDate;// 修改日期
	private String contractNumber;// 合同编号
	private String orderNumber;// 销售订单编号
	private String customerName;// 客户名称
	private String materielName;// 物料名称
	private String materielModel;// 规格型号
	private String contractId;// 合同id
	private int isQualified;// 出厂检测报告：是否全部合格 0 : 合格 1： 不合格
	private String factoryTime;// 出厂时间
	private int materielId;// 物料id
	private Integer isExamine;
	private String transports;
	private String expectedDeliveryDate;// 预计发货时间
	private String userName;// 销售员
	private String orderDetailedId;// 销售订单明细ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialID() {
		return serialID;
	}

	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public int getOrderDetailedNumber() {
		return orderDetailedNumber;
	}

	public void setOrderDetailedNumber(int orderDetailedNumber) {
		this.orderDetailedNumber = orderDetailedNumber;
	}

	public Double getAllotWeight() {
		return allotWeight;
	}

	public void setAllotWeight(Double allotWeight) {
		this.allotWeight = allotWeight;
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

	public Double getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

	public String getTransportBalance() {
		return transportBalance;
	}

	public void setTransportBalance(String transportBalance) {
		this.transportBalance = transportBalance;
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

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
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

	public String getCustomerCarName() {
		return customerCarName;
	}

	public void setCustomerCarName(String customerCarName) {
		this.customerCarName = customerCarName;
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

	public int getIsSelfLifting() {
		return isSelfLifting;
	}

	public void setIsSelfLifting(int isSelfLifting) {
		this.isSelfLifting = isSelfLifting;
	}

	public String getOtherDelivery() {
		return otherDelivery;
	}

	public void setOtherDelivery(String otherDelivery) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public int getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(int isQualified) {
		this.isQualified = isQualified;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

	public int getMaterielId() {
		return materielId;
	}

	public void setMaterielId(int materielId) {
		this.materielId = materielId;
	}

	public Integer getIsExamine() {
		return isExamine;
	}

	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}

	public String getTransports() {
		return transports;
	}

	public void setTransports(String transports) {
		this.transports = transports;
	}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderDetailedId() {
		return orderDetailedId;
	}

	public void setOrderDetailedId(String orderDetailedId) {
		this.orderDetailedId = orderDetailedId;
	}

}
