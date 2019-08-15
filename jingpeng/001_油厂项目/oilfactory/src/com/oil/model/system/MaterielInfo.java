package com.oil.model.system;

public class MaterielInfo {
	private int id;
	private String MaterielNumber;	 //物料编号	
	private int MaterielNameId;		 //物料名称id		
	private String MaterielName;	 //物料名称	
	private int MaterielModelId	;	 //物料型号id
	private String MaterielModel;    //物料型号
	private String Unit ;            //单位		
	private String MaterielType;     //物料类别（数据字典）
	private String MaterielTypeName; // 物料类别（数据字典）
	private int WarehouseId;	     //仓库id	
	private String WarehouseName;	 //仓库名称	
	private String 	Remarks;         //备注
	private Double PrimeNumber;	     //年期初数量	
	private Double TaxMoney;         //年期初含税金额	
	private Double NoTaxMoney;       //年期初不含税金额	
	private String IsDel;	         //删除标记		
	private String Creater;	         //创建人	
	private String CreaterDate;      //创建日期	
	private String Reviser;	         //修改人	
	private String ReviserDate;	     //修改日期	
	private int Serialnumber;        //序号	
	private String Operate;          //操作
	
	public String getMaterielTypeName() {
		return MaterielTypeName;
	}
	public void setMaterielTypeName(String materielTypeName) {
		MaterielTypeName = materielTypeName;
	}
	public String getWarehouseName() {
		return WarehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		WarehouseName = warehouseName;
	}
	public String getMaterielName() {
		return MaterielName;
	}
	public void setMaterielName(String materielName) {
		MaterielName = materielName;
	}
	public String getMaterielModel() {
		return MaterielModel;
	}
	public void setMaterielModel(String materielModel) {
		MaterielModel = materielModel;
	}
	public int getSerialnumber() {
		return Serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		Serialnumber = serialnumber;
	}
	public String getOperate() {
		return Operate;
	}
	public void setOperate(String operate) {
		Operate = operate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaterielNumber() {
		return MaterielNumber;
	}
	public void setMaterielNumber(String materielNumber) {
		MaterielNumber = materielNumber;
	}
	public int getMaterielNameId() {
		return MaterielNameId;
	}
	public void setMaterielNameId(int materielNameId) {
		MaterielNameId = materielNameId;
	}
	public int getMaterielModelId() {
		return MaterielModelId;
	}
	public void setMaterielModelId(int materielModelId) {
		MaterielModelId = materielModelId;
	}
	public String getMaterielType() {
		return MaterielType;
	}
	public void setMaterielType(String materielType) {
		MaterielType = materielType;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public int getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		WarehouseId = warehouseId;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public Double getPrimeNumber() {
		return PrimeNumber;
	}
	public void setPrimeNumber(Double primeNumber) {
		PrimeNumber = primeNumber;
	}
	public Double getTaxMoney() {
		return TaxMoney;
	}
	public void setTaxMoney(Double taxMoney) {
		TaxMoney = taxMoney;
	}
	public Double getNoTaxMoney() {
		return NoTaxMoney;
	}
	public void setNoTaxMoney(Double noTaxMoney) {
		NoTaxMoney = noTaxMoney;
	}
	public String getIsDel() {
		return IsDel;
	}
	public void setIsDel(String isDel) {
		IsDel = isDel;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public String getCreaterDate() {
		return CreaterDate;
	}
	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}
	public String getReviser() {
		return Reviser;
	}
	public void setReviser(String reviser) {
		Reviser = reviser;
	}
	public String getReviserDate() {
		return ReviserDate;
	}
	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}
	
}
