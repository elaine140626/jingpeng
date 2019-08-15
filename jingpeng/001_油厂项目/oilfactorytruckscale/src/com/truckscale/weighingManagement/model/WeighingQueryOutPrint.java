package com.truckscale.weighingManagement.model;

public class WeighingQueryOutPrint {

	private int id;//自增长id
	private String serialId;//流水号
	private String plateNumber;//车牌号码
	private String tareMeasureTime;//皮重称重时间
	private String weighingUnit;//称重单位
	private String customerName;//客户名称
	private String supplierName;//供料单位
	private String materielName;//产品名称
	private String materielModel;//规格型号
	private Double tareWeight;//皮重（t）
	private Double grossWeight;//毛重（t）
	private Double suttle;//净重（t）
	private String carrierName;//承运人
	private String carrierTelephone;//承运人电话
	private String fleetName;//车队名称
	private String isSelfLifting;//是否自提
	private String consignee;//收货人
	private String consigneePhone;//收货人电话
	private String testReportNumber;//检测报告编号
	private String facingSlipNum;//封签号
	private String facingSlipNum2;//封签号2
	private String facingSlipNum3;//封签号3
	private String facingSlipNum4;//封签号4
	private Double temperature;//温度
	private String remarks;//备注
	private String weightCollector;//检斤员
	private String outType;//出库单状态 0：正常 1：调拨 2：退货 3:空发 4:兑换 5.兑换时调拨
	private String transportBalance;//是否自提
	private String otherCompanyName;//其它公司
	private String driverAutograph;//图片名称
	private String materielName2;//兑换产品名称
	private String materielModel2;//兑换规格型号
	private Double exchangeWeight;//兑换重量
	private Double proportion;//兑换比例
	private String tareOperator;//皮重司磅员（检斤员）
	private String createrDate;//创建时间
	private String orderDetailedRemarks;//销售订单明细备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getTareMeasureTime() {
		return tareMeasureTime;
	}
	public void setTareMeasureTime(String tareMeasureTime) {
		this.tareMeasureTime = tareMeasureTime;
	}
	public String getWeighingUnit() {
		return weighingUnit;
	}
	public void setWeighingUnit(String weighingUnit) {
		this.weighingUnit = weighingUnit;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getCarrierTelephone() {
		return carrierTelephone;
	}
	public void setCarrierTelephone(String carrierTelephone) {
		this.carrierTelephone = carrierTelephone;
	}
	public String getFleetName() {
		return fleetName;
	}
	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}
	public String getIsSelfLifting() {
		return isSelfLifting;
	}
	public void setIsSelfLifting(String isSelfLifting) {
		this.isSelfLifting = isSelfLifting;
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
	public String getTestReportNumber() {
		return testReportNumber;
	}
	public void setTestReportNumber(String testReportNumber) {
		this.testReportNumber = testReportNumber;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getWeightCollector() {
		return weightCollector;
	}
	public void setWeightCollector(String weightCollector) {
		this.weightCollector = weightCollector;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getTransportBalance() {
		return transportBalance;
	}
	public void setTransportBalance(String transportBalance) {
		this.transportBalance = transportBalance;
	}
	public String getOtherCompanyName() {
		return otherCompanyName;
	}
	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}
	public String getDriverAutograph() {
		return driverAutograph;
	}
	public void setDriverAutograph(String driverAutograph) {
		this.driverAutograph = driverAutograph;
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
	public Double getExchangeWeight() {
		return exchangeWeight;
	}
	public void setExchangeWeight(Double exchangeWeight) {
		this.exchangeWeight = exchangeWeight;
	}
	public Double getProportion() {
		return proportion;
	}
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}
	public String getTareOperator() {
		return tareOperator;
	}
	public void setTareOperator(String tareOperator) {
		this.tareOperator = tareOperator;
	}
	public String getCreaterDate() {
		return createrDate;
	}
	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}
	public String getOrderDetailedRemarks() {
		return orderDetailedRemarks;
	}
	public void setOrderDetailedRemarks(String orderDetailedRemarks) {
		this.orderDetailedRemarks = orderDetailedRemarks;
	}
	
}
