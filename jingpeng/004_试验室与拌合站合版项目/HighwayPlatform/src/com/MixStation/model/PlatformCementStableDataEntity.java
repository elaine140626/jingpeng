package com.MixStation.model;
/**
 * 
 * @Title 水泥生产
 * @author ygt
 * @date 2018年9月20日
 */
public class PlatformCementStableDataEntity {
	private int serialNumber;// 序号
	private String I_Id;
	private String Org_ID;//组织机构ID
	private String Product_ID;//产品Id
	private String Equ_ID;//拌和机Id
	private String i_cons_Mix_ID;//施工配合比ID
	private String ProdPlan_No;//生产计划编号
	private String str_collect_Date;//采集时间
	private String d_set_Cement1;//水泥仓1设定值
	private String d_set_Cement2;//水泥仓2设定值
	private String d_set_Cement3;//水泥仓3设定值
	private String d_set_Cement4;//水泥仓4设定值
	private String d_weight_Cement1;//水泥仓1采集值
	private String d_weight_Cement2;//水泥仓2采集值
	private String d_weight_Cement3;//水泥仓3采集值
	private String d_weight_Cement4;//水泥仓4采集值
	private String d_set_Aggregate1;//骨料仓1设定值
	private String d_set_Aggregate2;//骨料仓2设定值
	private String d_set_Aggregate3;//骨料仓3设定值
	private String d_set_Aggregate4;//骨料仓4设定值
	private String d_set_Aggregate5;//骨料仓5设定值
	private String d_set_Aggregate6;//骨料仓6设定值
	private String d_weight_Aggregate1;//骨料仓1采集值
	private String d_weight_Aggregate2;//骨料仓2采集值
	private String d_weight_Aggregate3;//骨料仓3采集值
	private String d_weight_Aggregate4;//骨料仓4采集值
	private String d_weight_Aggregate5;//骨料仓5采集值
	private String d_weight_Aggregate6;//骨料仓6采集值
	private String d_set_Water;//水仓设定值
	private String d_weight_Water;//水仓采集值
	private String d_set_Admixture1;//外掺剂1仓设定值
	private String d_set_Admixture2;//外掺剂2仓设定值
	private String d_weight_Admixture1;//外掺剂1仓采集值
	private String d_weight_Admixture2;//外掺剂2仓采集值
	private String Analysis_Result;//数据分析结果
	private String d_total_Weight;//拌和总量
	private String i_mix_Time; //拌和时间
	private String Temperature_Meter;
	private String Construction_Unit;//施工地点
	private String Proj_Pos;//工程部位
	private String str_equipment_Name;//设备名称
	private String str_equipment_Type;
	private String productInfo;//产品名称
	private String Proportion_Code;//生产配合比编号
	private String Grade_Code;//级配编号
	private String Valid_Flag;
	private String Org_Name;//拌和站名称
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getI_Id() {
		return I_Id;
	}
	public void setI_Id(String i_Id) {
		I_Id = i_Id;
	}
	public String getOrg_ID() {
		return Org_ID;
	}
	public void setOrg_ID(String org_ID) {
		Org_ID = org_ID;
	}
	public String getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}
	public String getEqu_ID() {
		return Equ_ID;
	}
	public void setEqu_ID(String equ_ID) {
		Equ_ID = equ_ID;
	}
	public String getI_cons_Mix_ID() {
		return i_cons_Mix_ID;
	}
	public void setI_cons_Mix_ID(String i_cons_Mix_ID) {
		this.i_cons_Mix_ID = i_cons_Mix_ID;
	}
	public String getProdPlan_No() {
		return ProdPlan_No;
	}
	public void setProdPlan_No(String prodPlan_No) {
		ProdPlan_No = prodPlan_No;
	}
	public String getStr_collect_Date() {
		return str_collect_Date;
	}
	public void setStr_collect_Date(String str_collect_Date) {
		this.str_collect_Date = str_collect_Date;
	}
	public String getD_set_Cement1() {
		return d_set_Cement1;
	}
	public void setD_set_Cement1(String d_set_Cement1) {
		this.d_set_Cement1 = d_set_Cement1;
	}
	public String getD_set_Cement2() {
		return d_set_Cement2;
	}
	public void setD_set_Cement2(String d_set_Cement2) {
		this.d_set_Cement2 = d_set_Cement2;
	}
	public String getD_set_Cement3() {
		return d_set_Cement3;
	}
	public void setD_set_Cement3(String d_set_Cement3) {
		this.d_set_Cement3 = d_set_Cement3;
	}
	public String getD_set_Cement4() {
		return d_set_Cement4;
	}
	public void setD_set_Cement4(String d_set_Cement4) {
		this.d_set_Cement4 = d_set_Cement4;
	}
	public String getD_weight_Cement1() {
		return d_weight_Cement1;
	}
	public void setD_weight_Cement1(String d_weight_Cement1) {
		this.d_weight_Cement1 = d_weight_Cement1;
	}
	public String getD_weight_Cement2() {
		return d_weight_Cement2;
	}
	public void setD_weight_Cement2(String d_weight_Cement2) {
		this.d_weight_Cement2 = d_weight_Cement2;
	}
	public String getD_weight_Cement3() {
		return d_weight_Cement3;
	}
	public void setD_weight_Cement3(String d_weight_Cement3) {
		this.d_weight_Cement3 = d_weight_Cement3;
	}
	public String getD_weight_Cement4() {
		return d_weight_Cement4;
	}
	public void setD_weight_Cement4(String d_weight_Cement4) {
		this.d_weight_Cement4 = d_weight_Cement4;
	}
	public String getD_set_Aggregate1() {
		return d_set_Aggregate1;
	}
	public void setD_set_Aggregate1(String d_set_Aggregate1) {
		this.d_set_Aggregate1 = d_set_Aggregate1;
	}
	public String getD_set_Aggregate2() {
		return d_set_Aggregate2;
	}
	public void setD_set_Aggregate2(String d_set_Aggregate2) {
		this.d_set_Aggregate2 = d_set_Aggregate2;
	}
	public String getD_set_Aggregate3() {
		return d_set_Aggregate3;
	}
	public void setD_set_Aggregate3(String d_set_Aggregate3) {
		this.d_set_Aggregate3 = d_set_Aggregate3;
	}
	public String getD_set_Aggregate4() {
		return d_set_Aggregate4;
	}
	public void setD_set_Aggregate4(String d_set_Aggregate4) {
		this.d_set_Aggregate4 = d_set_Aggregate4;
	}
	public String getD_set_Aggregate5() {
		return d_set_Aggregate5;
	}
	public void setD_set_Aggregate5(String d_set_Aggregate5) {
		this.d_set_Aggregate5 = d_set_Aggregate5;
	}
	public String getD_set_Aggregate6() {
		return d_set_Aggregate6;
	}
	public void setD_set_Aggregate6(String d_set_Aggregate6) {
		this.d_set_Aggregate6 = d_set_Aggregate6;
	}
	public String getD_weight_Aggregate1() {
		return d_weight_Aggregate1;
	}
	public void setD_weight_Aggregate1(String d_weight_Aggregate1) {
		this.d_weight_Aggregate1 = d_weight_Aggregate1;
	}
	public String getD_weight_Aggregate2() {
		return d_weight_Aggregate2;
	}
	public void setD_weight_Aggregate2(String d_weight_Aggregate2) {
		this.d_weight_Aggregate2 = d_weight_Aggregate2;
	}
	public String getD_weight_Aggregate3() {
		return d_weight_Aggregate3;
	}
	public void setD_weight_Aggregate3(String d_weight_Aggregate3) {
		this.d_weight_Aggregate3 = d_weight_Aggregate3;
	}
	public String getD_weight_Aggregate4() {
		return d_weight_Aggregate4;
	}
	public void setD_weight_Aggregate4(String d_weight_Aggregate4) {
		this.d_weight_Aggregate4 = d_weight_Aggregate4;
	}
	public String getD_weight_Aggregate5() {
		return d_weight_Aggregate5;
	}
	public void setD_weight_Aggregate5(String d_weight_Aggregate5) {
		this.d_weight_Aggregate5 = d_weight_Aggregate5;
	}
	public String getD_weight_Aggregate6() {
		return d_weight_Aggregate6;
	}
	public void setD_weight_Aggregate6(String d_weight_Aggregate6) {
		this.d_weight_Aggregate6 = d_weight_Aggregate6;
	}
	public String getD_set_Water() {
		return d_set_Water;
	}
	public void setD_set_Water(String d_set_Water) {
		this.d_set_Water = d_set_Water;
	}
	public String getD_weight_Water() {
		return d_weight_Water;
	}
	public void setD_weight_Water(String d_weight_Water) {
		this.d_weight_Water = d_weight_Water;
	}
	public String getD_set_Admixture1() {
		return d_set_Admixture1;
	}
	public void setD_set_Admixture1(String d_set_Admixture1) {
		this.d_set_Admixture1 = d_set_Admixture1;
	}
	public String getD_set_Admixture2() {
		return d_set_Admixture2;
	}
	public void setD_set_Admixture2(String d_set_Admixture2) {
		this.d_set_Admixture2 = d_set_Admixture2;
	}
	public String getD_weight_Admixture1() {
		return d_weight_Admixture1;
	}
	public void setD_weight_Admixture1(String d_weight_Admixture1) {
		this.d_weight_Admixture1 = d_weight_Admixture1;
	}
	public String getD_weight_Admixture2() {
		return d_weight_Admixture2;
	}
	public void setD_weight_Admixture2(String d_weight_Admixture2) {
		this.d_weight_Admixture2 = d_weight_Admixture2;
	}
	public String getAnalysis_Result() {
		return Analysis_Result;
	}
	public void setAnalysis_Result(String analysis_Result) {
		Analysis_Result = analysis_Result;
	}
	public String getD_total_Weight() {
		return d_total_Weight;
	}
	public void setD_total_Weight(String d_total_Weight) {
		this.d_total_Weight = d_total_Weight;
	}
	public String getI_mix_Time() {
		return i_mix_Time;
	}
	public void setI_mix_Time(String i_mix_Time) {
		this.i_mix_Time = i_mix_Time;
	}
	public String getTemperature_Meter() {
		return Temperature_Meter;
	}
	public void setTemperature_Meter(String temperature_Meter) {
		Temperature_Meter = temperature_Meter;
	}
	public String getConstruction_Unit() {
		return Construction_Unit;
	}
	public void setConstruction_Unit(String construction_Unit) {
		Construction_Unit = construction_Unit;
	}
	public String getProj_Pos() {
		return Proj_Pos;
	}
	public void setProj_Pos(String proj_Pos) {
		Proj_Pos = proj_Pos;
	}
	public String getStr_equipment_Name() {
		return str_equipment_Name;
	}
	public void setStr_equipment_Name(String str_equipment_Name) {
		this.str_equipment_Name = str_equipment_Name;
	}
	public String getStr_equipment_Type() {
		return str_equipment_Type;
	}
	public void setStr_equipment_Type(String str_equipment_Type) {
		this.str_equipment_Type = str_equipment_Type;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getProportion_Code() {
		return Proportion_Code;
	}
	public void setProportion_Code(String proportion_Code) {
		Proportion_Code = proportion_Code;
	}
	public String getGrade_Code() {
		return Grade_Code;
	}
	public void setGrade_Code(String grade_Code) {
		Grade_Code = grade_Code;
	}
	public String getValid_Flag() {
		return Valid_Flag;
	}
	public void setValid_Flag(String valid_Flag) {
		Valid_Flag = valid_Flag;
	}
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	
}
