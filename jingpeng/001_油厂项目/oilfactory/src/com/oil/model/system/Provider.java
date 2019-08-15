package com.oil.model.system;

public class Provider {
		private int id;
		private String SupplierNumber;    //供应商编号
		private String SupplierName;      //供应商名称	
		private String Address;	          //地址	
		private String Contacts;          //联系人	
		private String Telephone;         //联系电话	
		private String OtherNumbers;      //其他联系方式	
		private String Remarks;	          //备注	
		private double ArrearsMoney;      //年期初我方欠款		
		private int IsDel;		          //删除标记	
		private String Creater;           //创建人	
		private String CreaterDate;       //创建日期				
		private String Reviser	;         //修改人	
		private String ReviserDate;       //修改日期	
		private int Serialnumber;         //序号
		private String Operate;           //操作
		private double SupplierDeviation; //允许偏差值
		
		
		public double getSupplierDeviation() {
			return SupplierDeviation;
		}
		public void setSupplierDeviation(double supplierDeviation) {
			SupplierDeviation = supplierDeviation;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSupplierNumber() {
			return SupplierNumber;
		}
		public void setSupplierNumber(String supplierNumber) {
			SupplierNumber = supplierNumber;
		}
		public String getSupplierName() {
			return SupplierName;
		}
		public void setSupplierName(String supplierName) {
			SupplierName = supplierName;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getContacts() {
			return Contacts;
		}
		public void setContacts(String contacts) {
			Contacts = contacts;
		}
		public String getTelephone() {
			return Telephone;
		}
		public void setTelephone(String telephone) {
			Telephone = telephone;
		}
		public String getOtherNumbers() {
			return OtherNumbers;
		}
		public void setOtherNumbers(String otherNumbers) {
			OtherNumbers = otherNumbers;
		}
		public String getRemarks() {
			return Remarks;
		}
		public void setRemarks(String remarks) {
			Remarks = remarks;
		}
		public double getArrearsMoney() {
			return ArrearsMoney;
		}
		public void setArrearsMoney(double arrearsMoney) {
			ArrearsMoney = arrearsMoney;
		}
		public int getIsDel() {
			return IsDel;
		}
		public void setIsDel(int isDel) {
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
		
}
