package com.blindSample.model;

public class ShiYan01Entity {
	private String SerialNumber;//流水号          
	private String TestRoomName;//实验实名
	private String TestRules; // 试验依据
	private String testDate;  //试验日期
	private String SampleName; //样品名称
	private String SampleCount;  // 试件个数
	private String TestOperator;  // 实验员
	private String Cement_Strength_Grade; // 强度等级
	private String Cement_Type; // 水泥品种
	private String Age; // 龄期（天）
	private String ConstructionUnit;  // 施工单位
	private String EngineeringName;   // 工程名称
	private String Purpose;           // 工程部位/用途
	private String CompTrength;  // 判定指标(抗压强度)
	private String RuptureStrength; // 判定指标(抗折强度)
	private String IsQualifiedTest;  // 结果判定
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
	public String getTestOperator() {
		return TestOperator;
	}
	public void setTestOperator(String testOperator) {
		TestOperator = testOperator;
	}
	public String getCement_Strength_Grade() {
		return Cement_Strength_Grade;
	}
	public void setCement_Strength_Grade(String cement_Strength_Grade) {
		Cement_Strength_Grade = cement_Strength_Grade;
	}
	public String getCement_Type() {
		return Cement_Type;
	}
	public void setCement_Type(String cement_Type) {
		Cement_Type = cement_Type;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
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
	public String getCompTrength() {
		return CompTrength;
	}
	public void setCompTrength(String compTrength) {
		CompTrength = compTrength;
	}
	public String getRuptureStrength() {
		return RuptureStrength;
	}
	public void setRuptureStrength(String ruptureStrength) {
		RuptureStrength = ruptureStrength;
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
