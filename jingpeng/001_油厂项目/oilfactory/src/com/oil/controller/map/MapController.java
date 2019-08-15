package com.oil.controller.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.map.MapCar;
import com.oil.service.map.MapService;

@RequestMapping("/Map")
@Controller
public class MapController {

	@Autowired
	private MapService mapService;
	
	//获取车辆坐标
	@RequestMapping("/getCarTrajectory.action")
	@ResponseBody
	public List<List<MapCar>> getCarTrajectory(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		if (map.get("carId")!=null) {
			String carId = map.get("carId").toString();
			String[] param = {};
			if(carId != null && !("").equals(carId)) {
				param = carId.split(",");
				map.put("carId", param);
			}
		}
		return mapService.getCarTrajectory(map);
		
	}
	
/*	@RequestMapping("/addMap.action")
	@ResponseBody
	public int addMap(MapRoad mapRoad){
		return mapService.addMap(mapRoad);
	}
	
	@RequestMapping("/getMapRoad.action")
	@ResponseBody
	public List<List<MapRoad>> getMapRoad(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return mapService.getMapRoad(map);
	}
	
	@RequestMapping("/getMapRoadName.action")
	@ResponseBody
	public List<MapRoad> getMapRoadName(){
		return mapService.getMapRoadName();
	}*/
	
	//获取车辆名字 *
	@RequestMapping("/getCarName.action")
	@ResponseBody
	public List<MapCar> getCarName(){
		return mapService.getCarName();
	}
	
	//获取车队
	@RequestMapping("/getFleetCars.action")
	@ResponseBody
	public List<Map<String, Object>> getFleetCars(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return mapService.getFleetCars(map);
	}
	
	//获取止运地 *
	@RequestMapping("/getCustomertransports.action")
	@ResponseBody
	public List<Map<String, Object>> getCustomertransports(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		return mapService.getCustomertransports(map);
	}
}
