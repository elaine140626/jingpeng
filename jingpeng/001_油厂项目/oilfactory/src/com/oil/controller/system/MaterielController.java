package com.oil.controller.system;

import java.text.ParseException;
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
import com.oil.model.system.MaterielInfo;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.system.MaterielService;

@Controller
@RequestMapping("/Materiel")
public class MaterielController {
	@Autowired
	private MaterielService MaterielService;	
	
	// 获取物料信息list
	@RequestMapping("/getMateriel.action")
	public @ResponseBody DataTablesResponseInfo getMateriel(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<MaterielInfo> list = MaterielService.getMateriel(map);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setSerialnumber(i+1);
		}
		dtri.setData(list);
		return dtri;
	}
	
	//添加产品信息
	@RequestMapping("/addMateriel.action")
	public @ResponseBody ResponseInfo addMateriel(HttpServletRequest request,MaterielInfo Materiel) {
		ResponseInfo info = new ResponseInfo();
		int result =MaterielService.addMateriel(Materiel);
		
		if(result>0) {
			info.setMessage("添加成功");
			info.setCode("success");
		}else {
			info.setMessage("添加失败");
			info.setCode("error");
		}
		return info;
	}
	
	//修改产品信息
	@RequestMapping("/updateMateriel.action")
	public @ResponseBody ResponseInfo updateMateriel(HttpServletRequest request,MaterielInfo Materiel){
		ResponseInfo Info = new ResponseInfo();
		int result = MaterielService.updateMateriel(Materiel);
		if(result>0) {
			Info.setMessage("修改成功");
			Info.setCode("success");
		}else {
			Info.setMessage("修改失败");
			Info.setCode("error");
		}
		return Info;
	}
	

	//删除的校验信息查询
	@RequestMapping("/getAllContractdetailed.action")
	public @ResponseBody List<Map<String, Object>> getAllContractdetailed(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return MaterielService.getAllContractdetailed(map);
	}
	
	//删除产品信息
	@RequestMapping("/delMaterielById.action")
	public @ResponseBody ResponseInfo delUserInfo(HttpServletRequest request,@RequestParam int id) {
		ResponseInfo Info = new ResponseInfo();
		int result =  MaterielService.delMaterielById(id);
		if(result>0) {
			Info.setMessage("删除成功");
			Info.setCode("success");
		}else {
			Info.setMessage("删除失败");
			Info.setCode("error");
		}
		return Info;
	}
	
	//查询仓库名称
	@RequestMapping("/getWarehouseinfo.action")
	public @ResponseBody List<WareHouseInfo> getWarehouseinfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		List<WareHouseInfo> list = MaterielService.getWareHouseInfo(map);
		return list;
	}
	
	// 根据物料编号,物料名称,物料型号去重
	@RequestMapping("/getAllMaterielNumber.action")
	public @ResponseBody List<Map<String, Object>> getAllMaterielNumber(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return MaterielService.getAllMaterielNumber(map);
	}
}
