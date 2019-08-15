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
import com.oil.model.system.CachetPersonnel;
import com.oil.service.system.CachetPersonnelService;

@Controller
@RequestMapping("/CachetPersonnel")
public class CachetPersonnelController {

	@Autowired
	CachetPersonnelService cachetPersonnelService;
	
	//获取全部人员电子公章
	@RequestMapping("/getCachetPersonnel.action")
	@ResponseBody
	public DataTablesResponseInfo getCachetPersonnel(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetPersonnelService.getCachetPersonnel(map);
	}
	
	//添加人员电子公章
	@RequestMapping("/addCachetPersonnel.action")
	@ResponseBody
	public ResponseInfo addCachetPersonnel(@RequestBody CachetPersonnel cachetPersonnel) {
		return cachetPersonnelService.addCachetPersonnel(cachetPersonnel);
	}
	
	//修改人员电子公章
	@RequestMapping("/updateCachetPersonnel.action")
	@ResponseBody
	public ResponseInfo updateCachetPersonnel(@RequestBody CachetPersonnel cachetPersonnel) {
		return cachetPersonnelService.updateCachetPersonnel(cachetPersonnel);
	}
	
	//删除人员电子公章
	@RequestMapping("/delCachetPersonnel.action")
	@ResponseBody
	public ResponseInfo delCachetPersonnel(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetPersonnelService.delCachetPersonnel(request,map);
	}
	
	
	//根据名称模糊查询
	@RequestMapping("/findCachetPersonnelByName.action")
	@ResponseBody
	public DataTablesResponseInfo findCachetPersonnelByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String cachetName = map.get("cachetName").toString();
		return cachetPersonnelService.findCachetPersonnelByName(cachetName);
	}
	
	//根据名称模糊查询
	@RequestMapping("/updateElectronicsName.action")
	@ResponseBody
	public int updateElectronicsName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return cachetPersonnelService.updateElectronicsName(request, map);
	}
}
