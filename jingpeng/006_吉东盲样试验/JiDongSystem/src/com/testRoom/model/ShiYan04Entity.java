package com.testRoom.model;

public class ShiYan04Entity {
	private String SerialNumber;//流水号          
	private String TestRoomName;      // 实验室名
	private String TestRules;          // 试验依据
	private String testDate;// 试验日期
	private String SampleName;         // 样品名称
	private String SampleCount;        // 试件个数
	private String SpecType;           // 试件类型
	private String TestOperator;       // 实验员
	private String MoldDate;           // 成型日期
	private String DesignStrength;     // 设计强度
	private String ConstructionUnit;   // 施工单位
	private String EngineeringName;    // 工程名称
	private String Purpose;            // 工程部位/用途）
	private String AvgValue;           // 平均抗压强度（MPa）
	private String IsQualifiedTest;    // 结果判定
	private String QRCode;//二维码
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
	public String getSpecType() {
		return SpecType;
	}
	public void setSpecType(String specType) {
		SpecType = specType;
	}
	public String getTestOperator() {
		return TestOperator;
	}
	public void setTestOperator(String testOperator) {
		TestOperator = testOperator;
	}
	public String getMoldDate() {
		return MoldDate;
	}
	public void setMoldDate(String moldDate) {
		MoldDate = moldDate;
	}
	public String getDesignStrength() {
		return DesignStrength;
	}
	public void setDesignStrength(String designStrength) {
		DesignStrength = designStrength;
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
	public String getAvgValue() {
		return AvgValue;
	}
	public void setAvgValue(String avgValue) {
		AvgValue = avgValue;
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
