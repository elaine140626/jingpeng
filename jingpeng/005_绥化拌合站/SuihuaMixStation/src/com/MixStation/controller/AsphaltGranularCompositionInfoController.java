package com.MixStation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.AsphaltGradDetailedEntity;
import com.MixStation.model.AsphaltGradingInfoEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.MeshSizeInfoEntity;
import com.MixStation.service.AsphaltGranularCompositionInfoService;
import com.mixingStation.model.ResponseInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/granularComposition")
public class AsphaltGranularCompositionInfoController {
	
	@Autowired
	private AsphaltGranularCompositionInfoService asphaltGranularCompositionInfoService;
	
	//查询工区下启用的配合比
	@RequestMapping("/getApshaltMixProportion.action")
	@ResponseBody
	public List<ApshaltMixProportionEntity> getApshaltMixProportion(@RequestParam HashMap<String, Object> map){
		return asphaltGranularCompositionInfoService.getApshaltMixProportion(map);
	}
	
	//查询工区下启用过的配合比
	@RequestMapping("/getApshaltMixProportionUsed.action")
	@ResponseBody
	public List<ApshaltMixProportionEntity> getApshaltMixProportionUsed(@RequestParam HashMap<String, Object> map){
		return asphaltGranularCompositionInfoService.getApshaltMixProportionUsed(map);
	}
	
	//查询所有筛孔
	@RequestMapping("/getAllMeshSizeInfo.action")
	@ResponseBody
	public List<MeshSizeInfoEntity> getAllMeshSizeInfo(@RequestParam HashMap<String, Object> map){
		return asphaltGranularCompositionInfoService.getAllMeshSizeInfo(map);
	}
	
	//查询所有级配信息
	@RequestMapping("/getAllAsphaltGradingInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getAllAsphaltGradingInfo(@RequestParam HashMap<String, Object> map){
		return asphaltGranularCompositionInfoService.getAllAsphaltGradingInfo(map);
	}
	
	//添加级配
	@RequestMapping("/addAsphaltGradingInfo.action")
	@ResponseBody
	public ResponseInfo addAsphaltGradingInfo(AsphaltGradingInfoEntity asphaltGradingInfoEntity,@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		// 0:新增  1:修改  2:复制
		String flag = param.get("flag").toString();
		String count = "0";
		//同一拌合站级配编号不能重复
		if("0".equals(flag) || "2".equals(flag)) {//新增复制的场合
			count = asphaltGranularCompositionInfoService.selectGradeCode(param.get("gradeCode").toString(), "",param.get("orgId").toString());
		}else {//修改的场合
			count = asphaltGranularCompositionInfoService.selectGradeCode(param.get("gradeCode").toString(), param.get("id").toString(),param.get("orgId").toString());
		}
		if("0".equals(count)) {
			//添加筛孔通过率
			JSONArray jsonArray=JSONArray.fromObject(param.get("asphaltGradDetailedList"));
			List<AsphaltGradDetailedEntity> asphaltGradDetailedList = new ArrayList<AsphaltGradDetailedEntity>();
			if(jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					AsphaltGradDetailedEntity asphaltGradDetailedEntity = new AsphaltGradDetailedEntity();
					//遍历jsonArray数组，把每一个转换成json对象
					JSONObject job = jsonArray.getJSONObject(i);
					int sum= 0;
					if (!"".equals(job.get("meshId").toString())) {
						asphaltGradDetailedEntity.setMeshId(Integer.parseInt(job.get("meshId").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware1").toString())) {
						asphaltGradDetailedEntity.setWare1(Double.valueOf(job.get("ware1").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware2").toString())) {
						asphaltGradDetailedEntity.setWare2(Double.valueOf(job.get("ware2").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware3").toString())) {
						asphaltGradDetailedEntity.setWare3(Double.valueOf(job.get("ware3").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware4").toString())) {
						asphaltGradDetailedEntity.setWare4(Double.valueOf(job.get("ware4").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware5").toString())) {
						asphaltGradDetailedEntity.setWare5(Double.valueOf(job.get("ware5").toString()));
						sum++;
					}
					if (!"".equals(job.get("ware6").toString())) {
						asphaltGradDetailedEntity.setWare6(Double.valueOf(job.get("ware6").toString()));
						sum++;
					}
					if (!"".equals(job.get("coldPowder1").toString())) {
						asphaltGradDetailedEntity.setColdPowder1(Double.valueOf(job.get("coldPowder1").toString()));
						sum++;
					}
					if (!"".equals(job.get("coldPowder2").toString())) {
						asphaltGradDetailedEntity.setColdPowder2(Double.valueOf(job.get("coldPowder2").toString()));
						sum++;
					}
					if (!"".equals(job.get("hotPowder").toString())) {
						asphaltGradDetailedEntity.setHotPowder(Double.valueOf(job.get("hotPowder").toString()));
						sum++;
					}
					if (!"".equals(job.get("upperLimit").toString())) {
						asphaltGradDetailedEntity.setUpperLimit(Double.valueOf(job.get("upperLimit").toString()));
						sum++;
					}
					if (!"".equals(job.get("lowerLimit").toString())) {
						asphaltGradDetailedEntity.setLowerLimit(Double.valueOf(job.get("lowerLimit").toString()));
						sum++;
					}
					if (!"".equals(job.get("operator").toString())) {
						asphaltGradDetailedEntity.setOperator(job.get("operator").toString());
						sum++;
					}
					if(sum!=0) {
						asphaltGradDetailedList.add(asphaltGradDetailedEntity);
					}
				}
			}
			int result = 0;
			if("0".equals(flag) || "2".equals(flag)) {//新增
				result = asphaltGranularCompositionInfoService.addAsphaltGradingInfo(asphaltGradingInfoEntity,asphaltGradDetailedList);
			}else {//修改
				result = asphaltGranularCompositionInfoService.updateAsphaltGradingInfo(asphaltGradingInfoEntity,asphaltGradDetailedList);
			}
			if (result>0) {
				info.setCode("success");
				info.setMessage("操作成功");
			}else {
				info.setCode("error");
				info.setMessage("操作失败");
			}
		}else {
			info.setCode("error");
			info.setMessage("级配编号重复");
		}
		return info;
	}
	
	
	//查询单挑信息通过级配id
	@RequestMapping("/getAllInfoById.action")
	@ResponseBody
	public Map<String,Object> getAllInfoById(@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//级配信息的获取
		List<AsphaltGradingInfoEntity> asphaltGradingList= asphaltGranularCompositionInfoService.getAsphaltGradingInfoById(map);
		//筛孔通过率的获取
		List<AsphaltGradDetailedEntity> asphaltGradDetailedList = asphaltGranularCompositionInfoService.getAsphaltGradDetailedByGradId(map);
		resultMap.put("asphaltGradingList", asphaltGradingList);
		resultMap.put("asphaltGradDetailedList", asphaltGradDetailedList);
		return resultMap;
	}
	
	//删除级配
	@RequestMapping("/deleteAsphaltGradingInfo.action")
	@ResponseBody
	public ResponseInfo deleteAsphaltGradingInfo(@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		int result = asphaltGranularCompositionInfoService.deleteAsphaltGradingInfo(param);
		if (result>0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	
	//启用
	@RequestMapping("/enableAsphaltGradingInfo.action")
	@ResponseBody
	public ResponseInfo enableAsphaltGradingInfo(@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		int result = asphaltGranularCompositionInfoService.enableAsphaltGradingInfo(param);
		if (result>0) {
			info.setCode("success");
			info.setMessage("启用成功");
		}else {
			info.setCode("error");
			info.setMessage("启用失败");
		}
		return info;
	}
}
