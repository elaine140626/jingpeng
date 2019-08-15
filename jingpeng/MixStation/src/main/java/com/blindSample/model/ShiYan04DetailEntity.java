package com.blindSample.model;

public class ShiYan04DetailEntity {
	private String MaxStress;     // 试验的最大压力（N）
	private String Rc;    // 无侧限坑压强度（MPa）
	public String getMaxStress() {
		return MaxStress;
	}
	public void setMaxStress(String maxStress) {
		MaxStress = maxStress;
	}
	public String getRc() {
		return Rc;
	}
	public void setRc(String rc) {
		Rc = rc;
	}
	
}
