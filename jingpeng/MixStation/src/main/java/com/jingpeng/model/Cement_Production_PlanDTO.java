package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 水泥生产计划
 * 
 * @author Administrator
 *
 */
public class Cement_Production_PlanDTO extends ModelSupport {
	private static final long serialVersionUID = -2475570604133432890L;

	private int i_id; // 自增长ID

	private int i_org_id; // 组织机构ID

	private String str_plan_No; // 生产计划编号

	private String str_startTime; // 生产计划开始时间

	private int i_equ_Id;// 拌合机ID

	private int i_product_Id; // 产品Id

	private int i_consProp_Id; // 施工配比ID

	private String str_proj_Pos; // 施工地点

	private String str_construction_Unit; // 施工单位

	private double d_scheduled_Production; // 计划生产量
	
	private int i_bunkerCor_Id;//料仓对应关系id

	private int i_isCancel; // 计划是否作废

	private String str_cancel_Time; // 作废时间

	private String str_invalid_Person; // 作废人

	private String str_cancel_Reason; // 作废原因

	private String str_remarks; // 备注

	private String str_operator; // 创建人

	private String str_create_Date; // 创建日期

	private String str_modifier; // 修改人

	private String str_modify_Date; // 修改日期

	private int i_valid_Flag; // 有效标识
	
	private String str_equipment_Name;//版和设备名称
	
	private String operate;//操作
	
	private int serialNumber;//序号
	
	private String  str_Prop_Code; // 施工配比编号
	
	private String  str_bunker_Code;//料仓编号
	
	private String str_Watering_Site;//浇灌部位
	
	private String str_material_Name;
	
	private String str_material_Model;//产品型号名称
	
	private String Proportion_Code;
	private String Material_Model;//型号
	
	private String  Material_Name;

	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProportion_Code() {
		return Proportion_Code;
	}

	public void setProportion_Code(String proportion_Code) {
		Proportion_Code = proportion_Code;
	}

	public String getStr_Prop_Code() {
		return str_Prop_Code;
	}

	public void setStr_Prop_Code(String str_Prop_Code) {
		this.str_Prop_Code = str_Prop_Code;
	}

	public String getMaterial_Name() {
		return Material_Name;
	}

	public void setMaterial_Name(String material_Name) {
		Material_Name = material_Name;
	}

	public String getMaterial_Model() {
		return Material_Model;
	}

	public void setMaterial_Model(String material_Model) {
		Material_Model = material_Model;
	}

	public String getStr_Watering_Site() {
		return str_Watering_Site;
	}

	public void setStr_Watering_Site(String str_Watering_Site) {
		this.str_Watering_Site = str_Watering_Site;
	}

	public String getStr_bunker_Code() {
		return str_bunker_Code;
	}

	public void setStr_bunker_Code(String str_bunker_Code) {
		this.str_bunker_Code = str_bunker_Code;
	}

	public String getStr_equipment_Name() {
		return str_equipment_Name;
	}

	public void setStr_equipment_Name(String str_equipment_Name) {
		this.str_equipment_Name = str_equipment_Name;
	}

	
	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_org_id() {
		return i_org_id;
	}

	public void setI_org_id(int i_org_id) {
		this.i_org_id = i_org_id;
	}

	public String getStr_plan_No() {
		return str_plan_No;
	}

	public void setStr_plan_No(String str_plan_No) {
		this.str_plan_No = str_plan_No;
	}

	public String getStr_startTime() {
		return str_startTime;
	}

	public void setStr_startTime(String str_startTime) {
		this.str_startTime = str_startTime;
	}

	public int getI_equ_Id() {
		return i_equ_Id;
	}

	public void setI_equ_Id(int i_equ_Id) {
		this.i_equ_Id = i_equ_Id;
	}

	public int getI_product_Id() {
		return i_product_Id;
	}

	public void setI_product_Id(int i_product_Id) {
		this.i_product_Id = i_product_Id;
	}

	public int getI_consProp_Id() {
		return i_consProp_Id;
	}

	public void setI_consProp_Id(int i_consProp_Id) {
		this.i_consProp_Id = i_consProp_Id;
	}

	public String getStr_proj_Pos() {
		return str_proj_Pos;
	}

	public void setStr_proj_Pos(String str_proj_Pos) {
		this.str_proj_Pos = str_proj_Pos;
	}

	public String getStr_construction_Unit() {
		return str_construction_Unit;
	}

	public void setStr_construction_Unit(String str_construction_Unit) {
		this.str_construction_Unit = str_construction_Unit;
	}

	public double getD_scheduled_Production() {
		return d_scheduled_Production;
	}

	public void setD_scheduled_Production(double d_scheduled_Production) {
		this.d_scheduled_Production = d_scheduled_Production;
	}

	public int getI_isCancel() {
		return i_isCancel;
	}

	public void setI_isCancel(int i_isCancel) {
		this.i_isCancel = i_isCancel;
	}

	public String getStr_cancel_Time() {
		return str_cancel_Time;
	}

	public void setStr_cancel_Time(String str_cancel_Time) {
		this.str_cancel_Time = str_cancel_Time;
	}

	public String getStr_invalid_Person() {
		return str_invalid_Person;
	}

	public void setStr_invalid_Person(String str_invalid_Person) {
		this.str_invalid_Person = str_invalid_Person;
	}

	public String getStr_cancel_Reason() {
		return str_cancel_Reason;
	}

	public void setStr_cancel_Reason(String str_cancel_Reason) {
		this.str_cancel_Reason = str_cancel_Reason;
	}

	public String getStr_remarks() {
		return str_remarks;
	}

	public void setStr_remarks(String str_remarks) {
		this.str_remarks = str_remarks;
	}

	public String getStr_operator() {
		return str_operator;
	}

	public void setStr_operator(String str_operator) {
		this.str_operator = str_operator;
	}

	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
	}

	public String getStr_modifier() {
		return str_modifier;
	}

	public void setStr_modifier(String str_modifier) {
		this.str_modifier = str_modifier;
	}

	public String getStr_modify_Date() {
		return str_modify_Date;
	}

	public void setStr_modify_Date(String str_modify_Date) {
		this.str_modify_Date = str_modify_Date;
	}

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}

	public int getI_bunkerCor_Id() {
		return i_bunkerCor_Id;
	}

	public void setI_bunkerCor_Id(int i_bunkerCor_Id) {
		this.i_bunkerCor_Id = i_bunkerCor_Id;
	}
}
