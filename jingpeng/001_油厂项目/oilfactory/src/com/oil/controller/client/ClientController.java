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

import com.oil.model.Arrears;
import com.oil.model.CityInfo;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.Provinceinfo;
import com.oil.service.client.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	//查询所有客户信息
	@RequestMapping("/getclient.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomerInfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Customerinfo> list = clientService.getCustomerInfoList(map);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setRownum(i+1);
			}
		}
		dInfo.setData(list);
		return dInfo;
	}
	
	//添加客户信息
	@RequestMapping("/addCusttomerinfo.action")
	@ResponseBody
	public ResponseInfo addCusttomerinfo(HttpServletRequest request,Customerinfo customerinfo) {
		ResponseInfo info = new ResponseInfo();
		int result = clientService.addCustomerinfo(customerinfo);
		if(result > 0) {
			info.setMessage("添加成功");
			info.setCode("success");
			info.setSid(customerinfo.getUuid());
		}else {
			info.setMessage("添加失败");
			info.setCode("error");
		}
		return info;
	}
	
	//通过客户名称模糊查询客户信息
	@RequestMapping("/findCustomerinfo.action")
	@ResponseBody
	public DataTablesResponseInfo findCustomerinfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		//获取客户的名称
		String customername = map.get("customername").toString();
		List<Customerinfo> list = clientService.findCustomerinfo(customername);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setRownum(i+1);
			}
		}
		dtri.setData(list);
		return dtri;
	}
	
	//通过主键查询一条客户信息
	@RequestMapping("/findCustomerinfoById.action")
	@ResponseBody
	public Customerinfo findCustomerinfoById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int uuid = Integer.valueOf(map.get("uuid").toString());
		System.out.println("++++++++"+uuid);
		Customerinfo findCustomerinfoById = clientService.findCustomerinfoById(uuid);
		return findCustomerinfoById;
	}
	
	//修改客户信息
	@RequestMapping("/updateCustomerinfo.action")
	@ResponseBody
	public ResponseInfo updateCustomerinfo(HttpServletRequest request,Customerinfo customerinfo){
		ResponseInfo info = new ResponseInfo();
		int result = clientService.updateCustomerinfo(customerinfo);
		if(result > 0) {
			info.setMessage("修改成功");
			info.setCode("success");
			info.setSid(customerinfo.getUuid());
		}else {
			info.setMessage("修改失败");
			info.setCode("error");
		}
		return info;
	}
	
	//删除客户信息
	@RequestMapping("/delCustomerinfoById.action")
	public @ResponseBody ResponseInfo delCustomerinfoById(HttpServletRequest request,@RequestParam int uuid) {
		ResponseInfo info = new ResponseInfo();
		//删除客户明细
		info =  clientService.delCustomerinfoById(uuid);		
		return info;
	}
	
	//查询数据字典等级
	@RequestMapping("/getDatadictionaryList.action")
	@ResponseBody
	public List<Datadictionary> getDatadictionaryList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return clientService.getDatadictionaryList();
	}
	
	//查询省
	@RequestMapping("/getProvinceinfoList.action")
	@ResponseBody
	public List<Provinceinfo> getProvinceinfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return clientService.getProvinceinfoList();
	}
	
	//查询市
	@RequestMapping("/getCityInfoList.action")
	@ResponseBody
	public List<CityInfo> getCityInfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		int ProvinceId = Integer.parseInt(map.get("id").toString());
		return clientService.getCityInfoList(ProvinceId);
	}
	
	//添加欠款明细
	@RequestMapping("/addArrears.action")
	@ResponseBody
	public ResponseInfo addArrears(@RequestBody List<Arrears> arrears) {
		ResponseInfo info = new ResponseInfo();
			if(arrears.size()!=0) {			
				for (int i = 0; i < arrears.size(); i++) {
					arrears.get(i).setIsDel(0);
				}
				int result = 0;
				for (int i = 0; i < arrears.size(); i++) {
					result = clientService.addArrears(arrears.get(i));
					if(result<0) {
						break;
					}
				}
				if(result>0) {
					info.setCode("200");
					info.setMessage("添加成功");
				}else {
					info.setCode("500");
					info.setMessage("添加失败");
				}
			}else {
				info.setCode("200");
				info.setMessage("添加成功");
			}
		return info;
	}
	
	//修改欠款明细
	@RequestMapping("/updateArrears.action")
	@ResponseBody
	public ResponseInfo updateArrears(@RequestBody List<Arrears> arrears) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		boolean isDel = true;
		boolean isAdd = true;
		for (int i = 0; i < arrears.size(); i++) {
			result = clientService.updateArrears(arrears.get(i).getCustomerId());
			if(result<0) {
				isDel=false;
				break;
			}
		}
		if(isDel) {
			for (int i = 0; i < arrears.size(); i++) {
				result = clientService.addArrears(arrears.get(i));
				if(result<0) {
					isAdd=false;
					break;
				}
			}
			if(isAdd){
				info.setCode("200");
				info.setMessage("添加成功");
			}else {
				info.setCode("500");
				info.setMessage("添加失败");
			}
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	//删除欠款明细(逻辑删)
	@RequestMapping("/delArrearsInfoById.action")
	@ResponseBody
	public ResponseInfo delArrearsInfoById(HttpServletRequest request,@RequestParam int id) {
		ResponseInfo info = new ResponseInfo();
		int result = clientService.updateArrearsInfo(id);
		if(result > 0){
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	
	//通过id查询所在的市
	@RequestMapping("/getCityInfo.action")
	@ResponseBody
	public CityInfo getCityInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int id = Integer.parseInt(map.get("city").toString());
		CityInfo cityInfo = clientService.getCityInfo(id);
		return cityInfo;
	}
	
	//通过市查询所在的省
	@RequestMapping("/getProvinceinfo.action")
	@ResponseBody
	public Provinceinfo getProvinceinfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int provinceId = Integer.parseInt(map.get("id").toString());
		Provinceinfo provinceinfo = clientService.getProvinceinfo(provinceId);
		return provinceinfo;
	}
	
	//通过客户查询欠款明细
	@RequestMapping("/getArrears.action")
	@ResponseBody
	public List<Arrears> getArrears(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		int customerId = Integer.parseInt(map.get("customerId").toString());
		List<Arrears> arrears = clientService.getArrears(customerId);
		return arrears;
	}
	//客户菜单根据父子节点查询
		@RequestMapping("/findByNameAndId.action")
		public @ResponseBody DataTablesResponseInfo findByNameAndId(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
			return clientService.findByNameAndId(map);
		}
		
	//客户名称去重
	@RequestMapping("/getCustomerName.action")
	@ResponseBody
	public List<Customerinfo> getCustomerName(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Customerinfo> list = clientService.getCustomerInfoList(map);
		return list;
	}
	//查询用户信息ztree
	@RequestMapping("/getClient_back.action")
	@ResponseBody
	public Map<String,Object> getClient_back(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = clientService.getClient_back();
		map.put("list", list);
		return map;
	}
}
