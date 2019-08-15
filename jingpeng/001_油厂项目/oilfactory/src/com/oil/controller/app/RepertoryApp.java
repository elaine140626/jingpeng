package com.oil.controller.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.service.repertory.RepertoryService;

/**
 * 
 * @Title 未称重app接口
 * @author Administrator
 * @date 2018年11月19日
 */
@Controller
@RequestMapping("/repertoryApp")
public class RepertoryApp {

	@Autowired
	private RepertoryService repertoryService;
	
	//app树形结构用户名称下拉框查询
	/*@RequestMapping("/getAppTreeCustomerName.json")
	public ResponseEntity<Map<String, Object>> getAppTreeCustomerName(){
		List<SalesOrderEntity> appTreeCustomerName = repertoryService.getAppTreeCustomerName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appTreeCustomerName", appTreeCustomerName);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}*/
	
	//app树形结构销售订单下拉框查询
	@RequestMapping(value = {"/getAppSalesOrderList.json"})
	public ResponseEntity<Map<String, Object>> getAppSalesOrderList(){
    	List<SalesOrderEntity> appSalesOrderList = repertoryService.getAppSalesOrderList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appSalesOrderList", appSalesOrderList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//页面数据
	@RequestMapping(value = {"/getAppNoWeighList.json"})
	public ResponseEntity<Map<String, Object>> getAppNoWeighList(@RequestParam(value="client")String client,@RequestParam(value="orderNumber")String orderNumber,@RequestParam(value="id")String id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("id", id);
    	param.put("client",client);
    	param.put("orderNumber",orderNumber);
		List<NoWeighOutWarehouse> appNoWeighList = repertoryService.getAppNoWeighList(param);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appNoWeighList", appNoWeighList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增页面查询销售订单编号
	@RequestMapping(value = {"/getAppOrderNumberList.json"})
	public ResponseEntity<Map<String, Object>> getAppOrderNumberList(@RequestParam(value="ckTime")String ckTime,@RequestParam(value="id1")String id1,@RequestParam(value="customername")String customername,@RequestParam(value="plateNumber")String plateNumber,@RequestParam(value="Id")String Id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("ckTime",ckTime);
    	param.put("Id",Id);
    	param.put("id1",id1);
    	param.put("customername",customername);
    	param.put("plateNumber",plateNumber);
    	List<Map<String, Object>> appInfoList = repertoryService.getOutboundEntitys(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appOrderNumberList", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增页面获取所有车辆号码
	@RequestMapping(value = {"/getAppPlateNumberList.json"})
	public ResponseEntity<Map<String, Object>> getAppPlateNumberList(@RequestParam(value="ckTime")String ckTime,@RequestParam(value="id1")String id1,@RequestParam(value="customername")String customername,@RequestParam(value="plateNumber")String plateNumber,@RequestParam(value="Id")String Id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("ckTime",ckTime);
    	param.put("Id",Id);
    	param.put("id1",id1);
    	param.put("customername",customername);
    	param.put("plateNumber",plateNumber);
    	List<Map<String, Object>> appInfoList = repertoryService.getAllPlateNumbers(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appPlateNumberList", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增页面获取所有客户名称
	@RequestMapping(value = {"/getAppCustomerNameList.json"})
	public ResponseEntity<Map<String, Object>> getAppCustomerNameList(@RequestParam(value="ckTime")String ckTime,@RequestParam(value="id1")String id1,@RequestParam(value="customername")String customername,@RequestParam(value="plateNumber")String plateNumber,@RequestParam(value="Id")String Id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("ckTime",ckTime);
    	param.put("Id",Id);
    	param.put("id1",id1);
    	param.put("customername",customername);
    	param.put("plateNumber",plateNumber);
    	List<Map<String, Object>> appInfoList = repertoryService.getAllCustomerName(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appCustomerNameList", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增页面获取所有出库单
	@RequestMapping(value = {"/getAppOutboundList.json"})
	public ResponseEntity<Map<String, Object>> getAppOutboundList(@RequestParam(value="ckTime")String ckTime,@RequestParam(value="id1")String id1,@RequestParam(value="customername")String customername,@RequestParam(value="plateNumber")String plateNumber,@RequestParam(value="Id")String Id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("ckTime",ckTime);
    	param.put("Id",Id);
    	param.put("id1",id1);
    	param.put("customername",customername);
    	param.put("plateNumber",plateNumber);
    	List<Map<String, Object>> appInfoList = repertoryService.getAllOutbounds(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appOutboundList", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增页面查询销售订单明细
	@RequestMapping(value = {"/getAppOrderDetailInfo.json"})
	public ResponseEntity<Map<String, Object>> getAppOrderDetailInfo(@RequestParam(value="salesOrderId")String salesOrderId,@RequestParam(value="id")String id){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("id",id);
    	param.put("salesOrderId",salesOrderId);
    	List<Map<String, Object>> appInfoList = repertoryService.getOrderDetailInfo(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appOrderDetailInfo", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//调拨未称重信息
	@RequestMapping(value = {"/getAppDiaoBoNoWeighList.json"})
	public ResponseEntity<Map<String, Object>> getAppDiaoBoNoWeighList(@RequestParam(value="salesOrderId")String salesOrderId){
		// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("salesOrderId",salesOrderId);
    	List<Map<String, Object>> appInfoList = repertoryService.getDiaoBoNoWeighList(param);
    	//响应数据
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appDiaoBoNoWeighList", appInfoList);
	    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//新增+复制
	@RequestMapping(value = {"/addAppNoWeigh.json"})
	public ResponseEntity<Map<String, Object>> addAppNoWeigh(HttpServletRequest request,@RequestParam(value="noWeighOutWarehouse")String noWeighOutWarehouse,@RequestParam(value="userId")String userId) throws Exception{
		//String noWeighOutWarehouse = "[{\"customername\":\"app测试\"},{\"customerAlias\":\"app测试别称\"}]";
		//参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		//将字符串直接转换成对象（将json的格式转换成数字的格式）
		List<HashMap> mapList = JSON.parseArray(noWeighOutWarehouse,HashMap.class);
		map.put("userId",userId);
		// 流水号
		map.put("serialID", mapList.get(0).get("serialID").toString());
		//销售订单编号
		map.put("orderNumber", Integer.parseInt(mapList.get(1).get("orderNumber").toString()));
		//销售订单明细
		map.put("orderDetailedId", Integer.parseInt(mapList.get(2).get("orderDetailedId").toString()));
		//客户别称
		map.put("customerAlias", mapList.get(3).get("customerAlias").toString());
		//客户名称
		map.put("customername", mapList.get(4).get("customername").toString());
		//是否随车带走
		map.put("isCarryOff", Integer.parseInt(mapList.get(5).get("isCarryOff").toString()));
		//车牌号码
		map.put("plateNumber", mapList.get(6).get("plateNumber").toString());
		//是否客户自提
		map.put("isSelfLifting", Integer.parseInt(mapList.get(7).get("isSelfLifting").toString()));
		//选择出库单
		map.put("dispatchOutWarehouseId", Integer.parseInt(mapList.get(8).get("dispatchOutWarehouseId").toString()));
		//送货人
		map.put("deliverer", mapList.get(9).get("deliverer").toString());
		//送货电话
		map.put("deliveryPhone", mapList.get(10).get("deliveryPhone").toString());
		//物料名称/型号
		map.put("materielInfoId", Integer.parseInt(mapList.get(11).get("materielInfoId").toString()));
		//税率
		map.put("taxRate", mapList.get(12).get("taxRate").toString());
		//邮单编号
		map.put("postNumber", mapList.get(13).get("postNumber").toString());
		//销售数量
		map.put("saleNumber", mapList.get(14).get("saleNumber").toString());
		//其他备注
		map.put("otherRemarks", mapList.get(15).get("otherRemarks").toString());
		//销售单价
		map.put("salePrice", mapList.get(16).get("salePrice").toString());
		//它发货方式
		map.put("otherDelivery", mapList.get(17).get("otherDelivery").toString());
		//销售金额
		map.put("saleMoney", mapList.get(18).get("saleMoney").toString());
		//车辆信息
		map.put("customerCarName", mapList.get(19).get("customerCarName").toString());
		//收货人
		map.put("consignee", mapList.get(20).get("consignee").toString());
		//结算情况
		map.put("transportBalance", mapList.get(21).get("transportBalance").toString());
		//收货地址
		map.put("address", mapList.get(22).get("address").toString());
		//联系电话
		map.put("consigneePhone", mapList.get(23).get("consigneePhone").toString());
		//是否需要检测
		map.put("isTesting", Integer.parseInt(mapList.get(24).get("isTesting").toString()));
		//备注
		map.put("remarks", mapList.get(25).get("remarks").toString());
		repertoryService.addNoWeighOutWarehouse(request, map);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//修改
	@RequestMapping(value = {"/updateAppNoWeigh.json"})
	public ResponseEntity<Map<String, Object>> updateAppNoWeigh(HttpServletRequest request,@RequestParam(value="noWeighOutWarehouse")String noWeighOutWarehouse,@RequestParam(value="id")String id,@RequestParam(value="userId")String userId) throws Exception{
		//String noWeighOutWarehouse = "[{\"id\":85},{\"customername\":\"app测试修改\"},{\"customerAlias\":\"app测试别称修改\"}]";
		//参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap> mapList = JSON.parseArray(noWeighOutWarehouse,HashMap.class);
		map.put("id", id);
		map.put("userId",userId);
		// 流水号
		map.put("serialID", mapList.get(0).get("serialID").toString());
		//销售订单编号
		map.put("orderNumber", Integer.parseInt(mapList.get(1).get("orderNumber").toString()));
		//销售订单明细
		map.put("orderDetailedId", Integer.parseInt(mapList.get(2).get("orderDetailedId").toString()));
		//客户别称
		map.put("customerAlias", mapList.get(3).get("customerAlias").toString());
		//客户名称
		map.put("customername", mapList.get(4).get("customername").toString());
		//是否随车带走
		map.put("isCarryOff", Integer.parseInt(mapList.get(5).get("isCarryOff").toString()));
		//车牌号码
		map.put("plateNumber", mapList.get(6).get("plateNumber").toString());
		//是否客户自提
		map.put("isSelfLifting", Integer.parseInt(mapList.get(7).get("isSelfLifting").toString()));
		//选择出库单
		map.put("dispatchOutWarehouseId", Integer.parseInt(mapList.get(8).get("dispatchOutWarehouseId").toString()));
		//送货人
		map.put("deliverer", mapList.get(9).get("deliverer").toString());
		//送货电话
		map.put("deliveryPhone", mapList.get(10).get("deliveryPhone").toString());
		//物料名称/型号
		map.put("materielInfoId", Integer.parseInt(mapList.get(11).get("materielInfoId").toString()));
		//税率
		map.put("taxRate", mapList.get(12).get("taxRate").toString());
		//邮单编号
		map.put("postNumber", mapList.get(13).get("postNumber").toString());
		//销售数量
		map.put("saleNumber", mapList.get(14).get("saleNumber").toString());
		//其他备注
		map.put("otherRemarks", mapList.get(15).get("otherRemarks").toString());
		//销售单价
		map.put("salePrice", mapList.get(16).get("salePrice").toString());
		//它发货方式
		map.put("otherDelivery", mapList.get(17).get("otherDelivery").toString());
		//销售金额
		map.put("saleMoney", mapList.get(18).get("saleMoney").toString());
		//车辆信息
		map.put("customerCarName", mapList.get(19).get("customerCarName").toString());
		//收货人
		map.put("consignee", mapList.get(20).get("consignee").toString());
		//结算情况
		map.put("transportBalance", mapList.get(21).get("transportBalance").toString());
		//收货地址
		map.put("address", mapList.get(22).get("address").toString());
		//联系电话
		map.put("consigneePhone", mapList.get(23).get("consigneePhone").toString());
		//是否需要检测
		map.put("isTesting", Integer.parseInt(mapList.get(24).get("isTesting").toString()));
		//备注
		map.put("remarks", mapList.get(25).get("remarks").toString());
		repertoryService.updateNoWeighOutWarehouse(request, map);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//删除
	@RequestMapping(value = {"deleteAppNoWeigh.json"})
	public ResponseEntity<Map<String, Object>> deleteAppNoWeigh(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="userId")String userId) throws IOException{
		//参数
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		repertoryService.updateValidFlag(request, map);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
}
