package com.oil.model.export;
 
import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;
 
 /**
  * @since 导出用实体类
  * @author lyq
  */
public class ContractInfoExportEntity implements Serializable  {
	
	// Id
//	@Excel(name = "序号")
	private int id;
	
	// 合同编号
	@Excel(name = "合同编号", width=18)
	private String contractNumber; 
	
	// 合同名称
	@Excel(name = "合同名称")
	private String contractName; 
	
	// 客户名称
	@Excel(name = "客户名称", isWrap=false, width=25)
	private String customerName; 
	
	// 销售数量
	@Excel(name = "销售数量", type=10)
	private Double saleNumber; 
	
	// 销售员
	@Excel(name = "销售员")
	private String name; 
	
	// 合同状态（数据字典） 0:执行中 1:未执行 2:完成 3:作废 4:未鉴定 5:其他
	@Excel(name = "合同状态")
	private String contractStateName; 
	
	// 备注
	@Excel(name = "备注")
	private String remarks; 
	
	// 附件
	@Excel(name = "附件")
	private String contractRoute;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Double saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContractStateName() {
		return contractStateName;
	}

	public void setContractStateName(String contractStateName) {
		this.contractStateName = contractStateName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getContractRoute() {
		return contractRoute;
	}

	public void setContractRoute(String contractRoute) {
		this.contractRoute = contractRoute;
	}
	
}
