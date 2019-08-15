package com.oil.controller.transportation;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.transportation.TranSportListStoragemeasureService;

@Controller
@RequestMapping("/TranSportListStoragemeasure")
public class TranSportListStoragemeasureController {

	@Autowired
	private TranSportListStoragemeasureService tranSportListStoragemeasureService;
	
	@RequestMapping("/addTranSportList.action")
	@ResponseBody
	public ResponseInfo addTranSportList(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return tranSportListStoragemeasureService.addTranSportList(map);
	}
	
	@RequestMapping("/getExOrStPlateNumber.action")
	@ResponseBody
	public DataTablesResponseInfo getExOrStPlateNumber(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return tranSportListStoragemeasureService.getExOrStPlateNumber(map);
	}
	
	@RequestMapping("/getSaleType.action")
	@ResponseBody
	public DataTablesResponseInfo getSaleType()  {
		return tranSportListStoragemeasureService.getSaleType();
	}
	
	@RequestMapping("/getMaterielinfo.action")
	@ResponseBody
	public DataTablesResponseInfo getMaterielinfo()  {
		return tranSportListStoragemeasureService.getMaterielinfo();
	}
	
	@RequestMapping("/getFleetInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getFleetInfo()  {
		return tranSportListStoragemeasureService.getFleetInfo();
	}
	
	@RequestMapping("/getTranSportList.action")
	@ResponseBody
	public DataTablesResponseInfo getTranSportList(HttpServletRequest request, @RequestParam Map<String,Object> map) throws ParseException  {
		return tranSportListStoragemeasureService.getTranSportList(map);
	}
	
	@RequestMapping("/delTranSportList.action")
	@ResponseBody
	public ResponseInfo delTranSportList(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.delTranSportList(map);
	}
	
	@RequestMapping("/getIsExAndSt.action")
	@ResponseBody
	public DataTablesResponseInfo getIsExAndSt(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.getIsExAndSt(map);
	}
	
	@RequestMapping("/getPlate.action")
	@ResponseBody
	public DataTablesResponseInfo getPlate()  {
		return tranSportListStoragemeasureService.getPlate();
	}
	
	@RequestMapping("/getTransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getTransportsById(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.getTransportsById(map);
	}
	
	@RequestMapping("/updateNoWeighOut.action")
	@ResponseBody
	public ResponseInfo updateNoWeighOut(HttpServletRequest request, @RequestParam Map<String,Object> map) throws Exception  {
		return tranSportListStoragemeasureService.updateNoWeighOut(map);
	}
	
	@RequestMapping("/adduploadfile.action")
	@ResponseBody
	public ResponseInfo adduploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.adduploadfile(map);
	}
	
	@RequestMapping("/getUploadfile.action")
	@ResponseBody
	public DataTablesResponseInfo getUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.getUploadfile(map);
	}
	
	@RequestMapping("/delUploadfile.action")
	@ResponseBody
	public ResponseInfo delUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.delUploadfile(map);
	}
	
	@RequestMapping("/isHaveUpload.action")
	@ResponseBody
	public ResponseInfo isHaveUpload(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.isHaveUpload(map);
	}
	
	@RequestMapping("/getEXById.action")
	@ResponseBody
	public DataTablesResponseInfo getEXById(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListStoragemeasureService.getEXById(map);
	}
}
