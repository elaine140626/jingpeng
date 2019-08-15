package com.jingpeng.model;

import java.math.BigDecimal;
import java.util.List;

import com.kdt.base.support.model.ModelSupport;

public class Asphalt_GradingModel extends ModelSupport{

	/**
	 * 沥青级配 新建实体 整合 需要数据
	 */
	private static final long serialVersionUID = -4848147669166756740L;
	
	private int i_id;//级配id
	
	private int i_org_Id;//组织Id
	
	private String str_org_Name; //组织名称
	
	private String str_grade_Code;//级配编号
	
	private String str_create_Date;//创建时间
	
	private String str_modify_Date;//修改时间
	
	private int i_product_Id;//产品id
	
	private String str_operator;// 修改人
	
	private int  i_material_Name_id;//产品名称id
	
	private int  i_material_Model_id;//产品型号id
	
	private String  str_material_Name;//产品名称
	
	private String  str_material_Model;//产品型号
	
	private String  str_material_Mode;//物料类型
	
	private String str_material_Type; //产品类型（1水泥、0沥青）
	
	private String  str_warehouse_Num;//仓号
	
	private BigDecimal d_a0_075;//0.075孔径通过率
	
	private BigDecimal d_a0_15;//0.15孔径通过率
	
	private BigDecimal d_a0_3;//0.3孔径通过率
	
	private BigDecimal d_a0_6;//0.6孔径通过率
	
	private BigDecimal d_a1_18;//1.18孔径通过率
	
	private BigDecimal d_a2_36;//2.36孔径通过率
	
	private BigDecimal d_a4_75;//4.75孔径通过率
	
	private BigDecimal d_a9_5;//9.5孔径通过率
	
	private BigDecimal d_a13_2;//13.2孔径通过率
	
	private BigDecimal d_a16;//16孔径通过率
	
	private BigDecimal d_a19;//19孔径通过率
	
	private BigDecimal d_a26_5;//26.5孔径通过率
	
	private BigDecimal d_a31_5;//31.5孔径通过率
	
	private BigDecimal d_a37_5;//37.5孔径通过率
	
	private BigDecimal d_a53;//53.0孔径通过率
	
	private int i_valid_Flag;// 用户是否有效：1有效，0无效
	
	private int i_upload;// 上传标识
	
	private int i_grad_Id;//级配id
	
	private String str_modifier;// 修改人
	private String str_remarks;

	private String str_unique_Code;// MD5 唯一标识
	private String operate;
	private int serialNumber;
	private String materNameAndModel;

	public String getStr_unique_Code() {
		return str_unique_Code;
	}

	public void setStr_unique_Code(String str_unique_Code) {
		this.str_unique_Code = str_unique_Code;
	}

	public String getStr_modifier() {
		return str_modifier;
	}

	public void setStr_modifier(String str_modifier) {
		this.str_modifier = str_modifier;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public String getStr_org_Name() {
		return str_org_Name;
	}

	public void setStr_org_Name(String str_org_Name) {
		this.str_org_Name = str_org_Name;
	}

	public String getStr_grade_Code() {
		return str_grade_Code;
	}

	public void setStr_grade_Code(String str_grade_Code) {
		this.str_grade_Code = str_grade_Code;
	}

	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
	}

	public String getStr_modify_Date() {
		return str_modify_Date;
	}

	public void setStr_modify_Date(String str_modify_Date) {
		this.str_modify_Date = str_modify_Date;
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

	public String getStr_material_Type() {
		return str_material_Type;
	}

	public void setStr_material_Type(String str_material_Type) {
		this.str_material_Type = str_material_Type;
	}

	public String getStr_warehouse_Num() {
		return str_warehouse_Num;
	}

	public void setStr_warehouse_Num(String str_warehouse_Num) {
		this.str_warehouse_Num = str_warehouse_Num;
	}

	public BigDecimal getD_a0_075() {
		return d_a0_075;
	}

	public void setD_a0_075(BigDecimal d_a0_075) {
		this.d_a0_075 = d_a0_075;
	}

	public BigDecimal getD_a0_15() {
		return d_a0_15;
	}

	public void setD_a0_15(BigDecimal d_a0_15) {
		this.d_a0_15 = d_a0_15;
	}

	public BigDecimal getD_a0_3() {
		return d_a0_3;
	}

	public void setD_a0_3(BigDecimal d_a0_3) {
		this.d_a0_3 = d_a0_3;
	}

	public BigDecimal getD_a0_6() {
		return d_a0_6;
	}

	public void setD_a0_6(BigDecimal d_a0_6) {
		this.d_a0_6 = d_a0_6;
	}

	public BigDecimal getD_a1_18() {
		return d_a1_18;
	}

	public void setD_a1_18(BigDecimal d_a1_18) {
		this.d_a1_18 = d_a1_18;
	}

	public BigDecimal getD_a2_36() {
		return d_a2_36;
	}

	public void setD_a2_36(BigDecimal d_a2_36) {
		this.d_a2_36 = d_a2_36;
	}

	public BigDecimal getD_a4_75() {
		return d_a4_75;
	}

	public void setD_a4_75(BigDecimal d_a4_75) {
		this.d_a4_75 = d_a4_75;
	}

	public BigDecimal getD_a9_5() {
		return d_a9_5;
	}

	public void setD_a9_5(BigDecimal d_a9_5) {
		this.d_a9_5 = d_a9_5;
	}

	public BigDecimal getD_a13_2() {
		return d_a13_2;
	}

	public void setD_a13_2(BigDecimal d_a13_2) {
		this.d_a13_2 = d_a13_2;
	}

	public BigDecimal getD_a16() {
		return d_a16;
	}

	public void setD_a16(BigDecimal d_a16) {
		this.d_a16 = d_a16;
	}

	public BigDecimal getD_a19() {
		return d_a19;
	}

	public void setD_a19(BigDecimal d_a19) {
		this.d_a19 = d_a19;
	}

	public BigDecimal getD_a26_5() {
		return d_a26_5;
	}

	public void setD_a26_5(BigDecimal d_a26_5) {
		this.d_a26_5 = d_a26_5;
	}

	public BigDecimal getD_a31_5() {
		return d_a31_5;
	}

	public void setD_a31_5(BigDecimal d_a31_5) {
		this.d_a31_5 = d_a31_5;
	}

	public BigDecimal getD_a37_5() {
		return d_a37_5;
	}

	public void setD_a37_5(BigDecimal d_a37_5) {
		this.d_a37_5 = d_a37_5;
	}

	public BigDecimal getD_a53() {
		return d_a53;
	}

	public void setD_a53(BigDecimal d_a53) {
		this.d_a53 = d_a53;
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

	public int getI_grad_Id() {
		return i_grad_Id;
	}

	public void setI_grad_Id(int i_grad_Id) {
		this.i_grad_Id = i_grad_Id;
	}

	public int getI_org_Id() {
		return i_org_Id;
	}

	public void setI_org_Id(int i_org_Id) {
		this.i_org_Id = i_org_Id;
	}

	public int getI_material_Name_id() {
		return i_material_Name_id;
	}

	public void setI_material_Name_id(int i_material_Name_id) {
		this.i_material_Name_id = i_material_Name_id;
	}

	public int getI_material_Model_id() {
		return i_material_Model_id;
	}

	public void setI_material_Model_id(int i_material_Model_id) {
		this.i_material_Model_id = i_material_Model_id;
	}

	public int getI_product_Id() {
		return i_product_Id;
	}

	public void setI_product_Id(int i_product_Id) {
		this.i_product_Id = i_product_Id;
	}

	public String getStr_operator() {
		return str_operator;
	}

	public void setStr_operator(String str_operator) {
		this.str_operator = str_operator;
	}

	public String getStr_material_Mode() {
		return str_material_Mode;
	}

	public void setStr_material_Mode(String str_material_Mode) {
		this.str_material_Mode = str_material_Mode;
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

	public String getMaterNameAndModel() {
		return materNameAndModel;
	}

	public void setMaterNameAndModel(String materNameAndModel) {
		this.materNameAndModel = materNameAndModel;
	}

	public String getStr_remarks() {
		return str_remarks;
	}

	public void setStr_remarks(String str_remarks) {
		this.str_remarks = str_remarks;
	}
	
}
