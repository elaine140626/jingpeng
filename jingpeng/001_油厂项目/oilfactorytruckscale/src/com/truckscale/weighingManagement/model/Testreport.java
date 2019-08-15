package com.truckscale.weighingManagement.model;

public class Testreport {
	private int id;
	
	//检测员
	private int testPersonnelId; 
	
	//检测时间
	private String testDate;
	
	//检测报告编号
	private String testReportNumber;
	
	//销售订单或出库单标识
	private String saleOrOut;
	
	//备注
	private String remarks;
	
	//是否全部合格
	private int isQualified;
	
	//出/入库单id
	private int exportStorageId;
	
	//报告名头
	private String reportRenown;
	
	//产品型号
	private String productModel;
	
	//使用地点
	private String usePlace;
	
	//指标类型
	private String indexType;
	
	//结论
	private String conclusion;
	
	//报告备注
	private String reportRemarks;
	
	//内部备注
	private String insideRemarks;
	
	//复核人
	private int reviewerId;
	
	//检测负责人
	private int inspectionSupervisorId;
	
	//检测单位
	private int detectionUnitId;
	
	//兑换前后
	private int exchange;
	
	//删除标记
	private int isDel;
	
	private String factoryTime;
	
	//创建人
	private String creater;
	
	//创建日期
	private String createrDate;
	
	//修改人
	private String reviser;
	
	//修改日期
	private String reviserDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestPersonnelId() {
		return testPersonnelId;
	}

	public void setTestPersonnelId(int testPersonnelId) {
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

	public int getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(int isQualified) {
		this.isQualified = isQualified;
	}

	public int getExportStorageId() {
		return exportStorageId;
	}

	public void setExportStorageId(int exportStorageId) {
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

	public int getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}

	public int getInspectionSupervisorId() {
		return inspectionSupervisorId;
	}

	public void setInspectionSupervisorId(int inspectionSupervisorId) {
		this.inspectionSupervisorId = inspectionSupervisorId;
	}

	public int getDetectionUnitId() {
		return detectionUnitId;
	}

	public void setDetectionUnitId(int detectionUnitId) {
		this.detectionUnitId = detectionUnitId;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
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

	public int getExchange() {
		return exchange;
	}

	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	
}
