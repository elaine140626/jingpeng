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
import com.oil.model.system.Detectionindex;
import com.oil.service.system.DetectionindexService;

@Controller
@RequestMapping("/Detectionindex")
public class DetectionindexController {
	
	@Autowired
	DetectionindexService detectionindexService;
	
    //获取全部检测指标
	@RequestMapping("/getDetectionindex.action")
	@ResponseBody
	public DataTablesResponseInfo getStartTransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return detectionindexService.getDetectionindex(map);
	}

	//添加检测指标
	@RequestMapping("/addDetectionindex.action")
	@ResponseBody
	public ResponseInfo addDetectionindex(@RequestBody Detectionindex detectionindex) {
		return detectionindexService.addDetectionindex(detectionindex);
	}
	
	//修改检测指标
	@RequestMapping("/updateDetectionindex.action")
	@ResponseBody
	public ResponseInfo updateDetectionindex(@RequestBody Detectionindex detectionindex) {
		return detectionindexService.updateDetectionindex(detectionindex);
	}
	
	//删除检测指标
	@RequestMapping("/delDetectionindex.action")
	@ResponseBody
	public ResponseInfo delDetectionindex(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return detectionindexService.delDetectionindex(request,map);
	}
	
	
	//根据客户名称模糊查询
	@RequestMapping("/findDetectionindexByName.action")
	@ResponseBody
	public DataTablesResponseInfo findDetectionindexByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String testingItems = map.get("testingItems").toString();
		return detectionindexService.findDetectionindexByName(testingItems);
	}
}
