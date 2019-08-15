package com.mixingStation.controller.asphalt;

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
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.service.asphalt.AsphaltProdProportionService;

@Controller
@RequestMapping("/asphaltProdProportion")
public class AsphaltProdProportionController {

	@Autowired
	private AsphaltProdProportionService asphaltProdProportionService;
	
	//查询沥青生产配合比list
	@RequestMapping("/getAllAsphaltProdProportion.action")
	@ResponseBody
	public DataTablesResponseInfo getAllAsphaltProdProportion(@	RequestParam Map<String,Object> map) {
		DataTablesResponseInfo allAsphaltProdProportion = asphaltProdProportionService.getAllAsphaltProdProportion(map);
		return allAsphaltProdProportion;
	}
	
	//获取产品名称
	@RequestMapping("/getMaterialNameList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialNameList(@RequestParam Map<String, Object> map) {
		return asphaltProdProportionService.getMaterialNameList(map);
	}
	
	//获取产品型号
	@RequestMapping("/getMaterialModelList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialModelList(@RequestParam Map<String, Object> map) {
		return asphaltProdProportionService.getMaterialModelList(map);
	}
	
	//按产品id查询目标配合比编号
	@RequestMapping("/getAsphTargetProCodeByProductId.action")
	@ResponseBody
	public List<Map<String,Object>> getAsphTargetProCodeByProductId(@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.getAsphTargetProCodeByProductId(map);
	}
	
	//按产品id查询级配比编号
	@RequestMapping("/getGradeCodeByIdByProductId.action")
	@ResponseBody
	public List<Map<String,Object>> getGradeCodeByIdByProductId(@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.getGradeCodeByIdByProductId(map);
	}
	
	//添加
	@RequestMapping("/addAsphaltProdProportion.action")
	@ResponseBody
	public ResponseInfo addAsphaltProdProportion(HttpServletRequest request,@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.addAsphaltProdProportion(request,map);
	}
	
	//修改
	@RequestMapping("/updateAsphaltProdProportion.action")
	@ResponseBody
	public ResponseInfo updateAsphaltProdProportion(HttpServletRequest request,@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.updateAsphaltProdProportion(request,map);
	}
	
	//通过沥青生产配合比Id查询 
	@RequestMapping("/getAsphaltProdProportionById.action")
	@ResponseBody
	public List<Map<String,Object>> getAsphaltProdProportionById(@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.getAsphaltProdProportionById(map);
	}
	
	//删除
	@RequestMapping("/deleteAsphaltProdProportion.action")
	@ResponseBody
	public ResponseInfo deleteAsphaltProdProportion(@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.deleteAsphaltProdProportion(map);
	}
	
	//查询生产计划是否调用沥青生产配合比
	@RequestMapping("/getPlanByProdId.action")
	@ResponseBody
	public List<Map<String,Object>> getPlanByProdId(@RequestParam Map<String,Object> map){
		return asphaltProdProportionService.getPlanByProdId(map);
	}
}
