package com.testRoom.model;

public class ShiYan21Entity {
	private String SerialNumber;// 流水号
	private String TestRoomName;// 实验室名
	private String TestRules;// 试验依据
	private String testDate; // 试验日期
	private String SampleName;// 样品名称
	private String SampleCount;// 试件数量
	private String TestOperator;// 实验员
	private String Diameter;// 直径
	private String Aggregate_Type;// 集料种类
	private String Aggregate_Specification;// 集料规格
	private String ConstructionUnit;// 施工单位
	private String EngineeringName;// 工程名称
	private String Purpose;// 工程部位/用途
	private Double Pass_Rate1 = 0D;// 小于0.075mm的含量（%）1 粗集料筛分试验 细集料筛分试验
	private Double Pass_Rate2 = 0D;// 小于0.075mm的含量（%）2 粗集料筛分试验 细集料筛分试验
	private String IsQualifiedTest;// 结果判定
	private String QRCode;// 二维码
	private Double Avg_Fineness_Modulus = 0D;// 平均细度模数 细集料筛分试验
	private String JudgementIndex;// 判定指标 t0表
	private String Test_Method;// 试验方法 粗集料针、片状颗粒含量试验
	private Float Samp_Mass = 0F; // 试件总质量(g) 粗集料针、片状颗粒含量试验（规准仪法）
	private Float Elon_Part_Mass = 0F; // 针片状颗粒总质量（g) 粗集料针、片状颗粒含量试验（规准仪法）
	private Double Elongated_Particles = 0D; // 针片状颗粒含量(%) 粗集料针、片状颗粒含量试验（规准仪法）
	private String CementType; // 水泥品种 水泥凝结时间
	private String CementStrengthGrade; // 强度等级 水泥凝结时间
	private String Start_Hour; // 起始时间时 水泥凝结时间
	private String Start_Minute; // 起始时间分 水泥凝结时间
	private String Initial_Set_Hour; // 初凝状态时间时 水泥凝结时间
	private String Initial_Set_Minute; // 初凝状态时间分 水泥凝结时间
	private String Final_Set_Hour; // 终凝状态时间时 水泥凝结时间
	private String Final_Set_Minute; // 终凝状态时间分 水泥凝结时间
	private String Init_Set_Time; // 初凝时间（分） 水泥凝结时间
	private String Final_Set_Time; // 终凝时间（分） 水泥凝结时间
	private String Modifier;// 修改人
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
	public String getDiameter() {
		return Diameter;
	}
	public void setDiameter(String diameter) {
		Diameter = diameter;
	}
	public String getAggregate_Type() {
		return Aggregate_Type;
	}
	public void setAggregate_Type(String aggregate_Type) {
		Aggregate_Type = aggregate_Type;
	}
	public String getAggregate_Specification() {
		return Aggregate_Specification;
	}
	public void setAggregate_Specification(String aggregate_Specification) {
		Aggregate_Specification = aggregate_Specification;
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
	public Double getPass_Rate1() {
		return Pass_Rate1;
	}
	public void setPass_Rate1(Double pass_Rate1) {
		Pass_Rate1 = pass_Rate1;
	}
	public Double getPass_Rate2() {
		return Pass_Rate2;
	}
	public void setPass_Rate2(Double pass_Rate2) {
		Pass_Rate2 = pass_Rate2;
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
	public Double getAvg_Fineness_Modulus() {
		return Avg_Fineness_Modulus;
	}
	public void setAvg_Fineness_Modulus(Double avg_Fineness_Modulus) {
		Avg_Fineness_Modulus = avg_Fineness_Modulus;
	}
	public String getJudgementIndex() {
		return JudgementIndex;
	}
	public void setJudgementIndex(String judgementIndex) {
		JudgementIndex = judgementIndex;
	}
	public String getTest_Method() {
		return Test_Method;
	}
	public void setTest_Method(String test_Method) {
		Test_Method = test_Method;
	}
	public Float getSamp_Mass() {
		return Samp_Mass;
	}
	public void setSamp_Mass(Float samp_Mass) {
		Samp_Mass = samp_Mass;
	}
	public Float getElon_Part_Mass() {
		return Elon_Part_Mass;
	}
	public void setElon_Part_Mass(Float elon_Part_Mass) {
		Elon_Part_Mass = elon_Part_Mass;
	}
	public Double getElongated_Particles() {
		return Elongated_Particles;
	}
	public void setElongated_Particles(Double elongated_Particles) {
		Elongated_Particles = elongated_Particles;
	}
	public String getCementType() {
		return CementType;
	}
	public void setCementType(String cementType) {
		CementType = cementType;
	}
	public String getCementStrengthGrade() {
		return CementStrengthGrade;
	}
	public void setCementStrengthGrade(String cementStrengthGrade) {
		CementStrengthGrade = cementStrengthGrade;
	}
	public String getStart_Hour() {
		return Start_Hour;
	}
	public void setStart_Hour(String start_Hour) {
		Start_Hour = start_Hour;
	}
	public String getStart_Minute() {
		return Start_Minute;
	}
	public void setStart_Minute(String start_Minute) {
		Start_Minute = start_Minute;
	}
	public String getInitial_Set_Hour() {
		return Initial_Set_Hour;
	}
	public void setInitial_Set_Hour(String initial_Set_Hour) {
		Initial_Set_Hour = initial_Set_Hour;
	}
	public String getInitial_Set_Minute() {
		return Initial_Set_Minute;
	}
	public void setInitial_Set_Minute(String initial_Set_Minute) {
		Initial_Set_Minute = initial_Set_Minute;
	}
	public String getFinal_Set_Hour() {
		return Final_Set_Hour;
	}
	public void setFinal_Set_Hour(String final_Set_Hour) {
		Final_Set_Hour = final_Set_Hour;
	}
	public String getFinal_Set_Minute() {
		return Final_Set_Minute;
	}
	public void setFinal_Set_Minute(String final_Set_Minute) {
		Final_Set_Minute = final_Set_Minute;
	}
	public String getInit_Set_Time() {
		return Init_Set_Time;
	}
	public void setInit_Set_Time(String init_Set_Time) {
		Init_Set_Time = init_Set_Time;
	}
	public String getFinal_Set_Time() {
		return Final_Set_Time;
	}
	public void setFinal_Set_Time(String final_Set_Time) {
		Final_Set_Time = final_Set_Time;
	}
	public String getModifier() {
		return Modifier;
	}
	public void setModifier(String modifier) {
		Modifier = modifier;
	}
}
