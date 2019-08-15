package com.mixingStation.controller.cement;

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

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.cement.CementConsPropDetailed;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.cement.CementConstructionProportionService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cementConstructionProportion")
public class CementConstructionProportionController extends BaseController{

	@Autowired
	private CementConstructionProportionService cementConstructionProportionService;
	
	//查询理论配比信息列表
	@RequestMapping("/getAllCementConstructionProportion.action")
	@ResponseBody
	public DataTablesResponseInfo getAllCementConstructionProportion(@RequestParam HashMap<String,Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<CementConstructionProportion> data = cementConstructionProportionService.getAllCementConstructionProportion(map);
		dtr.setData(data);
		return dtr;
	}
	
	//获取产品名称
	@RequestMapping("/getMaterialNameList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialNameList(@RequestParam HashMap<String, Object> map) {
		return cementConstructionProportionService.getMaterialNameList(map);
	}
	
	//获取产品型号
	@RequestMapping("/getMaterialModelList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialModelList(@RequestParam HashMap<String, Object> map) {
		return cementConstructionProportionService.getMaterialModelList(map);
	}
	
	//获取理论配比编号
	@RequestMapping("/getCementTheoryProportion.action")
	@ResponseBody
	public List<CementTheoryProportion> getCementTheoryProportion(@RequestParam HashMap<String, Object> map) {
		return cementConstructionProportionService.getCementTheoryProportion(map);
	}
	
	//通过id查询理论配比信息
	@RequestMapping("/getCementTheoryProportionById.action")
	@ResponseBody
	public Map<String,Object> getCementTheoryProportionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//理论配比信息获取
		List<CementTheoryProportion> cementTheoryProportionByIdInfo = cementConstructionProportionService.getCementTheoryProportion(map);
		//理论配比明细信息获取
		List<CementTheoPropDetailed> cementTheoPropDetailedByIdInfo = cementConstructionProportionService.getCementTheoPropDetailedById(map);
		resultMap.put("cementTheoryProportionByIdInfo", cementTheoryProportionByIdInfo);
		resultMap.put("cementTheoPropDetailedByIdInfo", cementTheoPropDetailedByIdInfo);
		return resultMap;
	}
	
	//添加施工配比
	@RequestMapping("/addCementConstructionProportion.action")
	@ResponseBody
	public ResponseInfo addCementConstructionProportion(HttpServletRequest request,@RequestParam HashMap<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//获取当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//施工配比
		CementConstructionProportion ccp = new CementConstructionProportion();
		//组织机构ID
		ccp.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//配比编码
		ccp.setPropCode(map.get("propCode").toString());
		//产品Id
		ccp.setProductId(Integer.parseInt(map.get("productId").toString()));
		//配比编号
		ccp.setTheoPropId(Integer.parseInt(map.get("theoPropId").toString()));
		//设计强度（单位：MPa）
		ccp.setDesignStrength(map.get("designStrength").toString());
		//水灰比
		ccp.setWaterCementRatio(Double.valueOf(map.get("waterCementRatio").toString()));
		//砂率
		ccp.setSandRatio(Double.valueOf(map.get("sandRatio").toString()));
		//坍落度1（单位：mm）
		ccp.setSlumpLow(Integer.parseInt(map.get("slumpLow").toString()));
		//坍落度2
		ccp.setSlumpHigh(Integer.parseInt(map.get("slumpHigh").toString()));
		//备注
		ccp.setRemarks(map.get("remarks").toString());
		//创建人
		ccp.setOperator(loginUser.getUserName());
		
		//施工配比明细
		//首先把字符串转换成JASONArray对象
		JSONArray jsonArray=JSONArray.fromObject(map.get("cementConsPropDetailedList"));
		List<CementConsPropDetailed> cementConsPropDetailedList = new ArrayList<CementConsPropDetailed>();
		if(jsonArray.size() > 1) {
			for (int i = 1; i < jsonArray.size(); i++) {
				//配比信息明细
				CementConsPropDetailed cementConsPropDetailed = new CementConsPropDetailed();
				//遍历jsonarray 数组，把每一对象装换成json
				JSONObject job = jsonArray.getJSONObject(i);
				int sum = 0;
				//原材料
				if(!"".equals(job.get("str_materialModel").toString())) {
					cementConsPropDetailed.setMaterialsId(Integer.parseInt(job.get("str_materialModel").toString()));
					sum++;
				}
				//理论配比用量
				if(!"".equals(job.get("theoryWeight").toString())) {
					cementConsPropDetailed.setTheoryWeight(Double.valueOf(job.get("theoryWeight").toString()));
					sum++;
				}
				//施工配比用量
				if(!"".equals(job.get("constructionWeight").toString())) {
					cementConsPropDetailed.setConstructionWeight(Double.valueOf(job.get("constructionWeight").toString()));
					sum++;
				}
				//厂家
				if(!"".equals(job.get("manufacturer").toString())) {
					cementConsPropDetailed.setManufacturer(job.get("manufacturer").toString());
					sum++;
				}
				//产地
				if(!"".equals(job.get("materialOrigin").toString())) {
					cementConsPropDetailed.setMaterialOrigin(job.get("materialOrigin").toString());
					sum++;
				}
				if(sum != 0) {
					cementConsPropDetailedList.add(cementConsPropDetailed);
				}
			}
		}
		int result = cementConstructionProportionService.addCementConstructionProportion(ccp, cementConsPropDetailedList);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	//通过id查询施工配比信息
	@RequestMapping("/getCementConstructionProportionById.action")
	@ResponseBody
	public Map<String,Object> getCementConstructionProportionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//施工配比信息获取
		List<CementConstructionProportion> cementConstructionProportionById = cementConstructionProportionService.getCementConstructionProportionById(map);
		//施工配比明细信息获取
		List<CementConsPropDetailed> cementConsPropDetailedById = cementConstructionProportionService.getCementConsPropDetailedById(map);
		resultMap.put("cementConstructionProportionByIdInfo", cementConstructionProportionById);
		resultMap.put("cementConsPropDetailedByIdInfo", cementConsPropDetailedById);
		return resultMap;
	}
	
	//修改施工配比
	@RequestMapping("/updateCementConstructionProportion.action")
	@ResponseBody
	public ResponseInfo updateCementConstructionProportion(HttpServletRequest request,@RequestParam HashMap<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//获取当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//施工配比
		CementConstructionProportion ccp = new CementConstructionProportion();
		//id
		ccp.setId(Integer.parseInt(map.get("id").toString()));
		//配比编码
		ccp.setPropCode(map.get("propCode").toString());
		//产品Id
		ccp.setProductId(Integer.parseInt(map.get("productId").toString()));
		//配比编号
		ccp.setTheoPropId(Integer.parseInt(map.get("theoPropId").toString()));
		//设计强度（单位：MPa）
		ccp.setDesignStrength(map.get("designStrength").toString());
		//水灰比
		ccp.setWaterCementRatio(Double.valueOf(map.get("waterCementRatio").toString()));
		//砂率
		ccp.setSandRatio(Double.valueOf(map.get("sandRatio").toString()));
		//坍落度1（单位：mm）
		ccp.setSlumpLow(Integer.parseInt(map.get("slumpLow").toString()));
		//坍落度2
		ccp.setSlumpHigh(Integer.parseInt(map.get("slumpHigh").toString()));
		//备注
		ccp.setRemarks(map.get("remarks").toString());
		//修改人
		ccp.setModifier(loginUser.getUserName());
		
		//施工配比明细
		//首先把字符串转换成JASONArray对象
		JSONArray jsonArray=JSONArray.fromObject(map.get("cementConsPropDetailedList"));
		List<CementConsPropDetailed> cementConsPropDetailedList = new ArrayList<CementConsPropDetailed>();
		if(jsonArray.size() > 1) {
			for (int i = 1; i < jsonArray.size(); i++) {
				//配比信息明细
				CementConsPropDetailed cementConsPropDetailed = new CementConsPropDetailed();
				//遍历jsonarray 数组，把每一对象装换成json
				JSONObject job = jsonArray.getJSONObject(i);
				int sum = 0;
				//原材料
				if(!"".equals(job.get("str_materialModel").toString())) {
					cementConsPropDetailed.setMaterialsId(Integer.parseInt(job.get("str_materialModel").toString()));
					sum++;
				}
				//理论配比用量
				if(!"".equals(job.get("theoryWeight").toString())) {
					cementConsPropDetailed.setTheoryWeight(Double.valueOf(job.get("theoryWeight").toString()));
					sum++;
				}
				//施工配比用量
				if(!"".equals(job.get("constructionWeight").toString())) {
					cementConsPropDetailed.setConstructionWeight(Double.valueOf(job.get("constructionWeight").toString()));
					sum++;
				}
				//厂家
				if(!"".equals(job.get("manufacturer").toString())) {
					cementConsPropDetailed.setManufacturer(job.get("manufacturer").toString());
					sum++;
				}
				//产地
				if(!"".equals(job.get("materialOrigin").toString())) {
					cementConsPropDetailed.setMaterialOrigin(job.get("materialOrigin").toString());
					sum++;
				}
				if(sum != 0) {
					cementConsPropDetailedList.add(cementConsPropDetailed);
				}
			}
		}
		int result = cementConstructionProportionService.updateCementConstructionProportion(ccp, cementConsPropDetailedList);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	//删除施工配比
	@RequestMapping("/deleteCementConstructionProportionById.action")
	@ResponseBody
	public ResponseInfo deleteCementTheoryProportionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = cementConstructionProportionService.deleteCementConstructionProportionById(map);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
}
