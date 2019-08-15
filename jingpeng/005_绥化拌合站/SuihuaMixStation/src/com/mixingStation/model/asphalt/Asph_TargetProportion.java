package com.mixingStation.model.asphalt;

import java.util.List;

/**
 * 沥青目标配合比
 * 
 * @author Administrator
 *
 */
public class Asph_TargetProportion{
	private static final long serialVersionUID = -5822724785886006107L;
	private int id;
	private int org_Id;// 组织机构ID
	private String proportion_Code;// 配比编码
	private int product_Id;// 产品Id
	private String remarks;// 备注
	private String operator;// 创建人
	private String create_Date;// 创建时间
	private String modifier;// 修改人
	private String modify_Date;// 修改日期
	private int valid_Flag;// 用户是否有效：1有效，0无效
	private String material_Name;//产品名称
	private String material_Model;//产品型号
	private String materNameAndModel;//产品名称and产品型号
	private int i_upload;// 上传标识
	private List<Asph_TargetPropDetailed> asph_TargetPropList;
	
	public int getI_upload() {
		return i_upload;
	}
	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}
	public List<Asph_TargetPropDetailed> getAsph_TargetPropList() {
		return asph_TargetPropList;
	}
	public void setAsph_TargetPropList(List<Asph_TargetPropDetailed> asph_TargetPropList) {
		this.asph_TargetPropList = asph_TargetPropList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrg_Id() {
		return org_Id;
	}
	public void setOrg_Id(int org_Id) {
		this.org_Id = org_Id;
	}
	public String getProportion_Code() {
		return proportion_Code;
	}
	public void setProportion_Code(String proportion_Code) {
		this.proportion_Code = proportion_Code;
	}
	public int getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(int product_Id) {
		this.product_Id = product_Id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModify_Date() {
		return modify_Date;
	}
	public void setModify_Date(String modify_Date) {
		this.modify_Date = modify_Date;
	}
	public int getValid_Flag() {
		return valid_Flag;
	}
	public void setValid_Flag(int valid_Flag) {
		this.valid_Flag = valid_Flag;
	}
	public String getMaterial_Name() {
		return material_Name;
	}
	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}
	public String getMaterial_Model() {
		return material_Model;
	}
	public void setMaterial_Model(String material_Model) {
		this.material_Model = material_Model;
	}
	public String getMaterNameAndModel() {
		return materNameAndModel;
	}
	public void setMaterNameAndModel(String materNameAndModel) {
		this.materNameAndModel = materNameAndModel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}