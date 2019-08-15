package com.testRoom.model;

public class ShiYan24DetailEntity {
	private String SerialNumber;
	private Float TBefore_Mass1 = 0F; // 试验前试样质量1
	private Float TAfter_Mass1 = 0F; // 试验后通过2.36mm筛孔的细料质量(g)1
	private Double Crush_Value1 = 0D; // 压碎值单值1
	private Float TBefore_Mass2 = 0F; // 试验前试样质量2
	private Float TAfter_Mass2 = 0F; // 试验后通过2.36mm筛孔的细料质量(g)2
	private Double Crush_Value2 = 0D; // 压碎值单值2
	private Float TBefore_Mass3 = 0F; // 试验前试样质量3
	private Float TAfter_Mass3 = 0F; // 试验后通过2.36mm筛孔的细料质量(g)3
	private Double Crush_Value3 = 0D; // 压碎值单值3
	private Double Avg_Crush_Value = 0D; // 压碎值平均值

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public Float getTBefore_Mass1() {
		return TBefore_Mass1;
	}

	public void setTBefore_Mass1(Float tBefore_Mass1) {
		TBefore_Mass1 = tBefore_Mass1;
	}

	public Float getTAfter_Mass1() {
		return TAfter_Mass1;
	}

	public void setTAfter_Mass1(Float tAfter_Mass1) {
		TAfter_Mass1 = tAfter_Mass1;
	}

	public Double getCrush_Value1() {
		return Crush_Value1;
	}

	public void setCrush_Value1(Double crush_Value1) {
		Crush_Value1 = crush_Value1;
	}

	public Float getTBefore_Mass2() {
		return TBefore_Mass2;
	}

	public void setTBefore_Mass2(Float tBefore_Mass2) {
		TBefore_Mass2 = tBefore_Mass2;
	}

	public Float getTAfter_Mass2() {
		return TAfter_Mass2;
	}

	public void setTAfter_Mass2(Float tAfter_Mass2) {
		TAfter_Mass2 = tAfter_Mass2;
	}

	public Double getCrush_Value2() {
		return Crush_Value2;
	}

	public void setCrush_Value2(Double crush_Value2) {
		Crush_Value2 = crush_Value2;
	}

	public Float getTBefore_Mass3() {
		return TBefore_Mass3;
	}

	public void setTBefore_Mass3(Float tBefore_Mass3) {
		TBefore_Mass3 = tBefore_Mass3;
	}

	public Float getTAfter_Mass3() {
		return TAfter_Mass3;
	}

	public void setTAfter_Mass3(Float tAfter_Mass3) {
		TAfter_Mass3 = tAfter_Mass3;
	}

	public Double getCrush_Value3() {
		return Crush_Value3;
	}

	public void setCrush_Value3(Double crush_Value3) {
		Crush_Value3 = crush_Value3;
	}

	public Double getAvg_Crush_Value() {
		return Avg_Crush_Value;
	}

	public void setAvg_Crush_Value(Double avg_Crush_Value) {
		Avg_Crush_Value = avg_Crush_Value;
	}

}
