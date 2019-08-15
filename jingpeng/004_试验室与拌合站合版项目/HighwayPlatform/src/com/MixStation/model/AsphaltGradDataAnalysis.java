package com.MixStation.model;
/**
 * 沥青生产数据级配分析
 * 
 * @author Administrator
 *
 */
public class AsphaltGradDataAnalysis {
	//private String id;
	private String curve_Type;// 曲线类型
	private String A0_075;// 0.075孔径通过率
	private String A0_15;// 0.15孔径通过率
	private String A0_3;// 0.3孔径通过率
	private String A0_6;// 0.6孔径通过率
	private String A1_18;// 1.18孔径通过率
	private String A2_36;// 2.36孔径通过率
	private String A4_75;// 4.75孔径通过率
	private String A9_5;// 9.5孔径通过率
	private String A13_2;// 13.2孔径通过率
	private String A16;// 16孔径通过率
	private String A19;// 19孔径通过率
	private String A26_5;// 26.5孔径通过率
	private String A31_5;// 31.5孔径通过率
	private String A37_5;// 37.5孔径通过率
	private String A53;// 53孔径通过率
	
	private int id;
	private int proId;// 生产数据Id
	private String CurveType;// 曲线类型
	// 级配id
	private int analysisId;
	// 筛孔id
	private int meshId;
	// 1#仓
	private double ware1;
	// 2#仓
	private double ware2;
	// 3#仓
	private double ware3;
	// 4#仓
	private double ware4;
	// 5#仓
	private double ware5;
	// 6#仓
	private double ware6;
	// 冷粉仓1
	private double coldWare1;
	// 冷粉仓2
	private double coldWare2;
	// 热粉仓
	private double hotWare;
	// 上限
	private double upperLimit;
	// 下限
	private double lowerLimit;
	private int valid_Flag;// 有效标识
	private int upload;// 上传标识
	private double meshSize;

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public String getCurve_Type() {
		return curve_Type;
	}

	public void setCurve_Type(String curve_Type) {
		this.curve_Type = curve_Type;
	}

	public String getA0_075() {
		return A0_075;
	}

	public void setA0_075(String a0_075) {
		A0_075 = a0_075;
	}

	public String getA0_15() {
		return A0_15;
	}

	public void setA0_15(String a0_15) {
		A0_15 = a0_15;
	}

	public String getA0_3() {
		return A0_3;
	}

	public void setA0_3(String a0_3) {
		A0_3 = a0_3;
	}

	public String getA0_6() {
		return A0_6;
	}

	public void setA0_6(String a0_6) {
		A0_6 = a0_6;
	}

	public String getA1_18() {
		return A1_18;
	}

	public void setA1_18(String a1_18) {
		A1_18 = a1_18;
	}

	public String getA2_36() {
		return A2_36;
	}

	public void setA2_36(String a2_36) {
		A2_36 = a2_36;
	}

	public String getA4_75() {
		return A4_75;
	}

	public void setA4_75(String a4_75) {
		A4_75 = a4_75;
	}

	public String getA9_5() {
		return A9_5;
	}

	public void setA9_5(String a9_5) {
		A9_5 = a9_5;
	}

	public String getA13_2() {
		return A13_2;
	}

	public void setA13_2(String a13_2) {
		A13_2 = a13_2;
	}

	public String getA16() {
		return A16;
	}

	public void setA16(String a16) {
		A16 = a16;
	}

	public String getA19() {
		return A19;
	}

	public void setA19(String a19) {
		A19 = a19;
	}

	public String getA26_5() {
		return A26_5;
	}

	public void setA26_5(String a26_5) {
		A26_5 = a26_5;
	}

	public String getA31_5() {
		return A31_5;
	}

	public void setA31_5(String a31_5) {
		A31_5 = a31_5;
	}

	public String getA37_5() {
		return A37_5;
	}

	public void setA37_5(String a37_5) {
		A37_5 = a37_5;
	}

	public String getA53() {
		return A53;
	}

	public void setA53(String a53) {
		A53 = a53;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getCurveType() {
		return CurveType;
	}

	public void setCurveType(String curveType) {
		CurveType = curveType;
	}

	public int getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(int analysisId) {
		this.analysisId = analysisId;
	}

	public int getMeshId() {
		return meshId;
	}

	public void setMeshId(int meshId) {
		this.meshId = meshId;
	}

	public double getWare1() {
		return ware1;
	}

	public void setWare1(double ware1) {
		this.ware1 = ware1;
	}

	public double getWare2() {
		return ware2;
	}

	public void setWare2(double ware2) {
		this.ware2 = ware2;
	}

	public double getWare3() {
		return ware3;
	}

	public void setWare3(double ware3) {
		this.ware3 = ware3;
	}

	public double getWare4() {
		return ware4;
	}

	public void setWare4(double ware4) {
		this.ware4 = ware4;
	}

	public double getWare5() {
		return ware5;
	}

	public void setWare5(double ware5) {
		this.ware5 = ware5;
	}

	public double getWare6() {
		return ware6;
	}

	public void setWare6(double ware6) {
		this.ware6 = ware6;
	}

	public double getColdWare1() {
		return coldWare1;
	}

	public void setColdWare1(double coldWare1) {
		this.coldWare1 = coldWare1;
	}

	public double getColdWare2() {
		return coldWare2;
	}

	public void setColdWare2(double coldWare2) {
		this.coldWare2 = coldWare2;
	}

	public double getHotWare() {
		return hotWare;
	}

	public void setHotWare(double hotWare) {
		this.hotWare = hotWare;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public int getValid_Flag() {
		return valid_Flag;
	}

	public void setValid_Flag(int valid_Flag) {
		this.valid_Flag = valid_Flag;
	}

	public int getUpload() {
		return upload;
	}

	public void setUpload(int upload) {
		this.upload = upload;
	}

	public double getMeshSize() {
		return meshSize;
	}

	public void setMeshSize(double meshSize) {
		this.meshSize = meshSize;
	}
	
}
