package com.mixingStation.controller.system;

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
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.service.system.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	//获取页面list
	@RequestMapping("/getAllEquipmentInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getAllEquipmentInfo(@RequestParam Map<String,Object> map) {
		return equipmentService.getAllEquipmentInfo(map);
	}
	
	//添加
	@RequestMapping("/addEquipmentInfo.action")
	@ResponseBody
	public ResponseInfo addEquipmentInfo(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return equipmentService.addEquipmentInfo(request,map);
	}
	
	//修改
	@RequestMapping("/updateEquipmentInfo.action")
	@ResponseBody
	public ResponseInfo updateEquipmentInfo(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return equipmentService.updateEquipmentInfo(request,map);
	}
	
	//删除
	@RequestMapping("/deleteEquipmentInfo.action")
	@ResponseBody
	public ResponseInfo deleteEquipmentInfo(@RequestParam Map<String, Object> map) {
		return equipmentService.deleteEquipmentInfo(map);
	}
	
	//通过id查询拌和设备信息
	@RequestMapping("/getEquipmentInfoById.action")
	@ResponseBody
	public List<EquipmentInfo> getEquipmentInfoById(@RequestParam Map<String, Object> map) {
		return equipmentService.getEquipmentInfoById(map);
	}
}
