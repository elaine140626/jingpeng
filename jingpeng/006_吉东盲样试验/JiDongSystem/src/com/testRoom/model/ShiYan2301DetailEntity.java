package com.testRoom.model;

public class ShiYan2301DetailEntity {
	private String SerialNumber;// 试验流水号
	private int Test_Serial; // 试验序号
	private Float Samp_Mass1 = 0F; // 试样总质量(g)1
	private Float In_Samp_Mass1 = 0F; // 试样中所含针片状颗粒的总质量(g)1
	private Double Elongated_Particles1 = 0D; // 针片状颗粒含量(%)单值1
	private Float Samp_Mass2 = 0F; // 试样总质量(g)2
	private Float In_Samp_Mass2 = 0F; // 试样中所含针片状颗粒的总质量(g)2
	private Double Elongated_Particles2 = 0D; // 针片状颗粒含量(%)单值2
	private Float Samp_Mass3 = 0F; // 试样总质量(g)3
	private Float In_Samp_Mass3 = 0F; // 试样中所含针片状颗粒的总质量(g)3
	private Double Elongated_Particles3 = 0D; // 针片状颗粒含量(%)单值3
	private Double Avg_Elon_Part = 0D; // 平均针片状颗粒含量

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public int getTest_Serial() {
		return Test_Serial;
	}

	public void setTest_Serial(int test_Serial) {
		Test_Serial = test_Serial;
	}

	public Float getSamp_Mass1() {
		return Samp_Mass1;
	}

	public void setSamp_Mass1(Float samp_Mass1) {
		Samp_Mass1 = samp_Mass1;
	}

	public Float getIn_Samp_Mass1() {
		return In_Samp_Mass1;
	}

	public void setIn_Samp_Mass1(Float in_Samp_Mass1) {
		In_Samp_Mass1 = in_Samp_Mass1;
	}

	public Double getElongated_Particles1() {
		return Elongated_Particles1;
	}

	public void setElongated_Particles1(Double elongated_Particles1) {
		Elongated_Particles1 = elongated_Particles1;
	}

	public Float getSamp_Mass2() {
		return Samp_Mass2;
	}

	public void setSamp_Mass2(Float samp_Mass2) {
		Samp_Mass2 = samp_Mass2;
	}

	public Float getIn_Samp_Mass2() {
		return In_Samp_Mass2;
	}

	public void setIn_Samp_Mass2(Float in_Samp_Mass2) {
		In_Samp_Mass2 = in_Samp_Mass2;
	}

	public Double getElongated_Particles2() {
		return Elongated_Particles2;
	}

	public void setElongated_Particles2(Double elongated_Particles2) {
		Elongated_Particles2 = elongated_Particles2;
	}

	public Float getSamp_Mass3() {
		return Samp_Mass3;
	}

	public void setSamp_Mass3(Float samp_Mass3) {
		Samp_Mass3 = samp_Mass3;
	}

	public Float getIn_Samp_Mass3() {
		return In_Samp_Mass3;
	}

	public void setIn_Samp_Mass3(Float in_Samp_Mass3) {
		In_Samp_Mass3 = in_Samp_Mass3;
	}

	public Double getElongated_Particles3() {
		return Elongated_Particles3;
	}

	public void setElongated_Particles3(Double elongated_Particles3) {
		Elongated_Particles3 = elongated_Particles3;
	}

	public Double getAvg_Elon_Part() {
		return Avg_Elon_Part;
	}

	public void setAvg_Elon_Part(Double avg_Elon_Part) {
		Avg_Elon_Part = avg_Elon_Part;
	}

}
