package com.blindSample.model;

public class ShiYan21DetailEntity {
	private String SerialNumber;// 试验流水号
	private Float Sieve_Size = 0F; // 筛孔尺寸（mm）
	private Double Sub_Sieve_Resi1 = 0D; // 分计筛余（%）
	private Double Total_Sieve_Resi1 = 0D; // 累计筛余（%）
	private Double Pass_Rate1 = 0D; // 通过百分率（%）
	private Float Set_Max_Pass_Rate = 0F; // 上限
	private Double Avg_Pass_Rate = 0D; // 中值
	private Float Set_Main_Pass_Rate = 0F;// 下限
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public Float getSieve_Size() {
		return Sieve_Size;
	}
	public void setSieve_Size(Float sieve_Size) {
		Sieve_Size = sieve_Size;
	}
	public Double getSub_Sieve_Resi1() {
		return Sub_Sieve_Resi1;
	}
	public void setSub_Sieve_Resi1(Double sub_Sieve_Resi1) {
		Sub_Sieve_Resi1 = sub_Sieve_Resi1;
	}
	public Double getTotal_Sieve_Resi1() {
		return Total_Sieve_Resi1;
	}
	public void setTotal_Sieve_Resi1(Double total_Sieve_Resi1) {
		Total_Sieve_Resi1 = total_Sieve_Resi1;
	}
	public Double getPass_Rate1() {
		return Pass_Rate1;
	}
	public void setPass_Rate1(Double pass_Rate1) {
		Pass_Rate1 = pass_Rate1;
	}
	public Float getSet_Max_Pass_Rate() {
		return Set_Max_Pass_Rate;
	}
	public void setSet_Max_Pass_Rate(Float set_Max_Pass_Rate) {
		Set_Max_Pass_Rate = set_Max_Pass_Rate;
	}
	public Double getAvg_Pass_Rate() {
		return Avg_Pass_Rate;
	}
	public void setAvg_Pass_Rate(Double avg_Pass_Rate) {
		Avg_Pass_Rate = avg_Pass_Rate;
	}
	public Float getSet_Main_Pass_Rate() {
		return Set_Main_Pass_Rate;
	}
	public void setSet_Main_Pass_Rate(Float set_Main_Pass_Rate) {
		Set_Main_Pass_Rate = set_Main_Pass_Rate;
	}

}
