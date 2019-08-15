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
import com.oil.service.client.ClientVisitService;

@Controller
@RequestMapping("/ClientVisit")
public class ClientVisitController {

	@Autowired
	private ClientVisitService clientVisitInfoService;
	//新增拜访记录
	@RequestMapping("/addClientVisit.action")
	@ResponseBody
	public ResponseInfo addClientVisit(@RequestBody Client_back client_back) {
		
		return clientVisitInfoService.addClientVisit(client_back);
	}
	//修改拜访记录
	@RequestMapping("/updateClientVisit.action")
	@ResponseBody
	public ResponseInfo updateClientVisit(@RequestBody Client_back client_back) {
		
		return clientVisitInfoService.updateClientVisit(client_back);
	}
	//删除拜访记录
	@RequestMapping("/delClientVisit.action")
	@ResponseBody
	public ResponseInfo delClientVisit(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return clientVisitInfoService.delClientVisit(id);
	}
	//获取拜访信息
	@RequestMapping("/getVisitContent.action")
	@ResponseBody
	public DataTablesResponseInfo getVisitContent() {
		
		return clientVisitInfoService.getVisitContent();
	}
	//客户名称下拉列表内容查询
	@RequestMapping("/getCustomerInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomerInfo() {
		
		return clientVisitInfoService.getCustomerInfo();
	}
	//根据客户名称获取客户信息
	@RequestMapping("/findCustomerinfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findCustomerinfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String customerName = map.get("customerNames").toString();
		return clientVisitInfoService.findCustomerinfoByName(customerName);
	}
	
	//客户菜单根据父子节点查询
	@RequestMapping("/findByNameAndId.action")
	public @ResponseBody DataTablesResponseInfo findByNameAndId(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		return clientVisitInfoService.findByNameAndId(map);
	}
	//拜访记录列表查询
	@RequestMapping("/getClientVisit.action")
	@ResponseBody
	public DataTablesResponseInfo getClientVisit() {
		
		return clientVisitInfoService.getClientVisit();
	}
	//根据id查询拜访记录信息
	@RequestMapping("/getClientVisitById.action")
	@ResponseBody
	public DataTablesResponseInfo getClientVisitById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("id").toString());
		return clientVisitInfoService.getClientVisitById(id);
	}
	//查询用户信息ztree
	@RequestMapping("/getClient_back.action")
	@ResponseBody
	public Map<String,Object> getClient_back(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = clientVisitInfoService.getClient_back();
		map.put("list", list);
		return map;
	}
	
	
}
