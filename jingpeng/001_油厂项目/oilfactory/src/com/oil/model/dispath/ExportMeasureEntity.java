package com.oil.model.dispath;

public class ExportMeasureEntity {
	private Integer id;
	private String SerialId; // 出库单编号
	private String plateNumber; // 车牌号码
	private Integer productId; // 物料id
	private String materielName; // 物料名称
	private String materielModel; // 规格型号
	private String lbsMaterialName; // 磅单打印物料名称
	private String materielName2; // 物料名称(兑换前)
	private String materielModel2; // 规格型号(兑换前)
	private Double proportion; // 兑换比例
	private String customerCode; // 客户编号
	private String customerName; // 客户名称
	private String customerAlias; // 客户别称
	private String contractNumber; // 合同编号
	private Integer salesOrderId; // 销售订单id
	private String orderNumber; // 销售订单编号
	private Integer orderDetailedId; // 销售订单明细id
	private String orderDetailedNumber; // 销售订单明细编号
	private Double saleNumber; // 销售数量
	private String outType; // 出库单状态 0：正常 1：调拨 2：退货 3:空发 4:兑换 5.兑换时调拨
	private String outTypeName; // 出库单状态名称
	private Integer exmIsExamine; // 是否审核合格 0:是 1:否
	private String salesName; // 销售员
	private String deliveryMan; // 送货人(司机)
	private String deliveryManPhone; // 送货电话(联系电话)
	private String address; // 送货地址
	private String consignee; // 收货人(联系人)
	private String consigneePhone; // 收货人电话(电话)
	private String consigneeAddress; // 收货地址
	private String testReportNumber; // 检测报告编号
	private String factoryTime; // 出厂时间
	private String expectedDeliveryDate; // 预计发货时间
	private Double tareWeight; // 皮重
	private Double grossWeight; // 毛重
	private Double suttle; // 净重
	private Integer isTesting; // 是否检测
	private String emptyCarId; // 关联空发出库单编号
	private String cancellationCause; // 作废原因
	private String cartWeighType; // 车辆称重类型（数据字典） 0:普通 1:大车 2:超大车
	private String transports; // 止运地
	private Double distance; // 运距（千米）
	private String saleCompanyName; // 销售公司名称
	private String transportBalances; // 结算情况
	private String transportBalancesName; // 结算情况
	private String startAddress; // 起运地
	private String outWarehouseId; // 空发关联出库单编号
	private String remarks; // 备注
	private String salRemarks; // 销售备注
	private Integer validFlag; // 有效标识
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	private Integer isLowerHair; // 是否下发生产计划
	private Integer isInspector; // 质检员审核工艺通知单
	private Integer isExamine; // 主任审核工艺通知单
	private Integer isCheck; // 生产任务核对
	private Integer isProduction; // 确认生产
	private Integer isApplication; // 提交质检申请
	private Integer isQualified; // 质检是否合格
	private Integer isAdjust; // 是否调整
	private Integer isComplete; // 是否生产完成
	private Integer isConfirmSubmission; // 是否确认实际消耗
	private Double loadTon; // 荷载吨数
	private Double receiptWeight; // 回执重量

	public String getSalRemarks() {
		return salRemarks;
	}

	public void setSalRemarks(String salRemarks) {
		this.salRemarks = salRemarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialId() {
		return SerialId;
	}

	public void setSerialId(String serialId) {
		SerialId = serialId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getLbsMaterialName() {
		return lbsMaterialName;
	}

	public void setLbsMaterialName(String lbsMaterialName) {
		this.lbsMaterialName = lbsMaterialName;
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

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderDetailedId() {
		return orderDetailedId;
	}

	public void setOrderDetailedId(Integer orderDetailedId) {
		this.orderDetailedId = orderDetailedId;
	}

	public String getOrderDetailedNumber() {
		return orderDetailedNumber;
	}

	public void setOrderDetailedNumber(String orderDetailedNumber) {
		this.orderDetailedNumber = orderDetailedNumber;
	}

	public Double getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public String getOutTypeName() {
		return outTypeName;
	}

	public void setOutTypeName(String outTypeName) {
		this.outTypeName = outTypeName;
	}

	public Integer getExmIsExamine() {
		return exmIsExamine;
	}

	public void setExmIsExamine(Integer exmIsExamine) {
		this.exmIsExamine = exmIsExamine;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
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

	public String getTestReportNumber() {
		return testReportNumber;
	}

	public void setTestReportNumber(String testReportNumber) {
		this.testReportNumber = testReportNumber;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public Double getTareWeight() {
		return tareWeight;
	}

	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getSuttle() {
		return suttle;
	}

	public void setSuttle(Double suttle) {
		this.suttle = suttle;
	}

	public Integer getIsTesting() {
		return isTesting;
	}

	public void setIsTesting(Integer isTesting) {
		this.isTesting = isTesting;
	}

	public String getEmptyCarId() {
		return emptyCarId;
	}

	public void setEmptyCarId(String emptyCarId) {
		this.emptyCarId = emptyCarId;
	}

	public String getCancellationCause() {
		return cancellationCause;
	}

	public void setCancellationCause(String cancellationCause) {
		this.cancellationCause = cancellationCause;
	}

	public String getCartWeighType() {
		return cartWeighType;
	}

	public void setCartWeighType(String cartWeighType) {
		this.cartWeighType = cartWeighType;
	}

	public String getTransports() {
		return transports;
	}

	public void setTransports(String transports) {
		this.transports = transports;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getSaleCompanyName() {
		return saleCompanyName;
	}

	public void setSaleCompanyName(String saleCompanyName) {
		this.saleCompanyName = saleCompanyName;
	}

	public String getTransportBalances() {
		return transportBalances;
	}

	public void setTransportBalances(String transportBalances) {
		this.transportBalances = transportBalances;
	}

	public String getTransportBalancesName() {
		return transportBalancesName;
	}

	public void setTransportBalancesName(String transportBalancesName) {
		this.transportBalancesName = transportBalancesName;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getOutWarehouseId() {
		return outWarehouseId;
	}

	public void setOutWarehouseId(String outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public String getCreater() {
		return Creater;
	}

	public void setCreater(String creater) {
		Creater = creater;
	}

	public String getCreaterDate() {
		return CreaterDate;
	}

	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}

	public String getReviser() {
		return Reviser;
	}

	public void setReviser(String reviser) {
		Reviser = reviser;
	}

	public String getReviserDate() {
		return ReviserDate;
	}

	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}

	public Integer getIsLowerHair() {
		return isLowerHair;
	}

	public void setIsLowerHair(Integer isLowerHair) {
		this.isLowerHair = isLowerHair;
	}

	public Integer getIsInspector() {
		return isInspector;
	}

	public void setIsInspector(Integer isInspector) {
		this.isInspector = isInspector;
	}

	public Integer getIsExamine() {
		return isExamine;
	}

	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getIsProduction() {
		return isProduction;
	}

	public void setIsProduction(Integer isProduction) {
		this.isProduction = isProduction;
	}

	public Integer getIsApplication() {
		return isApplication;
	}

	public void setIsApplication(Integer isApplication) {
		this.isApplication = isApplication;
	}

	public Integer getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public Integer getIsAdjust() {
		return isAdjust;
	}

	public void setIsAdjust(Integer isAdjust) {
		this.isAdjust = isAdjust;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	public Integer getIsConfirmSubmission() {
		return isConfirmSubmission;
	}

	public void setIsConfirmSubmission(Integer isConfirmSubmission) {
		this.isConfirmSubmission = isConfirmSubmission;
	}

	public Double getLoadTon() {
		return loadTon;
	}

	public void setLoadTon(Double loadTon) {
		this.loadTon = loadTon;
	}

	public Double getReceiptWeight() {
		return receiptWeight;
	}

	public void setReceiptWeight(Double receiptWeight) {
		this.receiptWeight = receiptWeight;
	}

}
