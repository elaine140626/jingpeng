package com.oil.controller.system;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.Provider;
import com.oil.service.system.ProviderService;


@Controller
@RequestMapping("/Provider")
public class ProviderController {

	@Autowired
	private ProviderService ProviderService;
	
	//查询所有供应商信息
	@RequestMapping("/getProvider.action")
	public @ResponseBody DataTablesResponseInfo getProvider(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<Provider> list = ProviderService.getProvider();
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setSerialnumber(i+1);
		}
		dtri.setData(list);
		return dtri;
	}
	//添加供应商信息
	@RequestMapping("/addProvider.action")
	public @ResponseBody ResponseInfo addProvider(HttpServletRequest request,Provider Provider) {
		ResponseInfo info = new ResponseInfo();
		int result =ProviderService.addProvider(Provider);
		if(result>0) {
			info.setMessage("添加成功");
			info.setCode("success");
		}else {
			info.setMessage("添加失败");
			info.setCode("error");
		}
		return info;
		}
	//修改供应商信息
	@RequestMapping("/updateProvider.action")
	public @ResponseBody ResponseInfo updateProvider(HttpServletRequest request,Provider Provider){
		ResponseInfo Info = new ResponseInfo();
		int result = ProviderService.updateProvider(Provider);
		if(result>0) {
			Info.setMessage("修改成功");
			Info.setCode("success");
		}else {
			Info.setMessage("修改失败");
			Info.setCode("error");
		}
		return Info;
	}
	//删除供应商信息
	@RequestMapping("/delProviderById.action")
	public @ResponseBody ResponseInfo delUserInfo(HttpServletRequest request,@RequestParam int id) {
		ResponseInfo Info = new ResponseInfo();
		int result =  ProviderService.delProviderById(id);
		if(result>0) {
			Info.setMessage("删除成功");
			Info.setCode("success");
		}else {
			Info.setMessage("删除失败");
			Info.setCode("error");
		}
		return Info;
	}
	//修改功能查询供应商信息
	@RequestMapping("/findProviderById.action")
	public @ResponseBody Provider findProviderByid(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		int id = Integer.parseInt(map.get("id").toString());
		Provider data = ProviderService.findProviderByid(id);
		return data;
	}
	//根据供应商名称模糊查询
	@RequestMapping("/findProvider.action")
	public @ResponseBody DataTablesResponseInfo findProvider(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		//获取供应商名称
		String SupplierName = map.get("SupplierName").toString();
		//通过供应商名称模糊查询
		List<Provider> list = ProviderService.findProvider(SupplierName);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setSerialnumber(i+1);
		}
		dtri.setData(list);
		return dtri;
	}
	//删除校验查询
	@RequestMapping("/getAllStoragemeasure.action")
	public @ResponseBody List<Map<String, Object>> getAllStoragemeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return ProviderService.getAllStoragemeasure(map);
	}
}
