package com.oil.model.testreports;

import java.math.BigDecimal;

public class Storagemeasure {

	// id
	private int id;
	
	// 流水号
	private String serialID;
	
	// ID卡编号
	private String iDCode;
	
	// 称量类型:1 自动称重；2手动称重；3手工归档
	private String weighType;
	
	// 销售合同id
	private int contractId;
	
	// 客户名称
	private String client;
	
	// 客户别称
	private String customerAlias;
	
	// 物料名称
	private int productID;
	
	// 车牌号码
	private String plateNumber;
	
	// 送货人
	private String deliveryMan;
	
	// 送货电话
	private String deliveryManPhone;
	
	// 车主
	private String carOwner;
	
	// 车主电话
	private String carOwnerTelephone;
	
	// 备注
	private String remarks;
	
	// 采购合同id
	private int purchaseContractId;
	
	// 供应商id
	private int supplierId;
	
	// 供应物料允许偏差
	private BigDecimal supplierDeviation;
	
	// 供应料净重
	private BigDecimal supplierSuttle;
	
	// 皮重
	private BigDecimal tareWeight;
	
	// 皮重司磅员
	private String tareOperator;
	
	// 皮重称重时间
	private String tareMeasureTime;
	
	// 毛重
	private BigDecimal grossWeight;
	
	// 毛重司磅员
	private String grossOperator;
	
	// 毛重称重时间
	private String grossMeasureTime;
	
	// 净重
	private BigDecimal suttle;
	
	private BigDecimal realityDeviation;

	// 物料实际偏差类型: 0 实际值,1 百分比
	private String realityDeviationType;
	
	// 技术说明
	private String skillExplain;
	
	// 数量
	private BigDecimal amount;
	
	// 入库检测是否偏差过大标识 0：偏差 1：正常
	private String deviationMark;
	
	// 来料磅单净重
	private BigDecimal lbsNetWeight;
	
	// 是否打印
	private int isPrint;
	
	// 归档人
	private String filingPerson;
	
	// 是否手工归档
	private String IsManualFiling;
	
	// 手工归档原因
	private String FileCause;
	
	// 异常标识,0 正常,1异常 默认0
	private int abnormalFlag;
	
	// 异常处理建议
	private String abnormalRemarks;
	
	// 异常处理人
	private String abnormalOperator;

	// 异常处理时间
	private String abnormalDatetime;
	
	private String abnormalPhone;
	
	// 车辆类型 0小车,1大车
	private String carType;
	
	// 优先级 0正常, 1 优先
	private String priority;
	
	private String yardman;

	// 作废原因, 作废标识使用 有效标识Valid_Flag
	private String cancellationCause;
	
	// 入库单类型标识 0：入库单 1：未入厂出库单对应的入库单 2:未入厂出库 3：退货
	private String enterTypeMark;
	
	// 出库单id
	private int outWarehouseId;
	
	// 检测报告id
	private int testReportId;
	
	// 运输单id
	private int transportListId;
	
	// 磅单编号
	private String lbsNumber;
	
	// 上传标识
	private int upload;
	
	// 有效标识,0无效,1有效
	private int validFlag;
	
	// 是否大车称重
	private int isHeavyCar;
	
	// 入厂时间
	private String EnterDate;
	
	// 删除标记
	private int isDel;
	
	// 创建人
	private String creater;
	
	// 创建日期
	private String createrDate;
	
	// 修改人
	private String reviser;
	
	// 修改日期
	private String reviserDate;

	//合格标识
	private String flag;
	
	//物料名称
	private String MaterielNameId;
	
	//规格型号
	private String MaterielModelId;
	
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
	public String getiDCode() {
		return iDCode;
	}
	public void setiDCode(String iDCode) {
		this.iDCode = iDCode;
	}
	public String getWeighType() {
		return weighType;
	}
	public void setWeighType(String weighType) {
		this.weighType = weighType;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
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
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	public int getPurchaseContractId() {
		return purchaseContractId;
	}
	public void setPurchaseContractId(int purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public BigDecimal getSupplierDeviation() {
		return supplierDeviation;
	}
	public void setSupplierDeviation(BigDecimal supplierDeviation) {
		this.supplierDeviation = supplierDeviation;
	}
	public BigDecimal getSupplierSuttle() {
		return supplierSuttle;
	}
	public void setSupplierSuttle(BigDecimal supplierSuttle) {
		this.supplierSuttle = supplierSuttle;
	}
	public BigDecimal getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(BigDecimal tareWeight) {
		this.tareWeight = tareWeight;
	}
	public String getTareOperator() {
		return tareOperator;
	}
	public void setTareOperator(String tareOperator) {
		this.tareOperator = tareOperator;
	}
	public String getTareMeasureTime() {
		return tareMeasureTime;
	}
	public void setTareMeasureTime(String tareMeasureTime) {
		this.tareMeasureTime = tareMeasureTime;
	}
	public BigDecimal getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getGrossOperator() {
		return grossOperator;
	}
	public void setGrossOperator(String grossOperator) {
		this.grossOperator = grossOperator;
	}
	public String getGrossMeasureTime() {
		return grossMeasureTime;
	}
	public void setGrossMeasureTime(String grossMeasureTime) {
		this.grossMeasureTime = grossMeasureTime;
	}
	public BigDecimal getSuttle() {
		return suttle;
	}
	public void setSuttle(BigDecimal suttle) {
		this.suttle = suttle;
	}
	public BigDecimal getRealityDeviation() {
		return realityDeviation;
	}
	public void setRealityDeviation(BigDecimal realityDeviation) {
		this.realityDeviation = realityDeviation;
	}
	public String getRealityDeviationType() {
		return realityDeviationType;
	}
	public void setRealityDeviationType(String realityDeviationType) {
		this.realityDeviationType = realityDeviationType;
	}
	public String getSkillExplain() {
		return skillExplain;
	}
	public void setSkillExplain(String skillExplain) {
		this.skillExplain = skillExplain;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDeviationMark() {
		return deviationMark;
	}
	public void setDeviationMark(String deviationMark) {
		this.deviationMark = deviationMark;
	}
	public BigDecimal getLbsNetWeight() {
		return lbsNetWeight;
	}
	public void setLbsNetWeight(BigDecimal lbsNetWeight) {
		this.lbsNetWeight = lbsNetWeight;
	}
	public int getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}
	public String getFilingPerson() {
		return filingPerson;
	}
	public void setFilingPerson(String filingPerson) {
		this.filingPerson = filingPerson;
	}
	public int getAbnormalFlag() {
		return abnormalFlag;
	}
	public void setAbnormalFlag(int abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}
	public String getAbnormalRemarks() {
		return abnormalRemarks;
	}
	public void setAbnormalRemarks(String abnormalRemarks) {
		this.abnormalRemarks = abnormalRemarks;
	}
	public String getAbnormalOperator() {
		return abnormalOperator;
	}
	public void setAbnormalOperator(String abnormalOperator) {
		this.abnormalOperator = abnormalOperator;
	}
	public String getAbnormalDatetime() {
		return abnormalDatetime;
	}
	public void setAbnormalDatetime(String abnormalDatetime) {
		this.abnormalDatetime = abnormalDatetime;
	}
	public String getAbnormalPhone() {
		return abnormalPhone;
	}
	public void setAbnormalPhone(String abnormalPhone) {
		this.abnormalPhone = abnormalPhone;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getYardman() {
		return yardman;
	}
	public void setYardman(String yardman) {
		this.yardman = yardman;
	}
	public String getCancellationCause() {
		return cancellationCause;
	}
	public void setCancellationCause(String cancellationCause) {
		this.cancellationCause = cancellationCause;
	}
	public String getEnterTypeMark() {
		return enterTypeMark;
	}
	public void setEnterTypeMark(String enterTypeMark) {
		this.enterTypeMark = enterTypeMark;
	}
	public int getOutWarehouseId() {
		return outWarehouseId;
	}
	public void setOutWarehouseId(int outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}
	public int getTestReportId() {
		return testReportId;
	}
	public void setTestReportId(int testReportId) {
		this.testReportId = testReportId;
	}
	public int getTransportListId() {
		return transportListId;
	}
	public void setTransportListId(int transportListId) {
		this.transportListId = transportListId;
	}
	public String getLbsNumber() {
		return lbsNumber;
	}
	public void setLbsNumber(String lbsNumber) {
		this.lbsNumber = lbsNumber;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public int getIsHeavyCar() {
		return isHeavyCar;
	}
	public void setIsHeavyCar(int isHeavyCar) {
		this.isHeavyCar = isHeavyCar;
	}
	public String getEnterDate() {
		return EnterDate;
	}
	public void setEnterDate(String enterDate) {
		EnterDate = enterDate;
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
	public String getIsManualFiling() {
		return IsManualFiling;
	}
	public void setIsManualFiling(String isManualFiling) {
		IsManualFiling = isManualFiling;
	}
	public String getFileCause() {
		return FileCause;
	}
	public void setFileCause(String fileCause) {
		FileCause = fileCause;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMaterielNameId() {
		return MaterielNameId;
	}
	public void setMaterielNameId(String materielNameId) {
		MaterielNameId = materielNameId;
	}
	public String getMaterielModelId() {
		return MaterielModelId;
	}
	public void setMaterielModelId(String materielModelId) {
		MaterielModelId = materielModelId;
	}
	
	
}
