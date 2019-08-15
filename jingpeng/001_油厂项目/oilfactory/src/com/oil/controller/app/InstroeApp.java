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
import com.oil.service.dispath.InstroeService;

@Controller
@RequestMapping("/instroeApp")
public class InstroeApp {
	
	@Autowired
	InstroeService instroeService;
	
	// 调度管理：调度入库单树形结构名称下拉
    @RequestMapping(value = { "/getStoragemeasure.json" })
    public ResponseEntity<Map<String, Object>> getStoragemeasure(){
    	List<Map<String, Object>> contractEntityList=instroeService.getStoragemeasure();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractEntityList", contractEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：调度入库单树形结构合同编号下拉
//    @RequestMapping(value = { "/getSalesContractLists.json" })
//    public ResponseEntity<Map<String, Object>> getSalesContractLists(@RequestParam(value="contractId")String contractId){
//    	Map<String, Object> param = new HashMap<String, Object>();
//    	param.put("contractId", contractId);
//    	List<Map<String, Object>> salesContractEntityList=instroeService.getSalesContractLists(param);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("salesContractEntityList", salesContractEntityList);
//        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
//    }
    
    // 调度管理：调度入库单车辆检下拉
//    @RequestMapping(value = { "/getPlateNumberList.json" })
//    public ResponseEntity<Map<String, Object>> getPlateNumberList(){
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	List<String> plateNumberList= instroeService.getPlateNumberList(map);
//        map.put("plateNumberList", plateNumberList);
//        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
//    }
    
    // 调度管理：调度入库单按客户名称和订单编号搜索
//    @RequestMapping(value = { "/searchByNameOrderNumber.json" })
//    public ResponseEntity<Map<String, Object>> searchByNameOrderNumber(@RequestParam(value="client")String client,@RequestParam(value="orderNumber")String orderNumber){
//    	HashMap<String, Object> param = new HashMap<String, Object>();
//    	param.put("client", client);
//    	param.put("orderNumber", orderNumber);
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	DataTablesResponseInfo instroeEntityList= instroeService.getInfoList(param);
//        map.put("instroeEntityList", instroeEntityList);
//        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
//    }
    
    // 调度管理：调度入库单按车牌号和时间搜索
    @RequestMapping(value = { "/searchByPlateNumberTime.json" })
    public ResponseEntity<Map<String, Object>> searchByPlateNumberTime(@RequestParam(value="client")String client,@RequestParam(value="plateNumbers")String plateNumbers,
    		@RequestParam(value="startTime")String startTime,@RequestParam(value="endTime")String endTime){
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("client", client);
    	param.put("plateNumbers", plateNumbers);
    	param.put("startTime", startTime);
    	param.put("endTime", endTime);
    	Map<String, Object> map = new HashMap<String, Object>();
    	DataTablesResponseInfo instroeEntityList= instroeService.getInfoList(param);
        map.put("instroeEntityList", instroeEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：调度入库单列表
    @RequestMapping(value = { "/getInfoList.json" })
    public ResponseEntity<Map<String, Object>> getInfoList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	DataTablesResponseInfo instroeEntityList= instroeService.getInfoList(map);
        map.put("instroeEntityList", instroeEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：根据id查询调度出库单
    @RequestMapping(value = { "/getInfoListById.json" })
    public ResponseEntity<Map<String, Object>> getInfoListById(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	DataTablesResponseInfo instroeEntityList= instroeService.getInfoList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("instroeEntityList", instroeEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：新增调度入库单，enterTypeMark=0：入库单新增 ，enterTypeMark=2：来料加工新增
    @RequestMapping(value = { "/addStoragemeasure.json" })
    public ResponseEntity<Map<String, Object>> addStoragemeasure(HttpServletRequest request,@RequestParam(value="enterTypeMark")String enterTypeMark,@RequestParam(value="userId")String userId,
    		@RequestParam(value="productID")String productID,@RequestParam(value="instroeEntity")String instroeEntity) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(instroeEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("enterTypeMark", enterTypeMark);
    	if (enterTypeMark.equals("0")) {
        	param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("purchaseContractId", maplist.get(1).get("purchaseContractId"));
        	param.put("plateNumber",maplist.get(2).get("plateNumber"));
        	param.put("cartWeighType",maplist.get(3).get("cartWeighType"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("amount",maplist.get(6).get("amount"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("priority",maplist.get(9).get("priority"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}else if(enterTypeMark.equals("2")) {
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("materielName", maplist.get(1).get("materielName"));
        	param.put("materielModel",maplist.get(2).get("materielModel"));
        	param.put("contractId",maplist.get(3).get("contractId"));
        	param.put("plateNumber",maplist.get(4).get("plateNumber"));
        	param.put("client",maplist.get(5).get("client"));
        	param.put("customerAlias",maplist.get(6).get("customerAlias"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("cartWeighType",maplist.get(9).get("cartWeighType"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}
    	instroeService.addImportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：复制调度入库单，enterTypeMark=0：入库单新增 ，enterTypeMark=2：来料加工新增
    @RequestMapping(value = { "/copyStoragemeasure.json" })
    public ResponseEntity<Map<String, Object>> copyStoragemeasure(HttpServletRequest request,@RequestParam(value="userId")String userId,
    		@RequestParam(value="productID")String productID,@RequestParam(value="enterTypeMark")String enterTypeMark,@RequestParam(value="instroeEntity")String instroeEntity) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(instroeEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("enterTypeMark", enterTypeMark);
    	if (enterTypeMark.equals("0")) {
        	param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("purchaseContractId", maplist.get(1).get("purchaseContractId"));
        	param.put("plateNumber",maplist.get(2).get("plateNumber"));
        	param.put("cartWeighType",maplist.get(3).get("cartWeighType"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("amount",maplist.get(6).get("amount"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("priority",maplist.get(9).get("priority"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}else if(enterTypeMark.equals("2")) {
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("materielName", maplist.get(1).get("materielName"));
        	param.put("materielModel",maplist.get(2).get("materielModel"));
        	param.put("contractId",maplist.get(3).get("contractId"));
        	param.put("plateNumber",maplist.get(4).get("plateNumber"));
        	param.put("client",maplist.get(5).get("client"));
        	param.put("customerAlias",maplist.get(6).get("customerAlias"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("cartWeighType",maplist.get(9).get("cartWeighType"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}
    	instroeService.addImportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：修改调度入库单，enterTypeMark=0：入库单新增 ，enterTypeMark=2：来料加工新增
    @RequestMapping(value = { "/updateStoragemeasure.json" })
    public ResponseEntity<Map<String, Object>> updateStoragemeasure(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="userId")String userId,
    		@RequestParam(value="productID")String productID,@RequestParam(value="enterTypeMark")String enterTypeMark,@RequestParam(value="instroeEntity")String instroeEntity) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(instroeEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("id",id);
    	param.put("enterTypeMark", enterTypeMark);
    	if (enterTypeMark.equals("0")) {
        	param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("purchaseContractId", maplist.get(1).get("purchaseContractId"));
        	param.put("plateNumber",maplist.get(2).get("plateNumber"));
        	param.put("cartWeighType",maplist.get(3).get("cartWeighType"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("amount",maplist.get(6).get("amount"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("priority",maplist.get(9).get("priority"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}else if(enterTypeMark.equals("2")) {
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("materielName", maplist.get(1).get("materielName"));
        	param.put("materielModel",maplist.get(2).get("materielModel"));
        	param.put("contractId",maplist.get(3).get("contractId"));
        	param.put("plateNumber",maplist.get(4).get("plateNumber"));
        	param.put("client",maplist.get(5).get("client"));
        	param.put("customerAlias",maplist.get(6).get("customerAlias"));
        	param.put("deliveryMan",maplist.get(7).get("deliveryMan"));
        	param.put("deliveryManPhone",maplist.get(8).get("deliveryManPhone"));
        	param.put("cartWeighType",maplist.get(9).get("cartWeighType"));
        	param.put("remarks",maplist.get(10).get("remarks"));
		}
    	instroeService.updateImportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
 // 调度管理：作废或删除 ,flag =0作 废 ,flag = 1删除
    @RequestMapping(value = { "/updateValidFlag.json" })
    public ResponseEntity<Map<String, Object>> updateValidFlag(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="flag")String flag,
    		@RequestParam(value="userId")String userId,@RequestParam(value="cancellationCause")String cancellationCause) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("id",id);
    	param.put("flag",flag);
    	param.put("userId",userId);
    	param.put("cancellationCause",cancellationCause);
    	instroeService.updateValidFlag(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
}
