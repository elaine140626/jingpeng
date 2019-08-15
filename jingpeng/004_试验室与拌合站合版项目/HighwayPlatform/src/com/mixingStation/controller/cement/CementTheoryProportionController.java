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
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.cement.CementTheoryProportionService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cementTheoryProportion")
public class CementTheoryProportionController extends BaseController{
	
	@Autowired
	private CementTheoryProportionService cementTheoryProportionService;

	//查询理论配比信息列表
	@RequestMapping("/getAllCementTheoryProportion.action")
	@ResponseBody
	public DataTablesResponseInfo getAllCementTheoryProportion(@RequestParam HashMap<String,Object> map) {
		return cementTheoryProportionService.getAllCementTheoryProportion(map);
	}
	
	//获取产品名称
	@RequestMapping("/getMaterialNameList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialNameList(@RequestParam HashMap<String, Object> map) {
		return cementTheoryProportionService.getMaterialNameList(map);
	}
	
	//获取产品型号
	@RequestMapping("/getMaterialModelList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialModelList(@RequestParam HashMap<String, Object> map) {
		return cementTheoryProportionService.getMaterialModelList(map);
	}
	
	//添加理论配比
	@RequestMapping("/addCementTheoryProportion.action")
	@ResponseBody
	public ResponseInfo addCementTheoryProportion(HttpServletRequest request,@RequestParam HashMap<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//获取当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//理论配比
		CementTheoryProportion ctp = new CementTheoryProportion();
		//组织机构ID
		ctp.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//配比编码
		ctp.setPropCode(map.get("propCode").toString());
		//产品Id
		ctp.setProductId(Integer.parseInt(map.get("productId").toString()));
		//设计强度（单位：MPa）
		ctp.setDesignStrength(map.get("designStrength").toString());
		//水灰比
		ctp.setWaterCementRatio(Double.valueOf(map.get("waterCementRatio").toString()));
		//砂率
		ctp.setSandRatio(Double.valueOf(map.get("sandRatio").toString()));
		//坍落度1（单位：mm）
		ctp.setSlumpLow(Integer.parseInt(map.get("slumpLow").toString()));
		//坍落度2
		ctp.setSlumpHigh(Integer.parseInt(map.get("slumpHigh").toString()));
		//备注
		ctp.setRemarks(map.get("remarks").toString());
		//创建人
		ctp.setOperator(loginUser.getUserName());
		
		//理论配比明细
		//首先把字符串转换成JASONArray对象
		JSONArray jsonArray=JSONArray.fromObject(map.get("cementTheoPropDetailedList"));
		List<CementTheoPropDetailed> cementTheoPropDetailedList = new ArrayList<CementTheoPropDetailed>();
		if(jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				//配比信息明细
				CementTheoPropDetailed cementTheoPropDetailed = new CementTheoPropDetailed();
				//遍历jsonarray 数组，把每一对象装换成json
				JSONObject job = jsonArray.getJSONObject(i);
				int sum = 0;
				//原材料
				if(!"".equals(job.get("str_materialModel").toString())) {
					cementTheoPropDetailed.setMaterialsId(Integer.parseInt(job.get("str_materialModel").toString()));
					sum++;
				}
				//原材料用量
				if(!"".equals(job.get("weight").toString())) {
					cementTheoPropDetailed.setWeight(Double.valueOf(job.get("weight").toString()));
					sum++;
				}
				//厂家
				if(!"".equals(job.get("manufacturer").toString())) {
					cementTheoPropDetailed.setManufacturer(job.get("manufacturer").toString());
					sum++;
				}
				//产地
				if(!"".equals(job.get("materialOrigin").toString())) {
					cementTheoPropDetailed.setMaterialOrigin(job.get("materialOrigin").toString());
					sum++;
				}
				if(sum != 0) {
					cementTheoPropDetailedList.add(cementTheoPropDetailed);
				}
			}
		}
		int result = cementTheoryProportionService.addCementTheoryProportion(ctp, cementTheoPropDetailedList);
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
	
	//通过id查询理论配比信息
	@RequestMapping("/getCementTheoryProportionById.action")
	@ResponseBody
	public Map<String,Object> getCementTheoryProportionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//理论配比信息获取
		List<CementTheoryProportion> cementTheoryProportionByIdInfo = cementTheoryProportionService.getCementTheoryProportionById(map);
		//理论配比明细信息获取
		List<CementTheoPropDetailed> cementTheoPropDetailedByIdInfo = cementTheoryProportionService.getCementTheoPropDetailedById(map);
		resultMap.put("cementTheoryProportionByIdInfo", cementTheoryProportionByIdInfo);
		resultMap.put("cementTheoPropDetailedByIdInfo", cementTheoPropDetailedByIdInfo);
		return resultMap;
	}
	
	//修改理论配比
	@RequestMapping("/updateCementTheoryProportion.action")
	@ResponseBody
	public ResponseInfo updateCementTheoryProportion(HttpServletRequest request,@RequestParam HashMap<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//获取当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//理论配比
		CementTheoryProportion ctp = new CementTheoryProportion();
		//id
		ctp.setId(Integer.parseInt(map.get("id").toString()));
		//配比编码
		ctp.setPropCode(map.get("propCode").toString());
		//产品Id
		ctp.setProductId(Integer.parseInt(map.get("productId").toString()));
		//设计强度（单位：MPa）
		ctp.setDesignStrength(map.get("designStrength").toString());
		//水灰比
		ctp.setWaterCementRatio(Double.valueOf(map.get("waterCementRatio").toString()));
		//砂率
		ctp.setSandRatio(Double.valueOf(map.get("sandRatio").toString()));
		//坍落度1（单位：mm）
		ctp.setSlumpLow(Integer.parseInt(map.get("slumpLow").toString()));
		//坍落度2
		ctp.setSlumpHigh(Integer.parseInt(map.get("slumpHigh").toString()));
		//备注
		ctp.setRemarks(map.get("remarks").toString());
		//创建人
		ctp.setModifier(loginUser.getUserName());
		
		//理论配比明细
		//首先把字符串转换成JASONArray对象
		JSONArray jsonArray=JSONArray.fromObject(map.get("cementTheoPropDetailedList"));
		List<CementTheoPropDetailed> cementTheoPropDetailedList = new ArrayList<CementTheoPropDetailed>();
		if(jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				//配比信息明细
				CementTheoPropDetailed cementTheoPropDetailed = new CementTheoPropDetailed();
				//遍历jsonarray 数组，把每一对象装换成json
				JSONObject job = jsonArray.getJSONObject(i);
				int sum = 0;
				//原材料
				if(!"".equals(job.get("str_materialModel").toString())) {
					cementTheoPropDetailed.setMaterialsId(Integer.parseInt(job.get("str_materialModel").toString()));
					sum++;
				}
				//原材料用量
				if(!"".equals(job.get("weight").toString())) {
					cementTheoPropDetailed.setWeight(Double.valueOf(job.get("weight").toString()));
					sum++;
				}
				//厂家
				if(!"".equals(job.get("manufacturer").toString())) {
					cementTheoPropDetailed.setManufacturer(job.get("manufacturer").toString());
					sum++;
				}
				//产地
				if(!"".equals(job.get("materialOrigin").toString())) {
					cementTheoPropDetailed.setMaterialOrigin(job.get("materialOrigin").toString());
					sum++;
				}
				if(sum != 0) {
					cementTheoPropDetailedList.add(cementTheoPropDetailed);
				}
			}
		}
		int result = cementTheoryProportionService.updateCementTheoryProportion(ctp, cementTheoPropDetailedList);
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
	
	//删除理论配比
	@RequestMapping("/deleteCementTheoryProportionById.action")
	@ResponseBody
	public ResponseInfo deleteCementTheoryProportionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = cementTheoryProportionService.deleteCementTheoryProportionById(map);
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
	
	//通过施工配比编号获取所有的物料id
	@RequestMapping("/getAllMaterials_id.action")
	@ResponseBody
	public DataTablesResponseInfo getAllMaterials_id(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			dtri.setData(cementTheoryProportionService.getAllMaterials_id(map));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
			
	}
	//材料名称
	@RequestMapping("/getYclList.action")
	@ResponseBody
	public DataTablesResponseInfo getYclList(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		CementConsPropDetailed cc = new CementConsPropDetailed();
		cc.setId(Integer.parseInt(map.get("id").toString()));
		try {
			dtri.setData(cementTheoryProportionService.getYclList(cc));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	//材料类型
	@RequestMapping("/getYclModelList.action")
	@ResponseBody
	public DataTablesResponseInfo getYclModelList(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			dtri.setData(cementTheoryProportionService.getYclModelList(map));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
