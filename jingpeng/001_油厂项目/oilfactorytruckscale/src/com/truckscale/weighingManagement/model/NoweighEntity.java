package com.truckscale.weighingManagement.model;

public class NoweighEntity {
	private int id; // id
	private int contractId; // 合同id
	private int salesOrderId; // 销售订单id
	private String serialId; // 流水号
	private String customerCode; // 客户编号
	private String customerName; // 客户名称
	private String consignee; // 收货人
	private String consigneePhone; // 收货电话
	private int materielInfoId; // 物料id
	private int materielNameId; // 物料名称id
	private int materielModelId; // 规格型号id
	private String materielName; // 物料名称
	private String materielModel; // 规格型号
	private String transportBalance; // 运输结算情况（数据字典）0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他
	private String carName; // 车牌号码
	private String deliveryMan; // 送货人
	private String deliveryManPhone; // 送货电话
	private String remarks; // 备注
	private int dispatchOutWarehouseId; // 出库单id
	private String testReportNumber; // 检测报告编号
	private int type; // 类型（数据字典） 0:正常 1:调拨
	private Double allotWeight;//调拨重量
	private String outType;//出库单状态
	private Double settlementWeight;//结算重量
	private String companyName;//销售公司
	private String otherCompanyName;//其它公司
	private Double tareWeight;//皮重（t）
	private Double grossWeight;//毛重（t）
	private Double suttle;//净重（t）
	private String facingSlipNum;//封签号
	private String facingSlipNum2;//封签号2
	private String facingSlipNum3;//封签号3
	private String facingSlipNum4;//封签号4
	private Double temperature;//温度
	private String tareMeasureTime;//皮重称重时间
	private String fleetName;//车队名称
    private String driverAutograph;//图片名称
    private String tareOperator;//皮重司磅员（检斤员）
    private String orderDetailedRemarks;//销售订单明细备注
	
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
	public String getTransportBalance() {
		return transportBalance;
	}
	public void setTransportBalance(String transportBalance) {
		this.transportBalance = transportBalance;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Double getSettlementWeight() {
		return settlementWeight;
	}
	public void setSettlementWeight(Double settlementWeight) {
		this.settlementWeight = settlementWeight;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOtherCompanyName() {
		return otherCompanyName;
	}
	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
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
	public String getFacingSlipNum() {
		return facingSlipNum;
	}
	public void setFacingSlipNum(String facingSlipNum) {
		this.facingSlipNum = facingSlipNum;
	}
	public String getFacingSlipNum2() {
		return facingSlipNum2;
	}
	public void setFacingSlipNum2(String facingSlipNum2) {
		this.facingSlipNum2 = facingSlipNum2;
	}
	public String getFacingSlipNum3() {
		return facingSlipNum3;
	}
	public void setFacingSlipNum3(String facingSlipNum3) {
		this.facingSlipNum3 = facingSlipNum3;
	}
	public String getFacingSlipNum4() {
		return facingSlipNum4;
	}
	public void setFacingSlipNum4(String facingSlipNum4) {
		this.facingSlipNum4 = facingSlipNum4;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public String getTareMeasureTime() {
		return tareMeasureTime;
	}
	public void setTareMeasureTime(String tareMeasureTime) {
		this.tareMeasureTime = tareMeasureTime;
	}
	public String getFleetName() {
		return fleetName;
	}
	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}
	public String getDriverAutograph() {
		return driverAutograph;
	}
	public void setDriverAutograph(String driverAutograph) {
		this.driverAutograph = driverAutograph;
	}
	public String getTareOperator() {
		return tareOperator;
	}
	public void setTareOperator(String tareOperator) {
		this.tareOperator = tareOperator;
	}
	public String getOrderDetailedRemarks() {
		return orderDetailedRemarks;
	}
	public void setOrderDetailedRemarks(String orderDetailedRemarks) {
		this.orderDetailedRemarks = orderDetailedRemarks;
	}
	
}
