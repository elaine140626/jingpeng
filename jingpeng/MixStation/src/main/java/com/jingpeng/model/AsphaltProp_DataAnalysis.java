package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 沥青生产数据分析
 * 
 * @author Administrator
 *
 */
public class AsphaltProp_DataAnalysis extends ModelSupport {
	private static final long serialVersionUID = -7896681581109808029L;
	private int i_id;
	private int i_pro_Id;// 生产数据Id
	private String str_data_Type;// 数据类型
	private double d_no1;// 1号仓
	private double d_no2;// 2号仓
	private double d_no3;// 3号仓
	private double d_no4;// 4号仓
	private double d_no5;// 5号仓
	private double d_no6;// 6号仓
	private double d_no7;// 7号仓
	private double d_no8;// 8号仓
	private double d_hotPowder;// 热粉仓
	private double d_coldPowder;// 冷粉仓
	private double d_totalPowder;// 总粉仓
	private double d_asphalt;// 沥青
	private double d_temperature;// 温度
	private double d_admixture1;// 一号外掺剂
	private double d_admixture2;// 二号外掺剂
	private int i_valid_Flag;// 有效标识
	private int i_upload;// 上传标识

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_pro_Id() {
		return i_pro_Id;
	}

	public void setI_pro_Id(int i_pro_Id) {
		this.i_pro_Id = i_pro_Id;
	}

	public String getStr_data_Type() {
		return str_data_Type;
	}

	public void setStr_data_Type(String str_data_Type) {
		this.str_data_Type = str_data_Type;
	}

	public double getD_no1() {
		return d_no1;
	}

	public void setD_no1(double d_no1) {
		this.d_no1 = d_no1;
	}

	public double getD_no2() {
		return d_no2;
	}

	public void setD_no2(double d_no2) {
		this.d_no2 = d_no2;
	}

	public double getD_no3() {
		return d_no3;
	}

	public void setD_no3(double d_no3) {
		this.d_no3 = d_no3;
	}

	public double getD_no4() {
		return d_no4;
	}

	public void setD_no4(double d_no4) {
		this.d_no4 = d_no4;
	}

	public double getD_no5() {
		return d_no5;
	}

	public void setD_no5(double d_no5) {
		this.d_no5 = d_no5;
	}

	public double getD_no6() {
		return d_no6;
	}

	public void setD_no6(double d_no6) {
		this.d_no6 = d_no6;
	}

	public double getD_no7() {
		return d_no7;
	}

	public void setD_no7(double d_no7) {
		this.d_no7 = d_no7;
	}

	public double getD_no8() {
		return d_no8;
	}

	public void setD_no8(double d_no8) {
		this.d_no8 = d_no8;
	}

	public double getD_hotPowder() {
		return d_hotPowder;
	}

	public void setD_hotPowder(double d_hotPowder) {
		this.d_hotPowder = d_hotPowder;
	}

	public double getD_coldPowder() {
		return d_coldPowder;
	}

	public void setD_coldPowder(double d_coldPowder) {
		this.d_coldPowder = d_coldPowder;
	}

	public double getD_totalPowder() {
		return d_totalPowder;
	}

	public void setD_totalPowder(double d_totalPowder) {
		this.d_totalPowder = d_totalPowder;
	}

	public double getD_asphalt() {
		return d_asphalt;
	}

	public void setD_asphalt(double d_asphalt) {
		this.d_asphalt = d_asphalt;
	}

	public double getD_temperature() {
		return d_temperature;
	}

	public void setD_temperature(double d_temperature) {
		this.d_temperature = d_temperature;
	}

	public double getD_admixture1() {
		return d_admixture1;
	}

	public void setD_admixture1(double d_admixture1) {
		this.d_admixture1 = d_admixture1;
	}

	public double getD_admixture2() {
		return d_admixture2;
	}

	public void setD_admixture2(double d_admixture2) {
		this.d_admixture2 = d_admixture2;
	}

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}

	public int getI_upload() {
		return i_upload;
	}

	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}

}
