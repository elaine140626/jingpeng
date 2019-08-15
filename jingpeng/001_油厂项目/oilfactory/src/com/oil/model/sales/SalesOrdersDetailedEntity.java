package com.oil.model.sales;

public class SalesOrdersDetailedEntity {
	private int Id;
	private int SalesOrderId; // 销售订单id
	private String OrderDetailedNumber; // 销售订单明细流水号
	private int MaterielId; // 物料id
	private int ExchangeMaterielId; // 兑换后物料id
	private int MaterielNameId; // 物料名称id
	private String MaterielName; // 物料名称
	private String MaterielModel; // 规格型号
	private String Unit; // 单位
	private int ExchangeMaterielNameId; // 兑换物料名称id
	private String ExchangeMaterielName; // 兑换物料名称
	private String ExchangeMaterielModel; // 兑换规格型号
	private String UnitPrice; // 单价
	private String ListModel; // 磅单显示产品名称-型号
	private String TransportBalance; // 运输结算情况
	private String TransportBalanceName;
	private String ExpectedDeliveryDate; // 预计发车时间
	private int IsCancel; // 是否作废
	private String CancelPerson; // 作废人
	private String CancelDate; // 作废时间
	private Double SaleNumber; // 销售数量
	private Double SalePrice; // 销售单价
	private Double SaleMoney; // 销售金额
	private String TaxRate; // 税率
	private String Remarks; // 备注
	private String Creater;
	private String Reviser;
	private int IsExchange; // 是否兑换
	private Double Proportion;//兑换比例
	private Double ExchangeWeight;//兑换重量
	private int IsActualDelivery;//是否实际发货
	private String Transports;//运输地点
	private Double Distance;//运输距离
	private String StartAddress;
	private Integer IsLowerHair;   //下发生产计划
	private Integer IsInspector;   //生产工艺通知单
	private Integer IsExamine;	//是否审核通过
	private Integer IsCheck;	//生产任务确认
	private Integer IsProduction;
	private Integer IsApplication;	//提交质检申请
	private Integer IsAdjust;
	private Integer IsQualified;	//是否合格
	private Integer IsComplete;	//生产完成确认
	private Integer isConfirmSubmission;
	private Double TareWeight;	//皮重
	private Double GrossWeight;	//毛重
	private Double AsphaltContent; //沥青含量
	private Double AsphaltContentExchange; //兑换后沥青含量（%）
	private Double Mileage; // 里程
	
	//合同明细中查询（ygt）
	private String DetailedIsCancel;
	private String DetailedIsExchange;
	private String DetailedIsActualDelivery; 
	
	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getStartAddress() {
		return StartAddress;
	}

	public void setStartAddress(String startAddress) {
		StartAddress = startAddress;
	}

	public int getExchangeMaterielNameId() {
		return ExchangeMaterielNameId;
	}

	public void setExchangeMaterielNameId(int exchangeMaterielNameId) {
		ExchangeMaterielNameId = exchangeMaterielNameId;
	}
	
	public Integer getIsAdjust() {
		return IsAdjust;
	}

	public void setIsAdjust(Integer isAdjust) {
		IsAdjust = isAdjust;
	}

	public Integer getIsProduction() {
		return IsProduction;
	}

	public void setIsProduction(Integer isProduction) {
		IsProduction = isProduction;
	}

	public Integer getIsConfirmSubmission() {
		return isConfirmSubmission;
	}

	public void setIsConfirmSubmission(Integer isConfirmSubmission) {
		this.isConfirmSubmission = isConfirmSubmission;
	}

	public String getExchangeMaterielName() {
		return ExchangeMaterielName;
	}

	public void setExchangeMaterielName(String exchangeMaterielName) {
		ExchangeMaterielName = exchangeMaterielName;
	}

	public String getExchangeMaterielModel() {
		return ExchangeMaterielModel;
	}

	public void setExchangeMaterielModel(String exchangeMaterielModel) {
		ExchangeMaterielModel = exchangeMaterielModel;
	}

	public int getExchangeMaterielId() {
		return ExchangeMaterielId;
	}

	public void setExchangeMaterielId(int exchangeMaterielId) {
		ExchangeMaterielId = exchangeMaterielId;
	}

	public String getTransports() {
		return Transports;
	}

	public void setTransports(String transports) {
		Transports = transports;
	}

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
	}

	public Double getExchangeWeight() {
		return ExchangeWeight;
	}

	public void setExchangeWeight(Double exchangeWeight) {
		ExchangeWeight = exchangeWeight;
	}

	public int getIsActualDelivery() {
		return IsActualDelivery;
	}

	public void setIsActualDelivery(int isActualDelivery) {
		IsActualDelivery = isActualDelivery;
	}

	public Double getProportion() {
		return Proportion;
	}

	public void setProportion(Double proportion) {
		Proportion = proportion;
	}

	public int getIsExchange() {
		return IsExchange;
	}

	public void setIsExchange(int isExchange) {
		IsExchange = isExchange;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getSalesOrderId() {
		return SalesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		SalesOrderId = salesOrderId;
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

	public String getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}
	
	public String getListModel() {
		return ListModel;
	}

	public void setListModel(String listModel) {
		ListModel = listModel;
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

	public String getExpectedDeliveryDate() {
		return ExpectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		ExpectedDeliveryDate = expectedDeliveryDate;
	}

	public int getIsCancel() {
		return IsCancel;
	}

	public void setIsCancel(int isCancel) {
		IsCancel = isCancel;
	}

	public String getCancelPerson() {
		return CancelPerson;
	}

	public void setCancelPerson(String cancelPerson) {
		CancelPerson = cancelPerson;
	}

	public String getCancelDate() {
		return CancelDate;
	}

	public void setCancelDate(String cancelDate) {
		CancelDate = cancelDate;
	}

	public Double getSaleNumber() {
		return SaleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		SaleNumber = saleNumber;
	}

	public Double getSalePrice() {
		return SalePrice;
	}

	public void setSalePrice(Double salePrice) {
		SalePrice = salePrice;
	}

	public Double getSaleMoney() {
		return SaleMoney;
	}

	public void setSaleMoney(Double saleMoney) {
		SaleMoney = saleMoney;
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

	public String getOrderDetailedNumber() {
		return OrderDetailedNumber;
	}

	public void setOrderDetailedNumber(String orderDetailedNumber) {
		OrderDetailedNumber = orderDetailedNumber;
	}

	public Integer getIsLowerHair() {
		return IsLowerHair;
	}

	public void setIsLowerHair(Integer isLowerHair) {
		IsLowerHair = isLowerHair;
	}

	public Integer getIsInspector() {
		return IsInspector;
	}

	public void setIsInspector(Integer isInspector) {
		IsInspector = isInspector;
	}

	public Integer getIsExamine() {
		return IsExamine;
	}

	public void setIsExamine(Integer isExamine) {
		IsExamine = isExamine;
	}

	public Integer getIsCheck() {
		return IsCheck;
	}

	public void setIsCheck(Integer isCheck) {
		IsCheck = isCheck;
	}

	public Integer getIsApplication() {
		return IsApplication;
	}

	public void setIsApplication(Integer isApplication) {
		IsApplication = isApplication;
	}

	public Integer getIsQualified() {
		return IsQualified;
	}

	public void setIsQualified(Integer isQualified) {
		IsQualified = isQualified;
	}

	public Integer getIsComplete() {
		return IsComplete;
	}

	public void setIsComplete(Integer isComplete) {
		IsComplete = isComplete;
	}

	public Double getTareWeight() {
		return TareWeight;
	}

	public void setTareWeight(Double tareWeight) {
		TareWeight = tareWeight;
	}

	public Double getGrossWeight() {
		return GrossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		GrossWeight = grossWeight;
	}

	public Double getAsphaltContent() {
		return AsphaltContent;
	}

	public void setAsphaltContent(Double asphaltContent) {
		AsphaltContent = asphaltContent;
	}
	
	public Double getAsphaltContentExchange() {
		return AsphaltContentExchange;
	}

	public void setAsphaltContentExchange(Double asphaltContentExchange) {
		AsphaltContentExchange = asphaltContentExchange;
	}

	public Double getMileage() {
		return Mileage;
	}

	public void setMileage(Double mileage) {
		Mileage = mileage;
	}

	public String getDetailedIsCancel() {
		return DetailedIsCancel;
	}

	public void setDetailedIsCancel(String detailedIsCancel) {
		DetailedIsCancel = detailedIsCancel;
	}

	public String getDetailedIsExchange() {
		return DetailedIsExchange;
	}

	public void setDetailedIsExchange(String detailedIsExchange) {
		DetailedIsExchange = detailedIsExchange;
	}

	public String getDetailedIsActualDelivery() {
		return DetailedIsActualDelivery;
	}

	public void setDetailedIsActualDelivery(String detailedIsActualDelivery) {
		DetailedIsActualDelivery = detailedIsActualDelivery;
	}
	
}
