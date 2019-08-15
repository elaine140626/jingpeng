package com.testRoom.model;

public class ShiYan06Entity {
	private String SerialNumber; // 流水号
	private String TestRoomName; // 实验室名
	private String TestRules; // 试验依据
	private String testDate; // 试验日期
	private String SampleName; // 样品名称
	private String SampleCount; // 试件个数
	private String TestOperator; // 实验员
	private String GradationType; // 级配类型
	private String MixtureType; // 混合料种类
	private String ConstructionUnit; // 施工单位
	private String EngineeringName; // 工程名称
	private String Purpose; // 工程部位/用途
	private String AvgStab; // 稳定度(kN)平均值
	private String AvgFlow; // 流值(0.1mm)平均值
	private String AvgMarsModulus; // 马歇尔模数(kN/mm)平均值
	// 图表
	private String StabLimit; // 判定指数(稳定度（kN))
	private String FlowLimit; // 判定指数(流值（mm）)
	private String IsQualifiedTest; // 结果判定
	private String QRCode; // 二维码
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getTestRoomName() {
		return TestRoomName;
	}
	public void setTestRoomName(String testRoomName) {
		TestRoomName = testRoomName;
	}
	public String getTestRules() {
		return TestRules;
	}
	public void setTestRules(String testRules) {
		TestRules = testRules;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getSampleName() {
		return SampleName;
	}
	public void setSampleName(String sampleName) {
		SampleName = sampleName;
	}
	public String getSampleCount() {
		return SampleCount;
	}
	public void setSampleCount(String sampleCount) {
		SampleCount = sampleCount;
	}
	public String getTestOperator() {
		return TestOperator;
	}
	public void setTestOperator(String testOperator) {
		TestOperator = testOperator;
	}
	public String getGradationType() {
		return GradationType;
	}
	public void setGradationType(String gradationType) {
		GradationType = gradationType;
	}
	public String getMixtureType() {
		return MixtureType;
	}
	public void setMixtureType(String mixtureType) {
		MixtureType = mixtureType;
	}
	public String getConstructionUnit() {
		return ConstructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		ConstructionUnit = constructionUnit;
	}
	public String getEngineeringName() {
		return EngineeringName;
	}
	public void setEngineeringName(String engineeringName) {
		EngineeringName = engineeringName;
	}
	public String getPurpose() {
		return Purpose;
	}
	public void setPurpose(String purpose) {
		Purpose = purpose;
	}
	public String getAvgStab() {
		return AvgStab;
	}
	public void setAvgStab(String avgStab) {
		AvgStab = avgStab;
	}
	public String getAvgFlow() {
		return AvgFlow;
	}
	public void setAvgFlow(String avgFlow) {
		AvgFlow = avgFlow;
	}
	public String getAvgMarsModulus() {
		return AvgMarsModulus;
	}
	public void setAvgMarsModulus(String avgMarsModulus) {
		AvgMarsModulus = avgMarsModulus;
	}
	public String getStabLimit() {
		return StabLimit;
	}
	public void setStabLimit(String stabLimit) {
		StabLimit = stabLimit;
	}
	public String getFlowLimit() {
		return FlowLimit;
	}
	public void setFlowLimit(String flowLimit) {
		FlowLimit = flowLimit;
	}
	public String getIsQualifiedTest() {
		return IsQualifiedTest;
	}
	public void setIsQualifiedTest(String isQualifiedTest) {
		IsQualifiedTest = isQualifiedTest;
	}
	public String getQRCode() {
		return QRCode;
	}
	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}
	
}
