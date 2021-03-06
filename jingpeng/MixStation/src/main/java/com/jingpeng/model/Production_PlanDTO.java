package com.jingpeng.model;


import com.kdt.base.support.model.ModelSupport;

public class Production_PlanDTO extends ModelSupport {

	/**
	 * 	生产计划表:指定拌合机生产使用，并明确指明该计划下的拌和数据使用去向及部位；
		可根据生产计划追踪 生产数据、生产数据去向使用部位、试验检测结果；
	 */
	private static final long serialVersionUID = -26273898145367770L;
	
	private int i_id; //自增长ID
	
	private int i_org_Id; //组织机构ID
	
	private String str_plan_No; //生产计划编号
	
	private String str_startTime; //生产计划开始时间
	
	private int i_product_Id; //产品Id
	
	private int i_prod_Id; //生产配比ID
	
	private String str_proj_Pos; //工程部位/用途
	
	private String str_construction_Unit; //施工单位
	
	private double d_scheduled_Production; //计划生产量
	
	private int i_isCancel; //计划是否作废
	private String str_isCancel;
	
	private String str_cancel_Time; //作废时间
	
	private String str_invalid_Person; //作废人
	
	private String str_cancel_Reason; //作废原因
	
	private String str_operator; //创建人
	
	private String str_create_Date; //创建日期
	
	private int i_valid_Flag; //有效标识
	
	private String str_material_Name;
	private String str_material_Model;
	private String str_proportion_Code;//生产配合比编号
	private String str_str_org_name;
	private String str_equipment_Name;
	private String nameAndModel;
	private String str_grade_Code;
	private int i_grad_Id;
	
	private int i_equ_Id;//拌合机ID
	
	private String operate;
	private int serialNumber;
	
	private String str_Mproportion_Code;//目标配合比编号
	private int i_peih_id;
	

	public int getI_peih_id() {
		return i_peih_id;
	}

	public void setI_peih_id(int i_peih_id) {
		this.i_peih_id = i_peih_id;
	}

	public String getStr_Mproportion_Code() {
		return str_Mproportion_Code;
	}

	public void setStr_Mproportion_Code(String str_Mproportion_Code) {
		this.str_Mproportion_Code = str_Mproportion_Code;
	}

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

	public int getI_product_Id() {
		return i_product_Id;
	}

	public void setI_product_Id(int i_product_Id) {
		this.i_product_Id = i_product_Id;
	}

	public int getI_prod_Id() {
		return i_prod_Id;
	}

	public void setI_prod_Id(int i_prod_Id) {
		this.i_prod_Id = i_prod_Id;
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

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
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

	public String getStr_proportion_Code() {
		return str_proportion_Code;
	}

	public void setStr_proportion_Code(String str_proportion_Code) {
		this.str_proportion_Code = str_proportion_Code;
	}

	public int getI_equ_Id() {
		return i_equ_Id;
	}

	public void setI_equ_Id(int i_equ_Id) {
		this.i_equ_Id = i_equ_Id;
	}

	public String getStr_str_org_name() {
		return str_str_org_name;
	}

	public void setStr_str_org_name(String str_str_org_name) {
		this.str_str_org_name = str_str_org_name;
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

	public String getStr_equipment_Name() {
		return str_equipment_Name;
	}

	public void setStr_equipment_Name(String str_equipment_Name) {
		this.str_equipment_Name = str_equipment_Name;
	}

	public String getNameAndModel() {
		return nameAndModel;
	}

	public void setNameAndModel(String nameAndModel) {
		this.nameAndModel = nameAndModel;
	}

	public String getStr_grade_Code() {
		return str_grade_Code;
	}

	public void setStr_grade_Code(String str_grade_Code) {
		this.str_grade_Code = str_grade_Code;
	}

	public String getStr_isCancel() {
		return str_isCancel;
	}

	public void setStr_isCancel(String str_isCancel) {
		this.str_isCancel = str_isCancel;
	}

	public int getI_grad_Id() {
		return i_grad_Id;
	}

	public void setI_grad_Id(int i_grad_Id) {
		this.i_grad_Id = i_grad_Id;
	}
	
}
