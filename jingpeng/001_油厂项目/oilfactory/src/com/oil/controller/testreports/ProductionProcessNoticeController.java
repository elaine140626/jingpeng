package com.oil.controller.testreports;

import java.io.IOException;
import java.util.ArrayList;
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
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;
import com.oil.service.testreports.ProductionProcessNoticeService;
import com.oil.util.PropertyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ProductionProcessNotice")
public class ProductionProcessNoticeController {
	@Autowired
	private ProductionProcessNoticeService productionProcessNoticeService;
	// 计划调度表获取datatable
	@RequestMapping("/getPlanMeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = productionProcessNoticeService.getPlanMeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 生产工艺通知单单条获取
	@RequestMapping("/getProductionProcessNoticeById.action")
	@ResponseBody
	public Map<String,Object> getProductionProcessNoticeById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 计划调度表获取
		List<NextProductionPlanEntity> list = productionProcessNoticeService.getPlanMeasure(map);
		// 生产管理表获取
		List<ProductionManagementEntity> listDetail = productionProcessNoticeService.getProductionManagement(map);
		
		resultMap.put("list", list);
		resultMap.put("listDetail", listDetail);
		return resultMap;
	}
	
	// 通知单修改
	@RequestMapping("/updateProductionProcessNotice.action")
	@ResponseBody
	public ResponseInfo updateProductionProcessNotice(@RequestParam Map<String, Object> param) throws IOException{		
		ResponseInfo info = new ResponseInfo();
		
		// 计划调度表
		NextProductionPlanEntity nextProductionPlanEntity = new NextProductionPlanEntity();
		nextProductionPlanEntity.setId(Integer.parseInt(param.get("Id").toString()));
		// 配料单生产批次
		nextProductionPlanEntity.setBatchingList(param.get("BatchingList").toString());
		// 工艺编号
		nextProductionPlanEntity.setTechnicsNumber(param.get("TechnicsNumber").toString());
		nextProductionPlanEntity.setReviser(param.get("Reviser").toString());

		// 生产管理表
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("listDetail"));
		List<ProductionManagementEntity> listDetail = new ArrayList<ProductionManagementEntity>();
		if(jsonArray.size()>0){
			for(int i=1;i<jsonArray.size();i++){
				ProductionManagementEntity productionManagementEntity = new ProductionManagementEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				// 计划调度id
				productionManagementEntity.setPlanMeasureId(Integer.parseInt(param.get("Id").toString()));
				// 原料id
				productionManagementEntity.setMaterielId(Integer.parseInt(job.get("MaterielModel").toString()));
				// 含量
				productionManagementEntity.setRatioWeight(Double.parseDouble(job.get("RatioWeight").toString()));
				// 理论消耗重量
				productionManagementEntity.setMaterielWeight(Double.parseDouble(job.get("MaterielWeight").toString()));
				// 备注
				productionManagementEntity.setRemarks(job.get("Remarks").toString());
				productionManagementEntity.setCreater(param.get("Reviser").toString());				
		
				listDetail.add(productionManagementEntity);									
			}
		}
			
		int res = 0;
		res = productionProcessNoticeService.updateProductionProcessNotice(nextProductionPlanEntity, listDetail);

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
	
	// 通知单审核
	@RequestMapping("/examineProductionProcessNotice.action")
	@ResponseBody
	public ResponseInfo examineProductionProcessNotice(NextProductionPlanEntity nextProductionPlanEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = productionProcessNoticeService.examineProductionProcessNotice(nextProductionPlanEntity);
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
