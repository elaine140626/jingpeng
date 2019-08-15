package com.mix.model;

public class Production_Plan{

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
	
	private int i_grad_Id;//级配id
	
	private String str_proj_Pos; //工程部位/用途
	
	private String str_construction_Unit; //施工单位
	
	private double d_scheduled_Production; //计划生产量
	
	private int i_isCancel; //计划是否作废
	
	private String str_cancel_Time; //作废时间
	
	private String str_invalid_Person; //作废人
	
	private String str_cancel_Reason; //作废原因
	
	private String str_remarks; //备注
	
	private String str_operator; //创建人
	
	private String str_create_Date; //创建日期
	
	private String str_modifier; //修改人
	
	private String  str_modify_Date; //修改日期
	
	private int i_valid_Flag; //有效标识
	
	private int i_upload; //上传标识
	
	private int i_equ_Id;//拌合机ID

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

	public int getI_upload() {
		return i_upload;
	}

	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}

	public int getI_equ_Id() {
		return i_equ_Id;
	}

	public void setI_equ_Id(int i_equ_Id) {
		this.i_equ_Id = i_equ_Id;
	}

	public int getI_grad_Id() {
		return i_grad_Id;
	}

	public void setI_grad_Id(int i_grad_Id) {
		this.i_grad_Id = i_grad_Id;
	}

}
