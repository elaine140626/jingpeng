package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class LqBar extends ModelSupport {
	private static final long serialVersionUID = -1209428169264365737L;
	private String Org_Name;
	private int Unqualified;
	private int GLUnqualified;
	private int FLUnqualified;
	private int LQUnqualified;
	private int WCJUnqualified;
	private int JPUnqualified;
	private String type;
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	public int getUnqualified() {
		return Unqualified;
	}
	public void setUnqualified(int unqualified) {
		Unqualified = unqualified;
	}
	public int getGLUnqualified() {
		return GLUnqualified;
	}
	public void setGLUnqualified(int gLUnqualified) {
		GLUnqualified = gLUnqualified;
	}
	public int getFLUnqualified() {
		return FLUnqualified;
	}
	public void setFLUnqualified(int fLUnqualified) {
		FLUnqualified = fLUnqualified;
	}
	public int getLQUnqualified() {
		return LQUnqualified;
	}
	public void setLQUnqualified(int lQUnqualified) {
		LQUnqualified = lQUnqualified;
	}
	public int getWCJUnqualified() {
		return WCJUnqualified;
	}
	public void setWCJUnqualified(int wCJUnqualified) {
		WCJUnqualified = wCJUnqualified;
	}
	public int getJPUnqualified() {
		return JPUnqualified;
	}
	public void setJPUnqualified(int jPUnqualified) {
		JPUnqualified = jPUnqualified;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
