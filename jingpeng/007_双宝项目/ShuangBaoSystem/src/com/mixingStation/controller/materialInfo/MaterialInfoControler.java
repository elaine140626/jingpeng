package com.mixingStation.controller.materialInfo;

import java.io.IOException;
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
import com.mixingStation.service.materialInfo.MaterialInfoService;
import com.mixingStation.util.MessageUtil;

@Controller
@RequestMapping("/material")
public class MaterialInfoControler{
	
	@Autowired
	private MaterialInfoService materialInfoService;
	
	/**
	 * 获取物料名称信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMaterialName.action")
	public @ResponseBody List<MaterialInfo> getMaterialNameList(@RequestParam Map<String, Object> map){
		return materialInfoService.getMaterialNameList(map);	
	}
	/**
	 * 获取物料型号信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMaterialModel.action")
	public @ResponseBody List<MaterialInfo> getMaterialModelList(@RequestParam Map<String, Object> map){
		return materialInfoService.getMaterialModelList(map);	
	}
	
	/**
	 * 获取物料list
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMaterialList.action")
	public @ResponseBody DataTablesResponseInfo getMaterialList(@RequestParam Map<String, Object> map){
		return materialInfoService.getMaterialList(map);	
	}
	/**
	 * 获取物料单位信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMeasureUnitList.action")
	public @ResponseBody List<MaterialInfo> getMeasureUnitList(@RequestParam Map<String, Object> map){
		return materialInfoService.getMeasureUnitList(map);	
	}
	/**
	 * 添加物料信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertMaterialInfo.action")
	public @ResponseBody ResponseInfo insertMaterialInfo(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int result = materialInfoService.insertMaterialInfo(request, map);
		if(result == 1) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("添加成功");
		}else if(result == 2) {
			info.setCode(MessageUtil.OPERATION_FAILED);
			info.setMessage("物料编码不能重复！");
		}else if(result == 3) {
			info.setCode(MessageUtil.OPERATION_FAILED);
			info.setMessage("规格型号不能相同！");
		}else {
			// 删除失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("添加失败");
		}
		return info;	
	}
	
	
	
	/**
	 * 修改物料信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateMaterial.action")
	public @ResponseBody ResponseInfo updateMaterialInfo(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int rusult = materialInfoService.updateMaterialInfo(request, map);
		if(rusult == 1) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("操作成功");
		}else if(rusult == 3) {
			info.setCode(MessageUtil.OPERATION_FAILED);
			info.setMessage("规格型号不能相同！");
		}else {
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("操作失败");
		}
		return info;	
		
			
	}
	/**
	 * 删除物料信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/delMaterial.action")
	public @ResponseBody ResponseInfo delMaterial(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int rusult = materialInfoService.delMaterial(request, map);
		if(rusult > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("操作成功");
		}else {
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("操作失败");
		}
		return info;	
		
			
	}

	/**
	 * 根据产品名称查询对应的所有产品型号
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMaterialModelByName.action")
	public @ResponseBody List<Map<String, Object>> getMaterialModelByName(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		List<Map<String, Object>> list = materialInfoService.getMaterialModelByName(request, map);
		return list;	
	}
	
	/**
	 * 根据产品名称和产品型号查询对应的产品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryProductId.action")
	public @ResponseBody List<MaterialInfo> queryProductId(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
		List<MaterialInfo> list = materialInfoService.queryProductId(request, map);
		return list;	
	}
	
}
