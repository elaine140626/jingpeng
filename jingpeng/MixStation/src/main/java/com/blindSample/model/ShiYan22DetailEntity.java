package com.blindSample.model;

public class ShiYan22DetailEntity {
	private String SerialNumber;// 试验流水号
	private Float BSmpl_Mass1 = 0F; // 试验前烘干试样质量(g)1
	private Float ASmpl_Mass1 = 0F; // 试验后烘干试样质量(g)1
	private Double Sediment_Percentage1 = 0D; // 含泥量/小于0.075mm颗粒含量1单值
	private Float BSmpl_Mass2 = 0F; // 试验前烘干试样质量(g)2
	private Float ASmpl_Mass2 = 0F; // 试验后烘干试样质量(g)2
	private Double Sediment_Percentage2 = 0D; // 含泥量/小于0.075mm颗粒含量2单值
	private Double Avg_Sedi_Perc = 0D; // 含泥量/小于0.075mm颗粒含量平均值
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public Float getBSmpl_Mass1() {
		return BSmpl_Mass1;
	}
	public void setBSmpl_Mass1(Float bSmpl_Mass1) {
		BSmpl_Mass1 = bSmpl_Mass1;
	}
	public Float getASmpl_Mass1() {
		return ASmpl_Mass1;
	}
	public void setASmpl_Mass1(Float aSmpl_Mass1) {
		ASmpl_Mass1 = aSmpl_Mass1;
	}
	public Double getSediment_Percentage1() {
		return Sediment_Percentage1;
	}
	public void setSediment_Percentage1(Double sediment_Percentage1) {
		Sediment_Percentage1 = sediment_Percentage1;
	}
	public Float getBSmpl_Mass2() {
		return BSmpl_Mass2;
	}
	public void setBSmpl_Mass2(Float bSmpl_Mass2) {
		BSmpl_Mass2 = bSmpl_Mass2;
	}
	public Float getASmpl_Mass2() {
		return ASmpl_Mass2;
	}
	public void setASmpl_Mass2(Float aSmpl_Mass2) {
		ASmpl_Mass2 = aSmpl_Mass2;
	}
	public Double getSediment_Percentage2() {
		return Sediment_Percentage2;
	}
	public void setSediment_Percentage2(Double sediment_Percentage2) {
		Sediment_Percentage2 = sediment_Percentage2;
	}
	public Double getAvg_Sedi_Perc() {
		return Avg_Sedi_Perc;
	}
	public void setAvg_Sedi_Perc(Double avg_Sedi_Perc) {
		Avg_Sedi_Perc = avg_Sedi_Perc;
	}	
}
