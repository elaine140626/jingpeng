package com.jingpeng.model;


import java.math.BigDecimal;

import com.kdt.base.support.model.ModelSupport;

/**
 * 级配孔径信息
 * 
 * @author Administrator
 *
 */
public class Asphalt_GradDetailed extends ModelSupport {
	private static final long serialVersionUID = -153359496986365391L;
	private int i_id;
	private int i_grad_Id;// 级配ID（外键）
	private String str_warehouse_Num;// 仓号
	private BigDecimal d_a0_075;// 0.075孔径通过率
	private BigDecimal d_a0_15;// 0.15孔径通过率
	private BigDecimal d_a0_3;// 0.3孔径通过率
	private BigDecimal d_a0_6;// 0.6孔径通过率
	private BigDecimal d_a1_18;// 1.18孔径通过率
	private BigDecimal d_a2_36;// 2.36孔径通过率
	private BigDecimal d_a4_75;// 4.75孔径通过率
	private BigDecimal d_a9_5;// 9.5孔径通过率
	private BigDecimal d_a13_2;// 13.2孔径通过率
	private BigDecimal d_a16;// 16孔径通过率
	private BigDecimal d_a19;// 19孔径通过率
	private BigDecimal d_a26_5;// 26.5孔径通过率
	private BigDecimal d_a31_5;// 31.5孔径通过率
	private BigDecimal d_a37_5;// 37.5孔径通过率
	private BigDecimal d_a53;// 53.0孔径通过率
	private int i_valid_Flag;// 用户是否有效：1有效，0无效
	private int i_upload;// 上传标识
	
	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_grad_Id() {
		return i_grad_Id;
	}

	public void setI_grad_Id(int i_grad_Id) {
		this.i_grad_Id = i_grad_Id;
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

}
