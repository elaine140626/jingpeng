package com.testRoom.model;

public class ShiYan06DetailEntity {
	private String Stab; // 稳定度（kN)
	private String Flow; // 流值（mm）
	private String MarshallModulus; // 马歇尔模数（kN/mm)
	private String UltLoadImage1;// 图表
	public String getStab() {
		return Stab;
	}
	public void setStab(String stab) {
		Stab = stab;
	}
	public String getFlow() {
		return Flow;
	}
	public void setFlow(String flow) {
		Flow = flow;
	}
	public String getMarshallModulus() {
		return MarshallModulus;
	}
	public void setMarshallModulus(String marshallModulus) {
		MarshallModulus = marshallModulus;
	}
	public String getUltLoadImage1() {
		return UltLoadImage1;
	}
	public void setUltLoadImage1(String ultLoadImage1) {
		UltLoadImage1 = ultLoadImage1;
	}
	
}
