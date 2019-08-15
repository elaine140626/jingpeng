package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 水泥生产数据采集
 * 
 * @author Administrator
 *
 */
public class Cement_ProductionData extends ModelSupport {
	private static final long serialVersionUID = -8569871044239739107L;
	private int i_id;//
	private int i_org_Id;// 组织机构ID
	private int i_equ_Id;// 拌和机Id
	private int i_cons_Mix_Id;// 施工配合比ID
	private int i_bin_Relationship_Id;// 料仓对应关系ID
	private String str_collecti_Type;// 采集方式
	private String str_collect_Date;// 采集时间
	private double d_set_Cement1;// 水泥仓1设定值
	private double d_set_Cement2;// 水泥仓2设定值
	private double d_set_Cement3;// 水泥仓3设定值
	private double d_set_Cement4;// 水泥仓4设定值
	private double d_set_Aggregate1;// 骨料仓1设定值
	private double d_set_Aggregate6;// 骨料仓6设定值
	private double d_set_Aggregate5;// 骨料仓5设定值
	private double d_set_Aggregate4;// 骨料仓4设定值
	private double d_set_Aggregate3;// 骨料仓3设定值
	private double d_set_Aggregate2;// 骨料仓2设定值
	private double d_set_Water;// 水仓设定值
	private double d_set_Admixture1;// 外掺剂1仓设定值
	private double d_set_Admixture2;// 外掺剂2仓设定值
	private int i_mix_Time;// 拌和时间
	private double d_weight_Cement1;// 水泥仓1采集值
	private double d_weight_Cement2;// 水泥仓2采集值
	private double d_weight_Cement3;// 水泥仓3采集值
	private double d_weight_Cement4;// 水泥仓4采集值
	private double d_weight_Aggregate1;// 骨料仓1采集值
	private double d_weight_Aggregate2;// 骨料仓2采集值
	private double d_weight_Aggregate3;// 骨料仓3采集值
	private double d_weight_Aggregate4;// 骨料仓4采集值
	private double d_weight_Aggregate5;// 骨料仓5采集值
	private double d_weight_Aggregate6;// 骨料仓6采集值
	private double d_weight_Water;// 水仓采集值
	private double d_weight_Admixture1;// 外掺剂1仓采集值
	private double d_weight_Admixture2;// 外掺剂2仓采集值
	private double d_total_Weight;// 拌和总量
	private String str_job_Location;// 施工地点
	private String str_watering_Site;// 浇灌部位
	private int i_analysis_Results;// 数据分析结果
	private int i_analysis_Results_Cement;// 水泥仓数据分析结果
	private int i_analysis_Results_Aggregate;// 骨料仓数据分析结果
	private int i_analysis_Results_Water;// 水仓数据分析结果
	private int i_analysis_Results_Admixture;// 外掺剂仓数据分析结果
	private int i_upload;// 上传标识
	private int i_alarm_Info_Id;// 预警处理信息ID
	private String str_create_Date;// 创建时间
	private int i_analysis_Results_Time;// 拌和时间分析结果
	private int i_valid_Flag;// 有效标识

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_org_Id() {
		return i_org_Id;
	}

	public void setI_org_Id(int i_org_Id) {
		this.i_org_Id = i_org_Id;
	}

	public int getI_equ_Id() {
		return i_equ_Id;
	}

	public void setI_equ_Id(int i_equ_Id) {
		this.i_equ_Id = i_equ_Id;
	}

	public int getI_cons_Mix_Id() {
		return i_cons_Mix_Id;
	}

	public void setI_cons_Mix_Id(int i_cons_Mix_Id) {
		this.i_cons_Mix_Id = i_cons_Mix_Id;
	}

	public int getI_bin_Relationship_Id() {
		return i_bin_Relationship_Id;
	}

	public void setI_bin_Relationship_Id(int i_bin_Relationship_Id) {
		this.i_bin_Relationship_Id = i_bin_Relationship_Id;
	}

	public String getStr_collecti_Type() {
		return str_collecti_Type;
	}

	public void setStr_collecti_Type(String str_collecti_Type) {
		this.str_collecti_Type = str_collecti_Type;
	}

	public String getStr_collect_Date() {
		return str_collect_Date;
	}

	public void setStr_collect_Date(String str_collect_Date) {
		this.str_collect_Date = str_collect_Date;
	}

	public double getD_set_Cement1() {
		return d_set_Cement1;
	}

	public void setD_set_Cement1(double d_set_Cement1) {
		this.d_set_Cement1 = d_set_Cement1;
	}

	public double getD_set_Cement2() {
		return d_set_Cement2;
	}

	public void setD_set_Cement2(double d_set_Cement2) {
		this.d_set_Cement2 = d_set_Cement2;
	}

	public double getD_set_Cement3() {
		return d_set_Cement3;
	}

	public void setD_set_Cement3(double d_set_Cement3) {
		this.d_set_Cement3 = d_set_Cement3;
	}

	public double getD_set_Cement4() {
		return d_set_Cement4;
	}

	public void setD_set_Cement4(double d_set_Cement4) {
		this.d_set_Cement4 = d_set_Cement4;
	}

	public double getD_set_Aggregate1() {
		return d_set_Aggregate1;
	}

	public void setD_set_Aggregate1(double d_set_Aggregate1) {
		this.d_set_Aggregate1 = d_set_Aggregate1;
	}

	public double getD_set_Aggregate6() {
		return d_set_Aggregate6;
	}

	public void setD_set_Aggregate6(double d_set_Aggregate6) {
		this.d_set_Aggregate6 = d_set_Aggregate6;
	}

	public double getD_set_Aggregate5() {
		return d_set_Aggregate5;
	}

	public void setD_set_Aggregate5(double d_set_Aggregate5) {
		this.d_set_Aggregate5 = d_set_Aggregate5;
	}

	public double getD_set_Aggregate4() {
		return d_set_Aggregate4;
	}

	public void setD_set_Aggregate4(double d_set_Aggregate4) {
		this.d_set_Aggregate4 = d_set_Aggregate4;
	}

	public double getD_set_Aggregate3() {
		return d_set_Aggregate3;
	}

	public void setD_set_Aggregate3(double d_set_Aggregate3) {
		this.d_set_Aggregate3 = d_set_Aggregate3;
	}

	public double getD_set_Aggregate2() {
		return d_set_Aggregate2;
	}

	public void setD_set_Aggregate2(double d_set_Aggregate2) {
		this.d_set_Aggregate2 = d_set_Aggregate2;
	}

	public double getD_set_Water() {
		return d_set_Water;
	}

	public void setD_set_Water(double d_set_Water) {
		this.d_set_Water = d_set_Water;
	}

	public double getD_set_Admixture1() {
		return d_set_Admixture1;
	}

	public void setD_set_Admixture1(double d_set_Admixture1) {
		this.d_set_Admixture1 = d_set_Admixture1;
	}

	public double getD_set_Admixture2() {
		return d_set_Admixture2;
	}

	public void setD_set_Admixture2(double d_set_Admixture2) {
		this.d_set_Admixture2 = d_set_Admixture2;
	}

	public int getI_mix_Time() {
		return i_mix_Time;
	}

	public void setI_mix_Time(int i_mix_Time) {
		this.i_mix_Time = i_mix_Time;
	}

	public double getD_weight_Cement1() {
		return d_weight_Cement1;
	}

	public void setD_weight_Cement1(double d_weight_Cement1) {
		this.d_weight_Cement1 = d_weight_Cement1;
	}

	public double getD_weight_Cement2() {
		return d_weight_Cement2;
	}

	public void setD_weight_Cement2(double d_weight_Cement2) {
		this.d_weight_Cement2 = d_weight_Cement2;
	}

	public double getD_weight_Cement3() {
		return d_weight_Cement3;
	}

	public void setD_weight_Cement3(double d_weight_Cement3) {
		this.d_weight_Cement3 = d_weight_Cement3;
	}

	public double getD_weight_Cement4() {
		return d_weight_Cement4;
	}

	public void setD_weight_Cement4(double d_weight_Cement4) {
		this.d_weight_Cement4 = d_weight_Cement4;
	}

	public double getD_weight_Aggregate1() {
		return d_weight_Aggregate1;
	}

	public void setD_weight_Aggregate1(double d_weight_Aggregate1) {
		this.d_weight_Aggregate1 = d_weight_Aggregate1;
	}

	public double getD_weight_Aggregate2() {
		return d_weight_Aggregate2;
	}

	public void setD_weight_Aggregate2(double d_weight_Aggregate2) {
		this.d_weight_Aggregate2 = d_weight_Aggregate2;
	}

	public double getD_weight_Aggregate3() {
		return d_weight_Aggregate3;
	}

	public void setD_weight_Aggregate3(double d_weight_Aggregate3) {
		this.d_weight_Aggregate3 = d_weight_Aggregate3;
	}

	public double getD_weight_Aggregate4() {
		return d_weight_Aggregate4;
	}

	public void setD_weight_Aggregate4(double d_weight_Aggregate4) {
		this.d_weight_Aggregate4 = d_weight_Aggregate4;
	}

	public double getD_weight_Aggregate5() {
		return d_weight_Aggregate5;
	}

	public void setD_weight_Aggregate5(double d_weight_Aggregate5) {
		this.d_weight_Aggregate5 = d_weight_Aggregate5;
	}

	public double getD_weight_Aggregate6() {
		return d_weight_Aggregate6;
	}

	public void setD_weight_Aggregate6(double d_weight_Aggregate6) {
		this.d_weight_Aggregate6 = d_weight_Aggregate6;
	}

	public double getD_weight_Water() {
		return d_weight_Water;
	}

	public void setD_weight_Water(double d_weight_Water) {
		this.d_weight_Water = d_weight_Water;
	}

	public double getD_weight_Admixture1() {
		return d_weight_Admixture1;
	}

	public void setD_weight_Admixture1(double d_weight_Admixture1) {
		this.d_weight_Admixture1 = d_weight_Admixture1;
	}

	public double getD_weight_Admixture2() {
		return d_weight_Admixture2;
	}

	public void setD_weight_Admixture2(double d_weight_Admixture2) {
		this.d_weight_Admixture2 = d_weight_Admixture2;
	}

	public double getD_total_Weight() {
		return d_total_Weight;
	}

	public void setD_total_Weight(double d_total_Weight) {
		this.d_total_Weight = d_total_Weight;
	}

	public String getStr_job_Location() {
		return str_job_Location;
	}

	public void setStr_job_Location(String str_job_Location) {
		this.str_job_Location = str_job_Location;
	}

	public String getStr_watering_Site() {
		return str_watering_Site;
	}

	public void setStr_watering_Site(String str_watering_Site) {
		this.str_watering_Site = str_watering_Site;
	}

	public int getI_analysis_Results() {
		return i_analysis_Results;
	}

	public void setI_analysis_Results(int i_analysis_Results) {
		this.i_analysis_Results = i_analysis_Results;
	}

	public int getI_analysis_Results_Cement() {
		return i_analysis_Results_Cement;
	}

	public void setI_analysis_Results_Cement(int i_analysis_Results_Cement) {
		this.i_analysis_Results_Cement = i_analysis_Results_Cement;
	}

	public int getI_analysis_Results_Aggregate() {
		return i_analysis_Results_Aggregate;
	}

	public void setI_analysis_Results_Aggregate(int i_analysis_Results_Aggregate) {
		this.i_analysis_Results_Aggregate = i_analysis_Results_Aggregate;
	}

	public int getI_analysis_Results_Water() {
		return i_analysis_Results_Water;
	}

	public void setI_analysis_Results_Water(int i_analysis_Results_Water) {
		this.i_analysis_Results_Water = i_analysis_Results_Water;
	}

	public int getI_analysis_Results_Admixture() {
		return i_analysis_Results_Admixture;
	}

	public void setI_analysis_Results_Admixture(int i_analysis_Results_Admixture) {
		this.i_analysis_Results_Admixture = i_analysis_Results_Admixture;
	}

	public int getI_upload() {
		return i_upload;
	}

	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}

	public int getI_alarm_Info_Id() {
		return i_alarm_Info_Id;
	}

	public void setI_alarm_Info_Id(int i_alarm_Info_Id) {
		this.i_alarm_Info_Id = i_alarm_Info_Id;
	}

	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
	}

	public int getI_analysis_Results_Time() {
		return i_analysis_Results_Time;
	}

	public void setI_analysis_Results_Time(int i_analysis_Results_Time) {
		this.i_analysis_Results_Time = i_analysis_Results_Time;
	}

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}

}
