package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 水泥生产物料统计辅助
 * 
 * @author Administrator
 *
 */
public class Cement_Product_Assist extends ModelSupport {
	private static final long serialVersionUID = -820661861097326685L;
	private int i_id;//
	private int i_product_Id;// 生产数据Id
	private int i_material_Id;// 物料Id
	private double d_material_Amount;// 物料投放量
	private int i_warehouse;// 投放料仓
	private int i_warehouseType;// 料仓类型

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_product_Id() {
		return i_product_Id;
	}

	public void setI_product_Id(int i_product_Id) {
		this.i_product_Id = i_product_Id;
	}

	public int getI_material_Id() {
		return i_material_Id;
	}

	public void setI_material_Id(int i_material_Id) {
		this.i_material_Id = i_material_Id;
	}

	public double getD_material_Amount() {
		return d_material_Amount;
	}

	public void setD_material_Amount(double d_material_Amount) {
		this.d_material_Amount = d_material_Amount;
	}

	public int getI_warehouse() {
		return i_warehouse;
	}

	public void setI_warehouse(int i_warehouse) {
		this.i_warehouse = i_warehouse;
	}

	public int getI_warehouseType() {
		return i_warehouseType;
	}

	public void setI_warehouseType(int i_warehouseType) {
		this.i_warehouseType = i_warehouseType;
	}
}
