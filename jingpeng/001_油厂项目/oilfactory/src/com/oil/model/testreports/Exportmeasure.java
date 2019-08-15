package com.oil.model.testreports;

import java.math.BigDecimal;

public class Exportmeasure {

	private int Id;
	
	//流水号
	private String SerialID;
	
	//ID卡编号
	private String IDCode;
	
	//称量类型:1 自动称重；2手动称重；3手工归档
	private String WeighType;

	//销售订单id
	private int SalesOrderId;

	//车牌号码
	private String PlateNumber;

	//客户编号
	private String Client;
	
	//客户名称
	private String CustomerName;

	//客户别称
	private String CustomerAlias;

	//送货地址
	private String Address;

	//收货人
	private String Consignee;

	//收货人电话
	private String ConsigneePhone;

	//物料名称
	private int ProductID;

	//磅单打印物料名称
	private String LbsMaterialName;

	//磅单输出名称
	private String LbsOutputName;

	//是否大车称重
	private int IsCartWeigh;

	//送货人
	private String DeliveryMan;

	//送货电话
	private String DeliveryManPhone;

	//车主
	private String CarOwner;

	//车主电话
	private String CarOwnerTelephone;

	//备注
	private String Remarks;

	//检测报告id
	private int TestReportId;

	//技术说明
	private String TechniqueSpecification;

	//皮重
	private BigDecimal TareWeight;

	//皮重司磅员
	private String TareOperator;

	//皮重称重时间
	private String TareMeasureTime;

	//毛重
	private BigDecimal GrossWeight;

	//毛重司磅员
	private String GrossOperator;

	//毛重称重时间
	private String GrossMeasureTime;

	//净重
	private BigDecimal Suttle;

	//调度时间
	private String DispatchDate;

	//是否打印
	private int IsPrint;

	//是否手工归档
	private String IsManualFiling;

	//归档人
	private String FilingPerson;

	//手工归档原因
	private String FileCause;

	//封签号
	private String FacingSlipNum;

	//报告编号
	private String ReportNum;

	//车辆类型（数据字典） 0小车,1大车
	private String CarType;

	//货品去向
	private String ProductToGo;

	//优先级 0正常, 1 优先
	private String Priority;

	//
	private String AccreditPhone;

	//授权是否可以出库 0 未授权, 1授权
	private int Accredit;

	//授权人
	private String Certigier;

	//授权人时间
	private String AccreditTime;

	//作废原因, 作废标识使用 有效标识Valid_Flag
	private String CancellationCause;

	//销售公司名称
	private String SaleCompanyName;

	//出库单类型标识 0：出库单 1：未入厂出库单
	private int OutTypeMark;

	//销售录入 结算数量
	private BigDecimal Amount;

	//货品单价
	private BigDecimal GoodsPrice;

	//货品成本
	private BigDecimal GoodsCost;

	//财务人员
	private String FinancePersonnel;

	//财务更新日期
	private String FinanceDate;

	//磅单编号
	private String LbsNumber;

	//是否调拨
	private int IsAllot;

	//调拨发起人
	private String AllotPersonnel;

	//调拨重量
	private BigDecimal AllotWeight;

	//是否退货
	private int IsReturn;

	//退货发起人
	private String ReturnPersonnel;

	//退货原因
	private String ReturnReason;

	//出库单状态 0：正常 1：整车调拨（作废） 2：半车调拨 3：退货
	private String OutType;

	//是否未入厂出库
	private int IsEnterFactory;

	//采购合同id
	private int PurchaseContractId;

	//装油卡编号
	private String OilCardCode;

	//是否检测
	private int IsTesting;

	//
	private String Yardman;

	//上传标识
	private int Upload;

	//有效标识,0无效,1有效
	private int ValidFlag;

	//出厂时间
	private String FactoryTime;

	//删除标记
	private int IsDel;

	//创建人
	private String Creater;

	//创建日期
	private String CreaterDate;

	//修改人
	private String reviser;

	//修改日期
	private String ReviserDate;
	
	//合格标识
	private String flag;
	
	//物料名称
	private String MaterielNameId;
	
	//规格型号
	private String MaterielModelId;
	
	//退货重量
	private double ReturnWeight;
	
	private Integer exportStorageId;
	
	private Integer aftExportStorageId;
	
	private Integer befExportStorageId;
	
	// 是否兑换
	private Integer isExchange;
	
	//物料名称(兑换后)
	private String exchangeMaterielName;
	
	//规格型号（兑换后）
	private String exchangeMaterielModel;
	
	//物料名称(兑换前)
	private String materielName;
	
	//规格型号（兑换前）
	private String materielModel;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSerialID() {
		return SerialID;
	}

	public void setSerialID(String serialID) {
		SerialID = serialID;
	}

	public String getIDCode() {
		return IDCode;
	}

	public void setIDCode(String iDCode) {
		IDCode = iDCode;
	}

	public String getWeighType() {
		return WeighType;
	}

	public void setWeighType(String weighType) {
		WeighType = weighType;
	}

	public int getSalesOrderId() {
		return SalesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		SalesOrderId = salesOrderId;
	}

	public String getPlateNumber() {
		return PlateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		PlateNumber = plateNumber;
	}

	public String getClient() {
		return Client;
	}

	public void setClient(String client) {
		Client = client;
	}

	public String getCustomerAlias() {
		return CustomerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		CustomerAlias = customerAlias;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getConsignee() {
		return Consignee;
	}

	public void setConsignee(String consignee) {
		Consignee = consignee;
	}

	public String getConsigneePhone() {
		return ConsigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		ConsigneePhone = consigneePhone;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getLbsMaterialName() {
		return LbsMaterialName;
	}

	public void setLbsMaterialName(String lbsMaterialName) {
		LbsMaterialName = lbsMaterialName;
	}

	public String getLbsOutputName() {
		return LbsOutputName;
	}

	public void setLbsOutputName(String lbsOutputName) {
		LbsOutputName = lbsOutputName;
	}

	public int getIsCartWeigh() {
		return IsCartWeigh;
	}

	public void setIsCartWeigh(int isCartWeigh) {
		IsCartWeigh = isCartWeigh;
	}

	public String getDeliveryMan() {
		return DeliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		DeliveryMan = deliveryMan;
	}

	public String getDeliveryManPhone() {
		return DeliveryManPhone;
	}

	public void setDeliveryManPhone(String deliveryManPhone) {
		DeliveryManPhone = deliveryManPhone;
	}

	public String getCarOwner() {
		return CarOwner;
	}

	public void setCarOwner(String carOwner) {
		CarOwner = carOwner;
	}

	public String getCarOwnerTelephone() {
		return CarOwnerTelephone;
	}

	public void setCarOwnerTelephone(String carOwnerTelephone) {
		CarOwnerTelephone = carOwnerTelephone;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public int getTestReportId() {
		return TestReportId;
	}

	public void setTestReportId(int testReportId) {
		TestReportId = testReportId;
	}

	public String getTechniqueSpecification() {
		return TechniqueSpecification;
	}

	public void setTechniqueSpecification(String techniqueSpecification) {
		TechniqueSpecification = techniqueSpecification;
	}

	public BigDecimal getTareWeight() {
		return TareWeight;
	}

	public void setTareWeight(BigDecimal tareWeight) {
		TareWeight = tareWeight;
	}

	public String getTareOperator() {
		return TareOperator;
	}

	public void setTareOperator(String tareOperator) {
		TareOperator = tareOperator;
	}

	public String getTareMeasureTime() {
		return TareMeasureTime;
	}

	public void setTareMeasureTime(String tareMeasureTime) {
		TareMeasureTime = tareMeasureTime;
	}

	public BigDecimal getGrossWeight() {
		return GrossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		GrossWeight = grossWeight;
	}

	public String getGrossOperator() {
		return GrossOperator;
	}

	public void setGrossOperator(String grossOperator) {
		GrossOperator = grossOperator;
	}

	public String getGrossMeasureTime() {
		return GrossMeasureTime;
	}

	public void setGrossMeasureTime(String grossMeasureTime) {
		GrossMeasureTime = grossMeasureTime;
	}

	public BigDecimal getSuttle() {
		return Suttle;
	}

	public void setSuttle(BigDecimal suttle) {
		Suttle = suttle;
	}

	public String getDispatchDate() {
		return DispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		DispatchDate = dispatchDate;
	}

	public int getIsPrint() {
		return IsPrint;
	}

	public void setIsPrint(int isPrint) {
		IsPrint = isPrint;
	}

	public String getIsManualFiling() {
		return IsManualFiling;
	}

	public void setIsManualFiling(String isManualFiling) {
		IsManualFiling = isManualFiling;
	}

	public String getFilingPerson() {
		return FilingPerson;
	}

	public void setFilingPerson(String filingPerson) {
		FilingPerson = filingPerson;
	}

	public String getFileCause() {
		return FileCause;
	}

	public void setFileCause(String fileCause) {
		FileCause = fileCause;
	}

	public String getFacingSlipNum() {
		return FacingSlipNum;
	}

	public void setFacingSlipNum(String facingSlipNum) {
		FacingSlipNum = facingSlipNum;
	}

	public String getReportNum() {
		return ReportNum;
	}

	public void setReportNum(String reportNum) {
		ReportNum = reportNum;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}

	public String getProductToGo() {
		return ProductToGo;
	}

	public void setProductToGo(String productToGo) {
		ProductToGo = productToGo;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getAccreditPhone() {
		return AccreditPhone;
	}

	public void setAccreditPhone(String accreditPhone) {
		AccreditPhone = accreditPhone;
	}

	public int getAccredit() {
		return Accredit;
	}

	public void setAccredit(int accredit) {
		Accredit = accredit;
	}

	public String getCertigier() {
		return Certigier;
	}

	public void setCertigier(String certigier) {
		Certigier = certigier;
	}

	public String getAccreditTime() {
		return AccreditTime;
	}

	public void setAccreditTime(String accreditTime) {
		AccreditTime = accreditTime;
	}

	public String getCancellationCause() {
		return CancellationCause;
	}

	public void setCancellationCause(String cancellationCause) {
		CancellationCause = cancellationCause;
	}

	public String getSaleCompanyName() {
		return SaleCompanyName;
	}

	public void setSaleCompanyName(String saleCompanyName) {
		SaleCompanyName = saleCompanyName;
	}

	public int getOutTypeMark() {
		return OutTypeMark;
	}

	public void setOutTypeMark(int outTypeMark) {
		OutTypeMark = outTypeMark;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public BigDecimal getGoodsPrice() {
		return GoodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		GoodsPrice = goodsPrice;
	}

	public BigDecimal getGoodsCost() {
		return GoodsCost;
	}

	public void setGoodsCost(BigDecimal goodsCost) {
		GoodsCost = goodsCost;
	}

	public String getFinancePersonnel() {
		return FinancePersonnel;
	}

	public void setFinancePersonnel(String financePersonnel) {
		FinancePersonnel = financePersonnel;
	}

	public String getFinanceDate() {
		return FinanceDate;
	}

	public void setFinanceDate(String financeDate) {
		FinanceDate = financeDate;
	}

	public String getLbsNumber() {
		return LbsNumber;
	}

	public void setLbsNumber(String lbsNumber) {
		LbsNumber = lbsNumber;
	}

	public int getIsAllot() {
		return IsAllot;
	}

	public void setIsAllot(int isAllot) {
		IsAllot = isAllot;
	}

	public String getAllotPersonnel() {
		return AllotPersonnel;
	}

	public void setAllotPersonnel(String allotPersonnel) {
		AllotPersonnel = allotPersonnel;
	}

	public BigDecimal getAllotWeight() {
		return AllotWeight;
	}

	public void setAllotWeight(BigDecimal allotWeight) {
		AllotWeight = allotWeight;
	}

	public int getIsReturn() {
		return IsReturn;
	}

	public void setIsReturn(int isReturn) {
		IsReturn = isReturn;
	}

	public String getReturnPersonnel() {
		return ReturnPersonnel;
	}

	public void setReturnPersonnel(String returnPersonnel) {
		ReturnPersonnel = returnPersonnel;
	}

	public String getReturnReason() {
		return ReturnReason;
	}

	public void setReturnReason(String returnReason) {
		ReturnReason = returnReason;
	}

	public String getOutType() {
		return OutType;
	}

	public void setOutType(String outType) {
		OutType = outType;
	}

	public int getIsEnterFactory() {
		return IsEnterFactory;
	}

	public void setIsEnterFactory(int isEnterFactory) {
		IsEnterFactory = isEnterFactory;
	}

	public int getPurchaseContractId() {
		return PurchaseContractId;
	}

	public void setPurchaseContractId(int purchaseContractId) {
		PurchaseContractId = purchaseContractId;
	}

	public String getOilCardCode() {
		return OilCardCode;
	}

	public void setOilCardCode(String oilCardCode) {
		OilCardCode = oilCardCode;
	}

	public int getIsTesting() {
		return IsTesting;
	}

	public void setIsTesting(int isTesting) {
		IsTesting = isTesting;
	}

	public String getYardman() {
		return Yardman;
	}

	public void setYardman(String yardman) {
		Yardman = yardman;
	}

	public int getUpload() {
		return Upload;
	}

	public void setUpload(int upload) {
		Upload = upload;
	}

	public int getValidFlag() {
		return ValidFlag;
	}

	public void setValidFlag(int validFlag) {
		ValidFlag = validFlag;
	}

	public String getFactoryTime() {
		return FactoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		FactoryTime = factoryTime;
	}

	public int getIsDel() {
		return IsDel;
	}

	public void setIsDel(int isDel) {
		IsDel = isDel;
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
		return reviser;
	}

	public void setReviser(String reviser) {
		this.reviser = reviser;
	}

	public String getReviserDate() {
		return ReviserDate;
	}

	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
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

	public double getReturnWeight() {
		return ReturnWeight;
	}

	public void setReturnWeight(double returnWeight) {
		ReturnWeight = returnWeight;
	}


	public Integer getIsExchange() {
		return isExchange;
	}

	public void setIsExchange(Integer isExchange) {
		this.isExchange = isExchange;
	}

	public String getExchangeMaterielName() {
		return exchangeMaterielName;
	}

	public void setExchangeMaterielName(String exchangeMaterielName) {
		this.exchangeMaterielName = exchangeMaterielName;
	}

	public String getExchangeMaterielModel() {
		return exchangeMaterielModel;
	}

	public void setExchangeMaterielModel(String exchangeMaterielModel) {
		this.exchangeMaterielModel = exchangeMaterielModel;
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

	public Integer getExportStorageId() {
		return exportStorageId;
	}

	public void setExportStorageId(Integer exportStorageId) {
		this.exportStorageId = exportStorageId;
	}

	public Integer getAftExportStorageId() {
		return aftExportStorageId;
	}

	public void setAftExportStorageId(Integer aftExportStorageId) {
		this.aftExportStorageId = aftExportStorageId;
	}

	public Integer getBefExportStorageId() {
		return befExportStorageId;
	}

	public void setBefExportStorageId(Integer befExportStorageId) {
		this.befExportStorageId = befExportStorageId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
}
