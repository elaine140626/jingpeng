package com.oil.controller.system;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.system.Startaddress;
import com.oil.model.system.Transports;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.service.system.CustomerTransportsService;

@Controller
@RequestMapping("/Transports")
public class CustomerTransportsController {
	@Autowired
	private CustomerTransportsService customerTransportsService;
	//客户止运地ztree
	@RequestMapping("/getTransports_Tree.action")
	@ResponseBody
	public Map<String,Object> getClient_back_Tree(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = customerTransportsService.getTransports_Tree();
		map.put("list", list);
		return map;
	}
	//添加客户止运地
	@RequestMapping("/addTransports.action")
	@ResponseBody
	public ResponseInfo addClient_back(@RequestBody Transports transports) {
		
		return customerTransportsService.addTransports(transports);
	}
	
	//添加客户止运地
	@RequestMapping("/addStartTransports.action")
	@ResponseBody
	public ResponseInfo addStartTransports(@RequestBody Startaddress startaddress) {
		
		return customerTransportsService.addStartTransports(startaddress);
	}
		
	//修改客户止运地
	@RequestMapping("/updateTransports.action")
	@ResponseBody
	public ResponseInfo updateTransports(@RequestBody Transports transports) {
		
		return customerTransportsService.updateTransports(transports);
	}
	
	//修改客户止运地
	@RequestMapping("/updateStartTransports.action")
	@ResponseBody
	public ResponseInfo updateStartTransports(@RequestBody Startaddress startaddress) {
		
		return customerTransportsService.updateStartTransports(startaddress);
	}
	
	//获取全部客户止运地信息
	@RequestMapping("/getTransports.action")
	@ResponseBody
	public DataTablesResponseInfo getTransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		
		return customerTransportsService.getTransports(map);
	}
	
    //获取全部起运地信息
	@RequestMapping("/getStartTransports.action")
	@ResponseBody
	public DataTablesResponseInfo getStartTransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return customerTransportsService.getStartTransports(map);
	}
	//删除客户止运地
	@RequestMapping("/delTransports.action")
	@ResponseBody
	public ResponseInfo delClient_back(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return customerTransportsService.delTransports(id);
	}
	
	//删除客户止运地
	@RequestMapping("/delStartTransports.action")
	@ResponseBody
	public ResponseInfo delStartTransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return customerTransportsService.delStartTransports(id);
	}
	
	//根据客户名称模糊查询
	@RequestMapping("/findCustomerinfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findCustomerinfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String customerName = map.get("customerNames").toString();
		return customerTransportsService.findCustomerinfoByName(customerName);
	}
	
	//根据客户名称模糊查询
	@RequestMapping("/findStartCustomerinfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findStartCustomerinfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String customerName = map.get("customerNames").toString();
		return customerTransportsService.findStartCustomerinfoByName(customerName);
	}
	//客户菜单根据父子节点查询
	@RequestMapping("/findByNameAndId.action")
	public @ResponseBody DataTablesResponseInfo findByNameAndId(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		return customerTransportsService.findByNameAndId(map);
	}
	
	//修改功能查询方法
	@RequestMapping("/getTransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getClient_backById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return customerTransportsService.getTransportsById(id);
	}
	
	//根据起运地信息id查询有无关联的来料加工
	@RequestMapping("/getStartTransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getStartTransportsById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return customerTransportsService.getStartTransportsById(id);
	}
	
	//获取没存入止运地的客户信息
	@RequestMapping("/getCustomerInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomerInfo() {
		return customerTransportsService.getCustomerInfo();
	}
	//根据客户Id获取止运地信息
	@RequestMapping("/getTrchangeInfo.action")
	@ResponseBody
	public Map<String,Object> getContractTree(@RequestParam HashMap<String, Object> map){
		int id = Integer.parseInt(map.get("id").toString());
		List<Map<String,Object>> list = customerTransportsService.getTrchangeInfo(id);
		map.put("list", list);
		return map;
	}
	
	//查询全部回访形式信息数据 
	@RequestMapping("/getVisitContent.action")
	@ResponseBody
	public DataTablesResponseInfo getVisitContent() {
		return customerTransportsService.getVisitContent();
	}
	
	//查询全部销售员信息数据
	@RequestMapping("/getUserInfoByCode.action")
	@ResponseBody
	public DataTablesResponseInfo getUserInfoByCode() {
		return customerTransportsService.getUserInfoByCode();
	}
	
	//查询用户信息ztree
	@RequestMapping("/getClient_back_List.action")
	@ResponseBody
	public Map<String,Object> getClient_back_List(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = customerTransportsService.getClient_back_List();
		map.put("list", list);
		return map;
	}
	
	//删除校验信息查询
	@RequestMapping("/getAllTransports.action")
	@ResponseBody
	public List<Map<String, Object>> getAllTransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return customerTransportsService.getAllTransports(map);
	}
}
