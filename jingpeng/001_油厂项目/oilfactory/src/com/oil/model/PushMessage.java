package com.oil.model;

public class PushMessage {

	//下发生产计划
	private String nextProductionCount;
	
	//出库单检测
	private String exportmeasureCount;
	
	//生产工艺通知单
	private String isInspector;
	
	//生产过程检测
	private String isExamine;
	
	//未称重出库单
	private String isCheck;
	
	//生产任务核对
	private String isProduction;
	
	//生产任务确认
	private String isApplication;
	
	//提交质检申请
	private String isQualified;
	
	//生产完成确认
	private String isComplete;
	
	//实际原料消耗
	private String isConfirmSubmission;
	
	public String getExportmeasureCount() {
		return exportmeasureCount;
	}

	public void setExportmeasureCount(String exportmeasureCount) {
		this.exportmeasureCount = exportmeasureCount;
	}

	public String getIsInspector() {
		return isInspector;
	}

	public void setIsInspector(String isInspector) {
		this.isInspector = isInspector;
	}

	public String getIsExamine() {
		return isExamine;
	}

	public void setIsExamine(String isExamine) {
		this.isExamine = isExamine;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getIsProduction() {
		return isProduction;
	}

	public void setIsProduction(String isProduction) {
		this.isProduction = isProduction;
	}

	public String getIsApplication() {
		return isApplication;
	}

	public void setIsApplication(String isApplication) {
		this.isApplication = isApplication;
	}

	public String getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public String getIsConfirmSubmission() {
		return isConfirmSubmission;
	}

	public void setIsConfirmSubmission(String isConfirmSubmission) {
		this.isConfirmSubmission = isConfirmSubmission;
	}
	
	public String getNextProductionCount() {
		return nextProductionCount;
	}

	public void setNextProductionCount(String nextProductionCount) {
		this.nextProductionCount = nextProductionCount;
	}

	
	
}
