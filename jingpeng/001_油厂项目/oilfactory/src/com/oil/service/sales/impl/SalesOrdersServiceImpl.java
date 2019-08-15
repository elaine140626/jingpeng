package com.oil.service.sales.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.sales.SalesOrdersDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.sales.Customerinfo;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;
import com.oil.service.sales.SalesOrdersService;
import com.oil.util.ReportMergeXls;

@Service
@Transactional
public class SalesOrdersServiceImpl implements SalesOrdersService{
		
	@Autowired
	private SalesOrdersDao salesOrdersDao;
	
	// 客户信息获取
	public Customerinfo getCustomerInfo(Map<String,Object> map) {
		return salesOrdersDao.getCustomerInfo(map);
	}
	
	// 销售订单取得
	public List<SalesOrdersInfoEntity> getSalesOrdersInfo(Map<String,Object> map){
		return salesOrdersDao.getSalesOrdersInfo(map);
	}
	
	// 销售订单明细查询
	public List<SalesOrdersDetailedEntity> getSalesOrdersDetailed(Map<String,Object> map){
		return salesOrdersDao.getSalesOrdersDetailed(map);
	}
	
	// 销售订单编号查重
	public String checkOrderNumber(String ContractNumber,String id){
		return salesOrdersDao.checkOrderNumber(ContractNumber,id);
	};
	
	// 新增销售订单
	public int addSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity,List<SalesOrdersDetailedEntity> SalesOrdersDetailedList) {
		int res = 0;
		// 销售订单表插入
		res += salesOrdersDao.insertSalesOrdersInfo(salesOrdersInfoEntity);
		// 取得刚插入销售订单表的id
		int id = salesOrdersDao.getSalesOrderId(salesOrdersInfoEntity.getCreater());
		// 销售订单明细表插入
		if (SalesOrdersDetailedList != null && SalesOrdersDetailedList.size()>0) {
			for (int i = 0; i < SalesOrdersDetailedList.size(); i++) {
				SalesOrdersDetailedList.get(i).setSalesOrderId(id);
				res += salesOrdersDao.insertSalesOrdersDetailed(SalesOrdersDetailedList.get(i));
			}
		}
		return res;	
	}
	
	// 修改销售订单
	public int updateSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity,List<SalesOrdersDetailedEntity> SalesOrdersDetailedList) {
		int res = 0;
		// 合同表更新
		if(Integer.parseInt(salesOrdersInfoEntity.getOrderState()) != 3) {
			salesOrdersInfoEntity.setCancellationCause("");
		}
		res += salesOrdersDao.updateSalesOrdersInfo(salesOrdersInfoEntity);
		Map<String,Object> map = new HashMap<>();
		map.put("id", Integer.toString(salesOrdersInfoEntity.getId()));
		List<SalesOrdersDetailedEntity> salesOrderList = salesOrdersDao.getSalesOrdersDetailed(map);
		//List<SalesOrdersDetailedEntity> salesOrderListRemove =  salesOrdersDao.getSalesOrdersDetailed(map);
		
			//添加修改明细
			for (int j = 0; j < SalesOrdersDetailedList.size(); j++) {
				Boolean isHave = false;
				for (int i = 0; i < salesOrderList.size(); i++) {
					if(salesOrderList.get(i).getOrderDetailedNumber().equals(SalesOrdersDetailedList.get(j).getOrderDetailedNumber())) {
						SalesOrdersDetailedList.get(j).setSalesOrderId(salesOrdersInfoEntity.getId());
						SalesOrdersDetailedList.get(j).setId(salesOrderList.get(i).getId());
						isHave = true;
						break;
					}
					if(i == salesOrderList.size()-1 && isHave == false) {
						SalesOrdersDetailedList.get(j).setSalesOrderId(salesOrdersInfoEntity.getId());
					}
				}
				if(isHave) {
					res += salesOrdersDao.updateSalesOrdersDetailed(SalesOrdersDetailedList.get(j));
				}else {
					res += salesOrdersDao.insertSalesOrdersDetailed(SalesOrdersDetailedList.get(j));
				}
			}
		return res;	
	}
	
	// 销售订单作废
	public int updateOrderState(SalesOrdersInfoEntity SalesOrdersInfoEntity) {
		return salesOrdersDao.updateOrderState(SalesOrdersInfoEntity);
	}
	
	// 销售订单删除
	public int deleteSalesOrdersInfo(String Reviser,String Id) {
		int res = 0;
		// 销售订单删除
		res += salesOrdersDao.updateIsDelSalesOrdersInfo(Reviser,Id);
		// 销售订单明细删除
		res += salesOrdersDao.updateIsDelSalesOrdersDetailed(Reviser,Id);
		return res;
	}
	// 销售订单目录tree
	public List<ContractTreeEntity> getContractTree(Map<String, Object> map){
		return salesOrdersDao.getContractTree(map);
	}

	@Override
	public DataTablesResponseInfo getCustomertransportsById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(salesOrdersDao.getCustomertransportsById(map));
		return info;
	}

	// 销售订单明细删除 (删除指定)
	@Override
	public ResponseInfo updateIsDelSalesOrdersDetailedByOrderDetailedNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		// 删除订单明细验证
		int res = salesOrdersDao.isDelOrderDetailedNumber(map);
		if(res > 0 ) {
			info.setMessage("操作失败");
			info.setCode("existence");
		}else {
			// 销售订单明细删除 (删除指定)
			result = salesOrdersDao.updateIsDelSalesOrdersDetailedByOrderDetailedNumber(map);
			if(result > 0) {
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
			}
		}
		return info;
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String sheetName = "销售信息";
		//表头
		String[] head0 = new String[] { "编号", "流水号", "客户名称", "发货日期", "产品名称", "规格型号","沥青含量", "出厂数量", "到厂数量", "结算数量",
				"单价", "金额", "车牌号", "是否含税","是否自提","联系人","电话","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = salesOrdersDao.getCustomerInfoEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
			 if(dataList.get(i).get("taxRateContent") == null ||  dataList.get(i).get("taxRateContent").equals("")) {
				 dataList.get(i).put("taxRateContent", "否");
			 }else {
				 dataList.get(i).put("taxRateContent", "是");
			 }
			 if(dataList.get(i).get("transportBalance") != null) {
				 if(Integer.parseInt(dataList.get(i).get("transportBalance").toString()) == 1) {
					 dataList.get(i).put("transportBalance", "是"); 
				 }else {
					 dataList.get(i).put("transportBalance", "否"); 
				 }
			 }
		}
		 String[] colName = new String[] { "row", "orderDetailedNumber", "customerName", "grossMeasureTime", "materielName", "materielModel", "asphaltContent", "allotWeight", "receiptWeight",
				 "collectWeigh", "salePrice", "saleMoney","plateNumber", "taxRateContent", "transportBalance", "contacts","telephone", "remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,数值对应列名称,创建时间,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0, colName, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
