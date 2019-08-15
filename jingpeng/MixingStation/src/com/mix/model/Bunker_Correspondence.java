package com.mix.model;


/**
 * 水泥料仓对应关系
 * 
 * @author Administrator
 *
 */
public class Bunker_Correspondence{
	private static final long serialVersionUID = -6864161904856575680L;
	private int i_id;//
	private int i_org_Id;// 组织机构ID
	private int i_equ_Id;// 拌和机Id
	private int i_consProp_Id;// 施工配比ID
	private String str_bunker_Code;
	private int i_mateId_Cement1;// 水泥仓1
	private int i_mateId_Cement2;// 水泥仓2
	private int i_mateId_Cement3;// 水泥仓3
	private int i_mateId_Cement4;// 水泥仓4
	private int i_mateId_Aggregate1;// 骨料仓1
	private int i_mateId_Aggregate2;// 骨料仓2
	private int i_mateId_Aggregate3;// 骨料仓3
	private int i_mateId_Aggregate4;// 骨料仓4
	private int i_mateId_Aggregate5;// 骨料仓5
	private int i_mateId_Aggregate6;// 骨料仓6
	private int i_mateId_Water;// 水仓
	private int i_mateId_Admixture1;// 外掺剂1仓
	private int i_mateId_Admixture2;// 外掺剂2仓
	private String str_effective_Time;// 生效时间
	private String str_remarks;// 备注
	private String str_operator;// 创建人
	private String str_create_Date;// 创建日期
	private String str_modifier;// 修改人
	private String str_modify_Date;// 修改日期
	private String str_material_Name;//产品名称
	private String str_material_Mold;//物料类别
	private int i_valid_Flag;// 有效标识
	private int i_upload;// 上传标识
	private String str_Material;
	private int i_productid;
	private String str_prop_Code;
	
	private String operate;
	
	public String getStr_material_Name() {
		return str_material_Name;
	}

	public void setStr_material_Name(String str_material_Name) {
		this.str_material_Name = str_material_Name;
	}

	public String getStr_material_Mold() {
		return str_material_Mold;
	}

	public void setStr_material_Mold(String str_material_Mold) {
		this.str_material_Mold = str_material_Mold;
	}

	private int serialNumber;
	
	public String getStr_Material() {
		return str_Material;
	}

	public void setStr_Material(String str_Material) {
		this.str_Material = str_Material;
	}

	public int getI_productid() {
		return i_productid;
	}

	public void setI_productid(int i_productid) {
		this.i_productid = i_productid;
	}

	public String getStr_prop_Code() {
		return str_prop_Code;
	}

	public void setStr_prop_Code(String str_prop_Code) {
		this.str_prop_Code = str_prop_Code;
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

	public int getI_consProp_Id() {
		return i_consProp_Id;
	}

	public void setI_consProp_Id(int i_consProp_Id) {
		this.i_consProp_Id = i_consProp_Id;
	}

	public int getI_mateId_Cement1() {
		return i_mateId_Cement1;
	}

	public void setI_mateId_Cement1(int i_mateId_Cement1) {
		this.i_mateId_Cement1 = i_mateId_Cement1;
	}

	public int getI_mateId_Cement2() {
		return i_mateId_Cement2;
	}

	public void setI_mateId_Cement2(int i_mateId_Cement2) {
		this.i_mateId_Cement2 = i_mateId_Cement2;
	}

	public int getI_mateId_Cement3() {
		return i_mateId_Cement3;
	}

	public void setI_mateId_Cement3(int i_mateId_Cement3) {
		this.i_mateId_Cement3 = i_mateId_Cement3;
	}

	public int getI_mateId_Cement4() {
		return i_mateId_Cement4;
	}

	public void setI_mateId_Cement4(int i_mateId_Cement4) {
		this.i_mateId_Cement4 = i_mateId_Cement4;
	}

	public int getI_mateId_Aggregate1() {
		return i_mateId_Aggregate1;
	}

	public void setI_mateId_Aggregate1(int i_mateId_Aggregate1) {
		this.i_mateId_Aggregate1 = i_mateId_Aggregate1;
	}

	public int getI_mateId_Aggregate2() {
		return i_mateId_Aggregate2;
	}

	public void setI_mateId_Aggregate2(int i_mateId_Aggregate2) {
		this.i_mateId_Aggregate2 = i_mateId_Aggregate2;
	}

	public int getI_mateId_Aggregate3() {
		return i_mateId_Aggregate3;
	}

	public void setI_mateId_Aggregate3(int i_mateId_Aggregate3) {
		this.i_mateId_Aggregate3 = i_mateId_Aggregate3;
	}

	public int getI_mateId_Aggregate4() {
		return i_mateId_Aggregate4;
	}

	public void setI_mateId_Aggregate4(int i_mateId_Aggregate4) {
		this.i_mateId_Aggregate4 = i_mateId_Aggregate4;
	}

	public int getI_mateId_Aggregate5() {
		return i_mateId_Aggregate5;
	}

	public void setI_mateId_Aggregate5(int i_mateId_Aggregate5) {
		this.i_mateId_Aggregate5 = i_mateId_Aggregate5;
	}

	public int getI_mateId_Aggregate6() {
		return i_mateId_Aggregate6;
	}

	public void setI_mateId_Aggregate6(int i_mateId_Aggregate6) {
		this.i_mateId_Aggregate6 = i_mateId_Aggregate6;
	}

	public int getI_mateId_Water() {
		return i_mateId_Water;
	}

	public void setI_mateId_Water(int i_mateId_Water) {
		this.i_mateId_Water = i_mateId_Water;
	}

	public int getI_mateId_Admixture1() {
		return i_mateId_Admixture1;
	}

	public void setI_mateId_Admixture1(int i_mateId_Admixture1) {
		this.i_mateId_Admixture1 = i_mateId_Admixture1;
	}

	public int getI_mateId_Admixture2() {
		return i_mateId_Admixture2;
	}

	public void setI_mateId_Admixture2(int i_mateId_Admixture2) {
		this.i_mateId_Admixture2 = i_mateId_Admixture2;
	}

	public String getStr_effective_Time() {
		return str_effective_Time;
	}

	public void setStr_effective_Time(String str_effective_Time) {
		this.str_effective_Time = str_effective_Time;
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

	public String getStr_bunker_Code() {
		return str_bunker_Code;
	}

	public void setStr_bunker_Code(String str_bunker_Code) {
		this.str_bunker_Code = str_bunker_Code;
	}

}
