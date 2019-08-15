package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * @author Administrator
 *
 */
public class V_Cement_ProductionData extends ModelSupport{

	/**
	 * 水泥基础数据视图
	 */
	private static final long serialVersionUID = -3404648983637225952L;
	
	private int i_id;//主键
	private String str_Equipment_Name;//拌合机名称
	private int i_product;//产品Id
	private String str_prodPlan_No;//生产计划编号
	private String str_Prop_Code;//生产配比编码
	private String  str_proj_Pos ;//工程部位/用途
	private int i_org_Id;//组织 id
	private String Org_Name;//拌合站名
	private String  str_material_Name;//产品名称
	private String  str_material_Model;//产品型号
	private String str_product;//产品加型号
	private int i_equ_Id;//设备id
	private int i_cons_Mix_Id;//施工配合比Id
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
	private String str_construction_Unit;//施工单位
	private int i_valid_Flag;// 有效标识
	private int i_total_number;//总条数
	private int i_qualified_number;//合格数
	private int i_failures;//不合格数
	private String str_beginDate;//开始时间（查询）
	private String str_endDate;//结束时间（查询）
	private int serialNumber;//序列
	private int totalWeight;
	private String os;
	private String str_proj_Pos1;
	
	
	private int cementWeight;
	
	
	
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	public String getStr_proj_Pos1() {
		return str_proj_Pos1;
	}
	public void setStr_proj_Pos1(String str_proj_Pos1) {
		this.str_proj_Pos1 = str_proj_Pos1;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getCementWeight() {
		return cementWeight;
	}
	public void setCementWeight(int cementWeight) {
		this.cementWeight = cementWeight;
	}
	public int getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getStr_beginDate() {
		return str_beginDate;
	}
	public void setStr_beginDate(String str_beginDate) {
		this.str_beginDate = str_beginDate;
	}
	public String getStr_endDate() {
		return str_endDate;
	}
	public void setStr_endDate(String str_endDate) {
		this.str_endDate = str_endDate;
	}
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public String getStr_Equipment_Name() {
		return str_Equipment_Name;
	}
	public void setStr_Equipment_Name(String str_Equipment_Name) {
		this.str_Equipment_Name = str_Equipment_Name;
	}

	public int getI_product() {
		return i_product;
	}
	public void setI_product(int i_product) {
		this.i_product = i_product;
	}
	public String getStr_prodPlan_No() {
		return str_prodPlan_No;
	}
	public void setStr_prodPlan_No(String str_prodPlan_No) {
		this.str_prodPlan_No = str_prodPlan_No;
	}
	public String getStr_Prop_Code() {
		return str_Prop_Code;
	}
	public void setStr_Prop_Code(String str_Prop_Code) {
		this.str_Prop_Code = str_Prop_Code;
	}
	public String getStr_proj_Pos() {
		return str_proj_Pos;
	}
	public void setStr_proj_Pos(String str_proj_Pos) {
		this.str_proj_Pos = str_proj_Pos;
	}
	public int getI_org_Id() {
		return i_org_Id;
	}
	public void setI_org_Id(int i_org_Id) {
		this.i_org_Id = i_org_Id;
	}
	public String getStr_material_Name() {
		return str_material_Name;
	}
	public void setStr_material_Name(String str_material_Name) {
		this.str_material_Name = str_material_Name;
	}

	public String getStr_material_Model() {
		return str_material_Model;
	}
	public void setStr_material_Model(String str_material_Model) {
		this.str_material_Model = str_material_Model;
	}
	public String getStr_product() {
		return str_product;
	}
	public void setStr_product(String str_product) {
		this.str_product = str_product;
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
	public String getStr_construction_Unit() {
		return str_construction_Unit;
	}
	public void setStr_construction_Unit(String str_construction_Unit) {
		this.str_construction_Unit = str_construction_Unit;
	}
	public int getI_valid_Flag() {
		return i_valid_Flag;
	}
	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}
	public int getI_total_number() {
		return i_total_number;
	}
	public void setI_total_number(int i_total_number) {
		this.i_total_number = i_total_number;
	}
	public int getI_qualified_number() {
		return i_qualified_number;
	}
	public void setI_qualified_number(int i_qualified_number) {
		this.i_qualified_number = i_qualified_number;
	}
	public int getI_failures() {
		return i_failures;
	}
	public void setI_failures(int i_failures) {
		this.i_failures = i_failures;
	}
}
