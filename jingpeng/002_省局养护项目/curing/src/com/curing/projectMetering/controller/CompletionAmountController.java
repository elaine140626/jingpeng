package com.curing.projectMetering.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.projectMetering.model.CompletionAmountEntity;
import com.curing.projectMetering.model.CompletionAmountSum;
import com.curing.projectMetering.service.CompletionAmountService;

@Controller
@RequestMapping("/CompletionAmount")
public class CompletionAmountController {
	
	@Autowired
	private CompletionAmountService completionAmountService;
	
	// 工程计量（2）list取得
	@RequestMapping("/getCompletionAmountList.action")
	@ResponseBody
	public DataTablesResponseInfo getCompletionAmountList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<CompletionAmountEntity> list = completionAmountService.getCompletionAmountList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程计量（2）合计
	@RequestMapping("/getCompletionAmountSum.action")
	@ResponseBody
	public List<CompletionAmountSum> getCompletionAmountSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CompletionAmountSum> list = completionAmountService.getCompletionAmountSum(map);
		return list;
	}
}
