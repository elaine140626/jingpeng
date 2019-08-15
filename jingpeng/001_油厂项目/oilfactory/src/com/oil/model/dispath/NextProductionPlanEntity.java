package com.oil.model.dispath;

public class NextProductionPlanEntity {
	private Integer Id; // 主键id
	private Integer SalesOrderDetailedId; // 销售订单明细id
	private Integer SalesOrderId; // 销售订单id
	private Integer CustomerId; // 客户id
	private Double SaleNumber; // 销售数量
	private String CustomerName; // 客户名称(定货单位名称)
	private String CustomerCode; // 客户编号
	private Integer MaterielId; // 物料id
	private String MaterielNumber; // 物料编号(产品编号)
	private String MaterielName; // 物料名称(产品名称)
	private String MaterielModel; // 规格型号(产品型号)
	private String Unit; // 单位
	private String PlanNumber; // 编号
	private Double PlanWeight; // 计划生产量
	private Integer AnalystOrDirector; // 化验员或主任
	private String StorageLocation; // 储位
	private String StorageLocationName; // 储位
	private String Batch; // 生产批次
	private String FinishTime; // 计划完成时间
	private Integer IsLowerHair; // 是否下发
	private String LowerHairPerson; // 下发人
	private String LowerHairDate; // 下发时间
	private Integer IsCheck; // 是否核对
	private String CheckPerson; // 核对人
	private String CheckDate; // 核对时间
	private String BatchingList; // 配料单生产批次
	private String TechnicsNumber; // 工艺编号
	private Integer IsInspector; // 质检员是否审核 0:是 1:否
	private String InspectorPerson; // 质检员审核人
	private String InspectorDate; // 质检员审核时间 
	private Integer IsExamine; // 是否通过审核
	private String ExaminePerson; // 审核人
	private String ExamineDate; // 审核时间
	private Integer IsProduction; // 是否确认生产
	private String ProductionPerson; // 确认人
	private String ProductionDate; // 确认时间
	private Integer IsApplication; // 是否提交质检申请
	private String ApplicationPerson; // 提交人
	private String ApplicationDate; // 提交时间
	private Integer IsQualified; // 质检是否合格
	private String QualifiedPerson; // 质检员
	private String QualifiedDate; // 质检时间
	private Integer IsAdjust; // 是否调整
	private Integer IsComplete; // 是否生产完成
	private String CompletePerson; // 完成人
	private String CompleteDate; // 完成时间
	private Double ActualWeight; // 实际原料消耗重量
	private Integer IsConfirmSubmission; // 是否确认实际消耗提交
	private String ConfirmSubmissionPerson; // 填写人
	private String ConfirmSubmissionDate; // 填写时间
	private String Remarks; // 备注
	private Integer AdjustNumber;// 调整次数
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getSalesOrderDetailedId() {
		return SalesOrderDetailedId;
	}

	public void setSalesOrderDetailedId(Integer salesOrderDetailedId) {
		SalesOrderDetailedId = salesOrderDetailedId;
	}

	public Integer getSalesOrderId() {
		return SalesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		SalesOrderId = salesOrderId;
	}

	public Integer getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Integer customerId) {
		CustomerId = customerId;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public Double getSaleNumber() {
		return SaleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		SaleNumber = saleNumber;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public Integer getMaterielId() {
		return MaterielId;
	}

	public void setMaterielId(Integer materielId) {
		MaterielId = materielId;
	}

	public String getMaterielNumber() {
		return MaterielNumber;
	}

	public void setMaterielNumber(String materielNumber) {
		MaterielNumber = materielNumber;
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

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getPlanNumber() {
		return PlanNumber;
	}

	public void setPlanNumber(String planNumber) {
		PlanNumber = planNumber;
	}

	public Double getPlanWeight() {
		return PlanWeight;
	}

	public void setPlanWeight(Double planWeight) {
		PlanWeight = planWeight;
	}

	public String getStorageLocation() {
		return StorageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		StorageLocation = storageLocation;
	}

	public String getBatch() {
		return Batch;
	}

	public void setBatch(String batch) {
		Batch = batch;
	}

	public String getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}

	public Integer getIsLowerHair() {
		return IsLowerHair;
	}

	public void setIsLowerHair(Integer isLowerHair) {
		IsLowerHair = isLowerHair;
	}

	public String getLowerHairPerson() {
		return LowerHairPerson;
	}

	public void setLowerHairPerson(String lowerHairPerson) {
		LowerHairPerson = lowerHairPerson;
	}

	public String getLowerHairDate() {
		return LowerHairDate;
	}

	public void setLowerHairDate(String lowerHairDate) {
		LowerHairDate = lowerHairDate;
	}

	public Integer getIsCheck() {
		return IsCheck;
	}

	public void setIsCheck(Integer isCheck) {
		IsCheck = isCheck;
	}

	public String getCheckPerson() {
		return CheckPerson;
	}

	public void setCheckPerson(String checkPerson) {
		CheckPerson = checkPerson;
	}

	public String getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
	}

	public String getBatchingList() {
		return BatchingList;
	}

	public void setBatchingList(String batchingList) {
		BatchingList = batchingList;
	}

	public String getTechnicsNumber() {
		return TechnicsNumber;
	}

	public void setTechnicsNumber(String technicsNumber) {
		TechnicsNumber = technicsNumber;
	}

	public Integer getIsExamine() {
		return IsExamine;
	}

	public void setIsExamine(Integer isExamine) {
		IsExamine = isExamine;
	}

	public String getExaminePerson() {
		return ExaminePerson;
	}

	public void setExaminePerson(String examinePerson) {
		ExaminePerson = examinePerson;
	}

	public String getExamineDate() {
		return ExamineDate;
	}

	public void setExamineDate(String examineDate) {
		ExamineDate = examineDate;
	}

	public Integer getIsProduction() {
		return IsProduction;
	}

	public void setIsProduction(Integer isProduction) {
		IsProduction = isProduction;
	}

	public String getProductionPerson() {
		return ProductionPerson;
	}

	public void setProductionPerson(String productionPerson) {
		ProductionPerson = productionPerson;
	}

	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String productionDate) {
		ProductionDate = productionDate;
	}

	public Integer getIsApplication() {
		return IsApplication;
	}

	public void setIsApplication(Integer isApplication) {
		IsApplication = isApplication;
	}

	public String getApplicationPerson() {
		return ApplicationPerson;
	}

	public void setApplicationPerson(String applicationPerson) {
		ApplicationPerson = applicationPerson;
	}

	public String getApplicationDate() {
		return ApplicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		ApplicationDate = applicationDate;
	}

	public Integer getIsQualified() {
		return IsQualified;
	}

	public void setIsQualified(Integer isQualified) {
		IsQualified = isQualified;
	}

	public String getQualifiedPerson() {
		return QualifiedPerson;
	}

	public void setQualifiedPerson(String qualifiedPerson) {
		QualifiedPerson = qualifiedPerson;
	}

	public String getQualifiedDate() {
		return QualifiedDate;
	}

	public void setQualifiedDate(String qualifiedDate) {
		QualifiedDate = qualifiedDate;
	}

	public Integer getIsAdjust() {
		return IsAdjust;
	}

	public void setIsAdjust(Integer isAdjust) {
		IsAdjust = isAdjust;
	}

	public Integer getIsComplete() {
		return IsComplete;
	}

	public void setIsComplete(Integer isComplete) {
		IsComplete = isComplete;
	}

	public String getCompletePerson() {
		return CompletePerson;
	}

	public void setCompletePerson(String completePerson) {
		CompletePerson = completePerson;
	}

	public String getCompleteDate() {
		return CompleteDate;
	}

	public void setCompleteDate(String completeDate) {
		CompleteDate = completeDate;
	}

	public Double getActualWeight() {
		return ActualWeight;
	}

	public void setActualWeight(Double actualWeight) {
		ActualWeight = actualWeight;
	}

	public Integer getIsConfirmSubmission() {
		return IsConfirmSubmission;
	}

	public void setIsConfirmSubmission(Integer isConfirmSubmission) {
		IsConfirmSubmission = isConfirmSubmission;
	}

	public String getConfirmSubmissionPerson() {
		return ConfirmSubmissionPerson;
	}

	public void setConfirmSubmissionPerson(String confirmSubmissionPerson) {
		ConfirmSubmissionPerson = confirmSubmissionPerson;
	}

	public String getConfirmSubmissionDate() {
		return ConfirmSubmissionDate;
	}

	public void setConfirmSubmissionDate(String confirmSubmissionDate) {
		ConfirmSubmissionDate = confirmSubmissionDate;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Integer getAdjustNumber() {
		return AdjustNumber;
	}

	public void setAdjustNumber(Integer adjustNumber) {
		AdjustNumber = adjustNumber;
	}

	public Integer getIsDel() {
		return IsDel;
	}

	public void setIsDel(Integer isDel) {
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

	public String getStorageLocationName() {
		return StorageLocationName;
	}

	public void setStorageLocationName(String storageLocationName) {
		StorageLocationName = storageLocationName;
	}

	public Integer getAnalystOrDirector() {
		return AnalystOrDirector;
	}

	public void setAnalystOrDirector(Integer analystOrDirector) {
		AnalystOrDirector = analystOrDirector;
	}

	public Integer getIsInspector() {
		return IsInspector;
	}

	public void setIsInspector(Integer isInspector) {
		IsInspector = isInspector;
	}

	public String getInspectorPerson() {
		return InspectorPerson;
	}

	public void setInspectorPerson(String inspectorPerson) {
		InspectorPerson = inspectorPerson;
	}

	public String getInspectorDate() {
		return InspectorDate;
	}

	public void setInspectorDate(String inspectorDate) {
		InspectorDate = inspectorDate;
	}

}
