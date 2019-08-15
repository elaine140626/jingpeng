package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class SnBar extends ModelSupport {
	private static final long serialVersionUID = -19428169264365737L;
	private String Org_Name;
	private int Unqualified;
	private int GLUnqualified;
	private int WCJUnqualified;
	private int SNUnqualified;
	private int WaterUnqualified;
	private String type;
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

	public int getWCJUnqualified() {
		return WCJUnqualified;
	}

	public void setWCJUnqualified(int wCJUnqualified) {
		WCJUnqualified = wCJUnqualified;
	}

	public int getSNUnqualified() {
		return SNUnqualified;
	}

	public void setSNUnqualified(int sNUnqualified) {
		SNUnqualified = sNUnqualified;
	}

	public int getWaterUnqualified() {
		return WaterUnqualified;
	}

	public void setWaterUnqualified(int waterUnqualified) {
		WaterUnqualified = waterUnqualified;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
