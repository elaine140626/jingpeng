package com.oil.model.dispath;

public class OutboundEntity {
	private int id;                      // 出库单id  
	private String SerialId;             // 流水号
	private String Serial_ID;            // 流水号
	private String SalePrice;			 // 修改前价格
	private String UpdatePrice;          // 修改后价格
	private int salesOrderId;            // 销售订单id                                
	private String orderNumber;          // 销售订单编号
	private String orderNumbers;
	private int contractId;              // 合同id                                  
	private String contractNumber;       // 合同编号                                  
	private String name;                 // 销售员                                   
	private String client;               // 客户名称                                  
	private String customerAlias;        // 客户别称         
	private String customerCode;         // 客户编号
	private String plateNumber;          // 车牌号码                                  
	private String carOwner;             // 车主                                    
	private String carOwnerTelephone;    // 车主电话                                  
	private int productID;               // 物料id                                  
	private int materielNameId;          // 物料名称id                                
	private int materielModelId;         // 规格型号id                                
	private String materielName;         // 物料名称                                  
	private String materielModel;        // 规格型号                                  
	private String address;              // 送货地址                                  
	private String deliveryMan;          // 送货人                                   
	private String deliveryManPhone;     // 送货电话                                  
	private String consignee;            // 收货人                                   
	private String consigneePhone;       // 收货人电话   
	private String consigneeAddress;     // 收货地址
	private int testReportId;            // 检测报告id                                
	private String testReportNumber;     // 检测报告编号                                
	private String expectedDeliveryDate; // 预计发货时间  
	private String factoryTime;          // 出厂时间
	private String facingSlipNum;        // 出厂封签号
	private int outType;                 // 出库单状态 0：正常 1：整车调拨  2：半车调拨 3：退货     
	private String outTypeName;          // 出库单状态名称
	private int isTesting;               // 是否检测
	private Double amount;               // 结算数量
	private Double goodsPrice;           // 货物单价
	private Double goodsCost;            // 货物成本
	private String taxRate;              // 税率
	private int purchaseContractId;      // 采购合同id
	private String lbsMaterialName;      // 磅单打印物料名称                              
	private String lbsOutputName;        // 磅单输出名称                                
	private String CartWeighType;        // 是否大车称重                                
	private String remarks;              // 备注                                    
	private String saleCompanyName;      // 销售公司名称                                
	private int outTypeMark;             // 出库单类型标识 0：出库单 1：未入厂出库单                
	private int validFlag;               // 有效标识 0无效 1有效 (作废标识) 
	private Double suttle;               // 净重
	private Double allotWeight;          // 调拨重量
	private String creater;              // 创建人
	private String reviser;              // 修改人
	private int priority;                // 优先级
	private Double saleNumber;           // 销售数量
	private Double salePrice;            // 销售单价
	private Double saleMoney;            // 销售金额
	private String cargoBalance;         // 货物结算情况
	private String transportBalance;     // 运输结算情况
	private String diaoBoClient;     	 // 运输结算情况
	private String diaoBoCustomerAlias;  // 运输结算情况
	private String diaoBoRemarks;     	 // 运输结算情况
	private Double tareWeight;           // 皮重  
	private Double grossWeight;          // 毛重  
	private Double freightMoney;         // 运输金额
	private String cancellationCause;    // 运输金额
	private String isEmpty;         	 // 是否关联空发出库单
	private String emptyList;         	 // 关联空发出库单号
	private String orderId;         	 // 销售订单Id
	private String noWeightId;         	 // 关联未称重出库单id
	private String diaoOrderNumber;      // 销售订单Id
	private String diaoCarOwner;         // 车主
	private String diaoCarOwnerTelephone;// 车主电话
	private String diaoOrderDetailedNumber;// 销售订单明细流水号
	private String diaoDeliverer;		 // 送货人
	private String diaoDeliveryPhone;	 // 送货电话
	private String diaoPlateNumber;	 	 // 车牌号码
	private String diaoConsignee;		 // 收货人
	private String diaoConsigneePhone;	 // 收货电话
	private String diaoAddress;	 	 	 // 收货地址
	private int orderDetailedId;	 	 // 收货地址
	private String transports;           //止运地
	private String distance;
	private String listModel;
	private String transportBalances;
	private String proportion;			// 兑换比例
	private String materielName2;		// 兑换前物料名称
	private String materielModel2;		// 兑换前规格型号
	private Integer isExamine;
	private String StartAddress;        // 起运地
	private String orderDetailedNumber; // 销售订单明细流水号
	public void setSalePrice(String salePrice) {
		SalePrice = salePrice;
	}
	public String getUpdatePrice() {
		return UpdatePrice;
	}
	public void setUpdatePrice(String updatePrice) {
		UpdatePrice = updatePrice;
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
	public Double getFreightMoney() {
		return freightMoney;
	}
	public void setFreightMoney(Double freightMoney) {
		this.freightMoney = freightMoney;
	}
	public String getCargoBalance() {
		return cargoBalance;
	}
	public void setCargoBalance(String cargoBalance) {
		this.cargoBalance = cargoBalance;
	}
	public String getTransportBalance() {
		return transportBalance;
	}
	public void setTransportBalance(String transportBalance) {
		this.transportBalance = transportBalance;
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
	public String getFacingSlipNum() {
		return facingSlipNum;
	}
	public void setFacingSlipNum(String facingSlipNum) {
		this.facingSlipNum = facingSlipNum;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getGoodsCost() {
		return goodsCost;
	}
	public void setGoodsCost(Double goodsCost) {
		this.goodsCost = goodsCost;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getSerialId() {
		return SerialId;
	}
	public void setSerialId(String serialId) {
		SerialId = serialId;
	}
	public int getPurchaseContractId() {
		return purchaseContractId;
	}
	public void setPurchaseContractId(int purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public Double getSuttle() {
		return suttle;
	}
	public void setSuttle(Double suttle) {
		this.suttle = suttle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getTestReportId() {
		return testReportId;
	}
	public void setTestReportId(int testReportId) {
		this.testReportId = testReportId;
	}
	public String getTestReportNumber() {
		return testReportNumber;
	}
	public void setTestReportNumber(String testReportNumber) {
		this.testReportNumber = testReportNumber;
	}
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public int getOutType() {
		return outType;
	}
	public void setOutType(int outType) {
		this.outType = outType;
	}
	public String getOutTypeName() {
		return outTypeName;
	}
	public void setOutTypeName(String outTypeName) {
		this.outTypeName = outTypeName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getLbsMaterialName() {
		return lbsMaterialName;
	}
	public void setLbsMaterialName(String lbsMaterialName) {
		this.lbsMaterialName = lbsMaterialName;
	}
	public String getLbsOutputName() {
		return lbsOutputName;
	}
	public void setLbsOutputName(String lbsOutputName) {
		this.lbsOutputName = lbsOutputName;
	}
	
	public String getCartWeighType() {
		return CartWeighType;
	}
	public void setCartWeighType(String cartWeighType) {
		CartWeighType = cartWeighType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSaleCompanyName() {
		return saleCompanyName;
	}
	public void setSaleCompanyName(String saleCompanyName) {
		this.saleCompanyName = saleCompanyName;
	}
	public int getOutTypeMark() {
		return outTypeMark;
	}
	public void setOutTypeMark(int outTypeMark) {
		this.outTypeMark = outTypeMark;
	}
	public String getFactoryTime() {
		return factoryTime;
	}
	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public int getIsTesting() {
		return isTesting;
	}
	public void setIsTesting(int isTesting) {
		this.isTesting = isTesting;
	}
	public String getCancellationCause() {
		return cancellationCause;
	}
	public void setCancellationCause(String cancellationCause) {
		this.cancellationCause = cancellationCause;
	}
	public String getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getEmptyList() {
		return emptyList;
	}
	public void setEmptyList(String emptyList) {
		this.emptyList = emptyList;
	}
	public Double getAllotWeight() {
		return allotWeight;
	}
	public void setAllotWeight(Double allotWeight) {
		this.allotWeight = allotWeight;
	}
	public String getDiaoBoClient() {
		return diaoBoClient;
	}
	public void setDiaoBoClient(String diaoBoClient) {
		this.diaoBoClient = diaoBoClient;
	}
	public String getDiaoBoCustomerAlias() {
		return diaoBoCustomerAlias;
	}
	public void setDiaoBoCustomerAlias(String diaoBoCustomerAlias) {
		this.diaoBoCustomerAlias = diaoBoCustomerAlias;
	}
	public String getDiaoBoRemarks() {
		return diaoBoRemarks;
	}
	public void setDiaoBoRemarks(String diaoBoRemarks) {
		this.diaoBoRemarks = diaoBoRemarks;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getNoWeightId() {
		return noWeightId;
	}
	public void setNoWeightId(String noWeightId) {
		this.noWeightId = noWeightId;
	}
	public String getSerial_ID() {
		return Serial_ID;
	}
	public void setSerial_ID(String serial_ID) {
		Serial_ID = serial_ID;
	}
	public String getDiaoOrderNumber() {
		return diaoOrderNumber;
	}
	public void setDiaoOrderNumber(String diaoOrderNumber) {
		this.diaoOrderNumber = diaoOrderNumber;
	}
	public String getDiaoCarOwner() {
		return diaoCarOwner;
	}
	public void setDiaoCarOwner(String diaoCarOwner) {
		this.diaoCarOwner = diaoCarOwner;
	}
	public String getDiaoCarOwnerTelephone() {
		return diaoCarOwnerTelephone;
	}
	public void setDiaoCarOwnerTelephone(String diaoCarOwnerTelephone) {
		this.diaoCarOwnerTelephone = diaoCarOwnerTelephone;
	}
	public String getDiaoDeliverer() {
		return diaoDeliverer;
	}
	public void setDiaoDeliverer(String diaoDeliverer) {
		this.diaoDeliverer = diaoDeliverer;
	}
	public String getDiaoDeliveryPhone() {
		return diaoDeliveryPhone;
	}
	public void setDiaoDeliveryPhone(String diaoDeliveryPhone) {
		this.diaoDeliveryPhone = diaoDeliveryPhone;
	}
	public String getDiaoPlateNumber() {
		return diaoPlateNumber;
	}
	public void setDiaoPlateNumber(String diaoPlateNumber) {
		this.diaoPlateNumber = diaoPlateNumber;
	}
	public String getDiaoConsignee() {
		return diaoConsignee;
	}
	public void setDiaoConsignee(String diaoConsignee) {
		this.diaoConsignee = diaoConsignee;
	}
	public String getDiaoConsigneePhone() {
		return diaoConsigneePhone;
	}
	public void setDiaoConsigneePhone(String diaoConsigneePhone) {
		this.diaoConsigneePhone = diaoConsigneePhone;
	}
	public String getDiaoAddress() {
		return diaoAddress;
	}
	public void setDiaoAddress(String diaoAddress) {
		this.diaoAddress = diaoAddress;
	}
	public String getOrderNumbers() {
		return orderNumbers;
	}
	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
	public int getOrderDetailedId() {
		return orderDetailedId;
	}
	public void setOrderDetailedId(int orderDetailedId) {
		this.orderDetailedId = orderDetailedId;
	}
	public String getDiaoOrderDetailedNumber() {
		return diaoOrderDetailedNumber;
	}
	public void setDiaoOrderDetailedNumber(String diaoOrderDetailedNumber) {
		this.diaoOrderDetailedNumber = diaoOrderDetailedNumber;
	}
	public String getTransports() {
		return transports;
	}
	public void setTransports(String transports) {
		this.transports = transports;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getListModel() {
		return listModel;
	}
	public void setListModel(String listModel) {
		this.listModel = listModel;
	}
	public String getTransportBalances() {
		return transportBalances;
	}
	public void setTransportBalances(String transportBalances) {
		this.transportBalances = transportBalances;
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
	public Integer getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getStartAddress() {
		return StartAddress;
	}
	public void setStartAddress(String startAddress) {
		StartAddress = startAddress;
	}
	public String getOrderDetailedNumber() {
		return orderDetailedNumber;
	}
	public void setOrderDetailedNumber(String orderDetailedNumber) {
		this.orderDetailedNumber = orderDetailedNumber;
	}
	
}
