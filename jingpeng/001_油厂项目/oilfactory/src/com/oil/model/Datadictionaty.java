package com.oil.model;

public class Datadictionaty {
		private int id ; 
		private String Type ; 	//	分组
		private String Code ;//索引
		private String Content ;//内容
		private int IsDel ;//删除标记
		private String Creater ;//创建人
		private String CreaterDate ;//创建日期
		private String Reviser ;//修改人
		private String ReviserDate ;//修改日期
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public String getCode() {
			return Code;
		}
		public void setCode(String code) {
			Code = code;
		}
		public String getContent() {
			return Content;
		}
		public void setContent(String content) {
			Content = content;
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
		
		

}
