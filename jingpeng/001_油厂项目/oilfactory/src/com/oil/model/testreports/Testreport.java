package com.oil.model.testreports;

public class Testreport {

	
	private Integer id;
	
	//检测员
	private Integer testPersonnelId; 
	
	// 检测员名称
	private String testPersonnelName;
	
	//检测时间
	private String testDate;
	
	//检测报告编号
	private String testReportNumber;
	
	//销售订单或出库单标识
	private String saleOrOut;
	
	//备注
	private String remarks;
	
	//是否全部合格
	private Integer isQualified;
	
	//出/入库单id
	private Integer exportStorageId;
	
	//报告名头
	private String reportRenown;
	
	//产品型号
	private String productModel;
	
	//使用地点
	private String usePlace;
	
	//指标类型
	private String indexType;
	
	//指标类型名称
	private String indexTypeName;
	
	//结论
	private String conclusion;
	
	//报告备注
	private String reportRemarks;
	
	//内部备注
	private String insideRemarks;
	
	//复核人
	private Integer reviewerId;
	
	//检测负责人
	private Integer inspectionSupervisorId;
	
	//检测单位
	private Integer detectionUnitId;
	
	// 检测单位名称
	private String detectionUnitName;
	
	// 是否兑换
	private Integer exchange;
	
	//删除标记
	private Integer isDel;
	
	private String factoryTime;
	private String grossMeasureTime;
	private String tareMeasureTime;
	//创建人
	private String creater;
	
	//创建日期
	private String createrDate;
	
	//修改人
	private String reviser;
	
	//修改日期
	private String reviserDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTestPersonnelId() {
		return testPersonnelId;
	}

	public void setTestPersonnelId(Integer testPersonnelId) {
		this.testPersonnelId = testPersonnelId;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getTestReportNumber() {
		return testReportNumber;
	}

	public void setTestReportNumber(String testReportNumber) {
		this.testReportNumber = testReportNumber;
	}

	public String getSaleOrOut() {
		return saleOrOut;
	}

	public void setSaleOrOut(String saleOrOut) {
		this.saleOrOut = saleOrOut;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public Integer getExportStorageId() {
		return exportStorageId;
	}

	public void setExportStorageId(Integer exportStorageId) {
		this.exportStorageId = exportStorageId;
	}

	public String getReportRenown() {
		return reportRenown;
	}

	public void setReportRenown(String reportRenown) {
		this.reportRenown = reportRenown;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getUsePlace() {
		return usePlace;
	}

	public void setUsePlace(String usePlace) {
		this.usePlace = usePlace;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getReportRemarks() {
		return reportRemarks;
	}

	public void setReportRemarks(String reportRemarks) {
		this.reportRemarks = reportRemarks;
	}

	public String getInsideRemarks() {
		return insideRemarks;
	}

	public void setInsideRemarks(String insideRemarks) {
		this.insideRemarks = insideRemarks;
	}

	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
	}

	public Integer getInspectionSupervisorId() {
		return inspectionSupervisorId;
	}

	public void setInspectionSupervisorId(Integer inspectionSupervisorId) {
		this.inspectionSupervisorId = inspectionSupervisorId;
	}

	public Integer getDetectionUnitId() {
		return detectionUnitId;
	}

	public void setDetectionUnitId(Integer detectionUnitId) {
		this.detectionUnitId = detectionUnitId;
	}

	public String getDetectionUnitName() {
		return detectionUnitName;
	}

	public void setDetectionUnitName(String detectionUnitName) {
		this.detectionUnitName = detectionUnitName;
	}
	public Integer getExchange() {
		return exchange;
	}

	public void setExchange(Integer exchange) {
		this.exchange = exchange;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

	public String getGrossMeasureTime() {
		return grossMeasureTime;
	}

	public void setGrossMeasureTime(String grossMeasureTime) {
		this.grossMeasureTime = grossMeasureTime;
	}

	public String getTareMeasureTime() {
		return tareMeasureTime;
	}

	public void setTareMeasureTime(String tareMeasureTime) {
		this.tareMeasureTime = tareMeasureTime;
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

	public String getTestPersonnelName() {
		return testPersonnelName;
	}

	public void setTestPersonnelName(String testPersonnelName) {
		this.testPersonnelName = testPersonnelName;
	}

	public String getIndexTypeName() {
		return indexTypeName;
	}

	public void setIndexTypeName(String indexTypeName) {
		this.indexTypeName = indexTypeName;
	}
}
