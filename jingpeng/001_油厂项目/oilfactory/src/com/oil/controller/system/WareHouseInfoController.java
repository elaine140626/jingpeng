package com.oil.controller.system;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.system.WareHouseInfoService;

@Controller
@RequestMapping("/WareHouse")
public class WareHouseInfoController {
	
	@Autowired
	private WareHouseInfoService wareHouseInfoService;

	//添加仓库信息
	@RequestMapping("/addWareHouseInfo.action")
	@ResponseBody
	public ResponseInfo addWareHouseInfo(@RequestBody WareHouseInfo wareHouseInfo) {
		
		return wareHouseInfoService.addWareHouseInfo(wareHouseInfo);
	}
	
	//添加大罐信息数据
	@RequestMapping("/addWareHouseInfoTank.action")
	@ResponseBody
	public ResponseInfo addWareHouseInfoTank(@RequestBody WareHouseInfo wareHouseInfo) {
		
		return wareHouseInfoService.addWareHouseInfoTank(wareHouseInfo);
	}
	
	//查询全部仓库信息数据
	@RequestMapping("/getLevel.action")
	@ResponseBody
	public DataTablesResponseInfo getLevel() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Datadictionary> DatadictionaryList = wareHouseInfoService.getLevel();
		info.setData(DatadictionaryList);
		return info;
	}
	
	//查询仓库信息数据
	@RequestMapping("/getWareHouseInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getWareHouseInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {

		return wareHouseInfoService.getWareHouseInfo();
	}
	
	//修改仓库信息
	@RequestMapping("/updateWareHouseInfo.action")
	@ResponseBody
	public ResponseInfo updateWareHouseInfo(@RequestBody WareHouseInfo wareHouseInfo) {

		return wareHouseInfoService.updateWareHouseInfo(wareHouseInfo);
	}
	
	//修改大罐信息数据
	@RequestMapping("/updateWareHouseInfoTank.action")
	@ResponseBody
	public ResponseInfo updateWareHouseInfoTank(@RequestBody WareHouseInfo wareHouseInfo) {

		return wareHouseInfoService.updateWareHouseInfoTank(wareHouseInfo);
	}
	
	//删除仓库信息
	@RequestMapping("/delWareHouseInfo.action")
	@ResponseBody
	public ResponseInfo delWareHouseInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		WareHouseInfo wareHouseInfo = wareHouseInfoService.getWareHouseInfoByid(id);
		wareHouseInfo.setIsDel(1);
		return wareHouseInfoService.delWareHouseInfo(wareHouseInfo);
	}
	
	//通过仓库信息id 查询仓库信息
	@RequestMapping("/getWareHouseInfoByid.action")
	@ResponseBody
	public DataTablesResponseInfo getWareHouseInfoByid(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		int id = Integer.parseInt(map.get("id").toString());
		info.setData(wareHouseInfoService.getWareHouseInfoByid(id));
		return info;
	}
	
	//根据仓库名称模糊查询 
	@RequestMapping("/findWareHouseInfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findWareHouseInfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String warehouseName = map.get("warehouseName").toString();
		return wareHouseInfoService.findWareHouseInfoByName(warehouseName);
	}
}
