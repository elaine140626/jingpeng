package com.oil.model.system;

public class WareHouseInfo {

	private int id; 
	private String warehouseNumber; //仓库编号
	private String warehouseName;   //仓库名称
	private String warehouseType;   //仓库类别
	private String remarks;         //备注
	private int isDel;              //删除标识：0：未删除 1：已删除
	private String creater;         //创建人
	private String createrDate;     //创建时间
	private String reviser;         //修改人
	private String reviserDate;     //修改时间
	private String operation;       //操作
	private int rownumber;          //序号
	private String type;            //类别
	private double tankHeight;      //储罐高
	private double tankCapacity;    //罐容
	private String position;        //位置
	private double liquidHeight;    //液体高度
	private double alarmLow;        //储罐预警最低高度
	private double alarmHigh;       //储罐预警最高高度
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getTankHeight() {
		return tankHeight;
	}
	public void setTankHeight(double tankHeight) {
		this.tankHeight = tankHeight;
	}
	public double getTankCapacity() {
		return tankCapacity;
	}
	public void setTankCapacity(double tankCapacity) {
		this.tankCapacity = tankCapacity;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getLiquidHeight() {
		return liquidHeight;
	}
	public void setLiquidHeight(double liquidHeight) {
		this.liquidHeight = liquidHeight;
	}
	public double getAlarmLow() {
		return alarmLow;
	}
	public void setAlarmLow(double alarmLow) {
		this.alarmLow = alarmLow;
	}
	public double getAlarmHigh() {
		return alarmHigh;
	}
	public void setAlarmHigh(double alarmHigh) {
		this.alarmHigh = alarmHigh;
	}
	public int getRownumber() {
		return rownumber;
	}
	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWarehouseNumber() {
		return warehouseNumber;
	}
	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterDate() {
		return createrDate;
	}
	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}
	public String getReviser() {
		return reviser;
	}
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	public String getReviserDate() {
		return reviserDate;
	}
	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}
	
	
	
}
