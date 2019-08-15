package com.oil.controller.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CachetCompany;
import com.oil.service.system.CachetCompanyService;


@Controller
@RequestMapping("/CachetCompany")
public class CachetCompanyController {

	@Autowired
	CachetCompanyService cachetCompanyService;
	
	//获取全部人员电子公章
	@RequestMapping("/getCachetCompany.action")
	@ResponseBody
	public DataTablesResponseInfo getCachetCompany(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetCompanyService.getCachetCompany(map);
	}
	
	//添加人员电子公章
	@RequestMapping("/addCachetCompany.action")
	@ResponseBody
	public ResponseInfo addCachetCompany(@RequestBody CachetCompany cachetPersonnel) {
		return cachetCompanyService.addCachetCompany(cachetPersonnel);
	}
	
	//修改人员电子公章
	@RequestMapping("/updateCachetCompany.action")
	@ResponseBody
	public ResponseInfo updateCachetCompany(@RequestBody CachetCompany cachetPersonnel) {
		return cachetCompanyService.updateCachetCompany(cachetPersonnel);
	}
	
	//删除人员电子公章
	@RequestMapping("/delCachetCompany.action")
	@ResponseBody
	public ResponseInfo delCachetCompany(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetCompanyService.delCachetCompany(request,map);
	}
	
	
	//根据名称模糊查询
	@RequestMapping("/findCachetCompanyByName.action")
	@ResponseBody
	public DataTablesResponseInfo findCachetCompanyByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String testCompany = map.get("testCompany").toString();
		return cachetCompanyService.findCachetCompanyByName(testCompany);
	}
	
	//根据名称模糊查询
	@RequestMapping("/updateElectronicsName.action")
	@ResponseBody
	public int updateElectronicsName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetCompanyService.updateElectronicsName(request, map);
	}
}
