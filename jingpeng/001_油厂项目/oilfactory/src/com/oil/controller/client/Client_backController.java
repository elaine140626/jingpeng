package com.oil.controller.client;

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

import com.oil.model.client.Client_back;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.client.Client_backService;

@Controller
@RequestMapping("/Client_back")
public class Client_backController {
	@Autowired
	private Client_backService client_backService;
	//获取回访记录客户ztree树
	@RequestMapping("/getClient_back_Tree.action")
	@ResponseBody
	public Map<String,Object> getClient_back_Tree(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = client_backService.getClient_back_Tree();
		map.put("list", list);
		return map;
	}
	//新增回访记录
	@RequestMapping("/addClient_back.action")
	@ResponseBody
	public ResponseInfo addClient_back(@RequestBody Client_back client_back) {
		
		return client_backService.addClient_back(client_back);
	}
	//修改回访记录
	@RequestMapping("/updateClient_back.action")
	@ResponseBody
	public ResponseInfo updateClient_back(@RequestBody Client_back client_back) {
		
		return client_backService.updateClient_back(client_back);
	}
	//删除回访记录
	@RequestMapping("/delClient_back.action")
	@ResponseBody
	public ResponseInfo delClient_back(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return client_backService.delClient_back(id);
	}
	
	//客户评价下拉列表内容查询
	@RequestMapping("/getVisitContent.action")
	@ResponseBody
	public DataTablesResponseInfo getVisitContent() {
		
		return client_backService.getVisitContent();
	}
	
	//客户名称下拉列表内容查询
	@RequestMapping("/getCustomerInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomerInfo() {
		
		return client_backService.getCustomerInfo();
	}
	
	//根据客户名称查询客户信息
	@RequestMapping("/findCustomerinfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findCustomerinfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String customerName = map.get("customerNames").toString();
		return client_backService.findCustomerinfoByName(customerName);
	}
	//回访记录列表查询
	@RequestMapping("/getClient_back.action")
	@ResponseBody
	public DataTablesResponseInfo getClient_back() {
		
		return client_backService.getClient_back();
	}
	//获取客户名称
	@RequestMapping("/getUserInfoByCode.action")
	@ResponseBody
	public DataTablesResponseInfo getUserInfoByCode() {
		
		return client_backService.getUserInfoByCode();
	}
	//根据id查询回访记录
	@RequestMapping("/getClient_backById.action")
	@ResponseBody
	public DataTablesResponseInfo getClient_backById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return client_backService.getClient_backById(id);
	}
	//客户菜单根据父子节点查询
	@RequestMapping("/findByNameAndId.action")
	public @ResponseBody DataTablesResponseInfo findByNameAndId(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		return client_backService.findByNameAndId(map);
	}
	//查询用户信息ztree
	@RequestMapping("/getClient_back_List.action")
	@ResponseBody
	public Map<String,Object> getClient_back_List(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = client_backService.getClient_back_List();
		map.put("list", list);
		return map;
	}
}
