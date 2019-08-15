package com.oil.controller.dispath;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.dispath.NextProductionPlanService;
import com.oil.util.PropertyUtil;

@Controller
@RequestMapping("/nextProductionPlan")
public class NextProductionPlanController {

	@Autowired
	NextProductionPlanService nextProductionPlanService;
	
	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getProductionPlanList.action")
	public @ResponseBody DataTablesResponseInfo getProductionPlanList(@RequestParam Map<String, Object> map){
		DataTablesResponseInfo data = new DataTablesResponseInfo();
		data.setData(nextProductionPlanService.getProductionPlanList(map));
		return data;	
	}
	
	/**编号判重
	 * @param 
	 * @return
	 */
	@RequestMapping("/getPlanNumber.action")
	public @ResponseBody Integer getPlanNumber(@RequestParam Map<String, Object> map){
		return nextProductionPlanService.getPlanNumber(map);	
	}
	
	/**获取客户对应的销售
	 * @param 
	 * @return
	 */
	@RequestMapping("/getSalesList.action")
	public @ResponseBody Map<String, Object> getSalesList(@RequestParam Map<String, Object> map){
		return nextProductionPlanService.getSalesList(map);	
	}
	
	/**
	 * 新增生产计划
	 * @param 
	 * @return
	 */
	@RequestMapping("/insertProductionPlan.action")
	public @ResponseBody ResponseInfo insertProductionPlan(NextProductionPlanEntity data) throws IOException{
		ResponseInfo info = new ResponseInfo();
		
		if(nextProductionPlanService.insertProductionPlan(data) > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		}else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}
	
	/**
	 * 修改生产计划
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateProductionPlan.action")
	public @ResponseBody ResponseInfo updateProductionPlan(NextProductionPlanEntity data) throws IOException{
		ResponseInfo info = new ResponseInfo();
		if(nextProductionPlanService.updateProductionPlan(data) > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		}else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}
	
	/**
	 * 删除生产计划
	 * @param 
	 * @return
	 */
	@RequestMapping("/delProductionPlan.action")
	public @ResponseBody ResponseInfo delProductionPlan(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		ResponseInfo info = new ResponseInfo();
		
		if(nextProductionPlanService.delProductionPlan(map) > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		}else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}
}
