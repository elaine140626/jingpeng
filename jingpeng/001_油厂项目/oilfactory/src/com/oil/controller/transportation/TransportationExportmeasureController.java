package com.oil.controller.transportation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.service.transportation.TransportationExportmeasureService;

@Controller
@RequestMapping("/TransportationExportmeasure")
public class TransportationExportmeasureController {

	@Autowired
	private TransportationExportmeasureService transportationExportmeasureService;
	
	@RequestMapping("/getExportMeasurePlate.action")
	@ResponseBody
	public DataTablesResponseInfo getPlate()  {
		return transportationExportmeasureService.getExportMeasurePlate();
	}
	
	@RequestMapping("/getSaleType.action")
	@ResponseBody
	public DataTablesResponseInfo getSaleType()  {
		return transportationExportmeasureService.getSaleType();
	}
	
	@RequestMapping("/getExportmeasureByPlateNumber.action")
	@ResponseBody
	public DataTablesResponseInfo getExportmeasureByPlateNumber(HttpServletRequest request, @RequestParam Map<String,Object> map) {
		
		return transportationExportmeasureService.getExportmeasureByPlateNumber(map);
	}
	
	@RequestMapping("/getCustomertransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomertransportsById(HttpServletRequest request, @RequestParam Map<String,Object> map) {
		
		return transportationExportmeasureService.getCustomertransportsById(map);
	}
	
	
	
}
