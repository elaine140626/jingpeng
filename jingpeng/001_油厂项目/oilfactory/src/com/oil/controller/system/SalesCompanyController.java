package com.oil.controller.system;

import java.io.IOException;
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
import com.oil.model.system.SalesCompany;
import com.oil.service.system.SalesCompanyService;

@Controller
@RequestMapping("/salesCompany")
public class SalesCompanyController {
		@Autowired
		private SalesCompanyService salesCompanyService;
		
		//获取list数据
		@RequestMapping("/getSalesCompanyList.action")
		@ResponseBody
		public DataTablesResponseInfo getSalesCompanyList(@RequestParam Map<String, Object> map){
			return salesCompanyService.getSalesCompanyList(map);	
		} 
	
		//新增销售公司
		@RequestMapping("/addSalesCompany.action")
		@ResponseBody
		public ResponseInfo addSalesCompany(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
			return salesCompanyService.addSalesCompany(request, map);
		}
		
		//修改销售公司
		@RequestMapping("/updateSalesCompany.action")
		@ResponseBody
		public ResponseInfo updateSalesCompany(HttpServletRequest request,@RequestParam Map<String, Object> map) throws IOException{
			return salesCompanyService.updateSalesCompany(request, map);
		}
		
		//删除销售公司
		@RequestMapping("/delSalesCompany.action")
		@ResponseBody
		public ResponseInfo delSalesCompany(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
			return salesCompanyService.delSalesCompany(request, map);
		}
		
		//id获取信息
		@RequestMapping("/getSalesCompanyInfo.action")
		public @ResponseBody SalesCompany getSalesCompanyInfo(@RequestParam Map<String, Object> map){
			SalesCompany SalesCompany = salesCompanyService.getSalesCompanyInfo(map);
			
			if(SalesCompany == null) {
				SalesCompany = new SalesCompany();
			}
			return SalesCompany;
		}
		
		//销售公司民称
		@RequestMapping("/getSalesCompanyName.action")
		@ResponseBody
		public List<Map<String,Object>> getSalesCompanyName(){
			List<Map<String,Object>> list = salesCompanyService.getSalesCompanyName();
			return list;
		}
		
		//删除的校验查询
		@RequestMapping("/getAllSalesCompany.action")
		@ResponseBody
		public List<Map<String, Object>> getAllSalesCompany(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
			return salesCompanyService.getAllSalesCompany(map);
		}
}
