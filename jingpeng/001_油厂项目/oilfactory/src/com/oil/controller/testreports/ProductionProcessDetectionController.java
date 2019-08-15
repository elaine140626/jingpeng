package com.oil.controller.testreports;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.testreports.ProductionProcessDetectionService;
import com.oil.util.PropertyUtil;

@Controller
@RequestMapping("/ProductionProcessDetection")
public class ProductionProcessDetectionController {
	@Autowired
	private ProductionProcessDetectionService productionProcessDetectionService;
	// 计划调度表获取(生产过程检测)
	@RequestMapping("/getPlanMeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = productionProcessDetectionService.getPlanMeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 生产过程检测
	@RequestMapping("/updateProductionProcessDetection.action")
	@ResponseBody
	public ResponseInfo updateProductionProcessDetection(NextProductionPlanEntity nextProductionPlanEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = productionProcessDetectionService.updateProductionProcessDetection(nextProductionPlanEntity);
		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
		
}
