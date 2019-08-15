package com.oil.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CarInfo;
import com.oil.model.system.FleetInfo;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.system.FleetInfoService;

@Controller
@RequestMapping("/FleetInfo")
public class FleetInfoController {

	int fid = 0;
	
	@Autowired
	private FleetInfoService fleetInfoService;

	@RequestMapping("/getFleetInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getFleetInfo() {
		return fleetInfoService.getFleetInfo();
	}
	
	@RequestMapping("/getFleetInfoByIdAndCarInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getFleetInfoByIdAndCarInfo(@RequestParam FleetInfo fleetInfo) {
		Map<String,Object> map = new HashMap<>();
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		FleetInfo Fleet = fleetInfoService.getFleetInfoById(fleetInfo.getId());
		List<CarInfo> carList = fleetInfoService.getCarInfo(fleetInfo);
		map.put("Fleet", Fleet);
		map.put("carList", carList);
		info.setData(map);
		return info;
	}
	
	@RequestMapping("/addFleetInfo.action")
	@ResponseBody
	public ResponseInfo addFleetInfo(@RequestBody FleetInfo fleetInfo) {
		ResponseInfo info = new ResponseInfo();
		fleetInfo.setIsDel(0);
		int result = fleetInfoService.addFleetInfo(fleetInfo);
		System.out.println("------------>"+fleetInfo.getId());
		if(result>0) {
			info.setCode("200");
			info.setSid(fleetInfo.getId());
			fid = fleetInfo.getId();
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	
	@RequestMapping("/addCarInfo.action")
	@ResponseBody
	public ResponseInfo addCarInfo(@RequestBody List<CarInfo> carInfo) {
		//List<CarInfo> carInfo = new ArrayList<>();
		ResponseInfo info = new ResponseInfo();
			int carFid = fid;
			if(carInfo.size()!=0) {			
				for (int i = 0; i < carInfo.size(); i++) {
					carInfo.get(i).setFleetId(carFid);
					carInfo.get(i).setIsDel(0);
				}
				int result = 0;
				for (int i = 0; i < carInfo.size(); i++) {
					result = fleetInfoService.addCarInfo(carInfo.get(i));
					if(result<0) {
						break;
					}
				}
				if(result>0) {
					info.setCode("200");
					info.setMessage("添加成功");
				}else {
					info.setCode("500");
					info.setMessage("添加失败");
				}
			}else {
				info.setCode("200");
				info.setMessage("添加成功");
			}
		return info;
	}
	
	@RequestMapping("/delFleetInfo.action")
	@ResponseBody
	public ResponseInfo delFleetInfo(HttpServletRequest request,@RequestParam int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		int cResilt = 0;
		FleetInfo fleetInfo = fleetInfoService.getFleetInfoById(id);
		fleetInfo.setIsDel(1);
		List<CarInfo> carInfoList = fleetInfoService.getCarInfo(fleetInfo);
		result = fleetInfoService.delFleetInfo(fleetInfo);
		if(carInfoList.size()!=0) {
			for (int i = 0; i < carInfoList.size(); i++) {
				carInfoList.get(i).setIsDel(1);
				cResilt = fleetInfoService.delCarInfo(carInfoList.get(i));
				if(cResilt<0) {
					break;
				}
			}
		}else {
			cResilt = 1;
		}
		if(result>0&&cResilt>0) {
			info.setCode("200");
			info.setMessage("删除成功");
		}else {
			info.setCode("500");
			info.setMessage("删除失败");
		}
		return info;
	}
	@RequestMapping("/getFleetInfoById.action")
	@ResponseBody
	public DataTablesResponseInfo getFleetInfoById(HttpServletRequest request,@RequestParam int id) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<>();
		FleetInfo  fleetInfo = fleetInfoService.getFleetInfoById(id);
		List<CarInfo> carInfo = fleetInfoService.getCarInfo(fleetInfo);
		map.put("fleetInfo", fleetInfo);
		map.put("carInfo", carInfo);
		info.setData(map);
		return info;
	}
	
	@RequestMapping("/updateFleetInfo.action")
	@ResponseBody
	public ResponseInfo updateFleetInfo(@RequestBody FleetInfo fleetInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = fleetInfoService.updateFleetInfo(fleetInfo);
		if(result>0) {
			info.setCode("200");
			info.setSid(fleetInfo.getId());
			fid = fleetInfo.getId();
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	
	@RequestMapping("/updateCarInfo.action")
	@ResponseBody
	public ResponseInfo updateCarInfo(@RequestBody List<CarInfo> carInfo) {
		//List<CarInfo> carInfo = new ArrayList<>();
		ResponseInfo info = new ResponseInfo();
			int carFid = fid;
			int result = 0;
			for (int i = 0; i < carInfo.size(); i++) {
				carInfo.get(i).setFleetId(carFid);
				if(carInfo.get(i).getId()==0) {
					carInfo.get(i).setIsDel(0);
					result = fleetInfoService.addCarInfo(carInfo.get(i));
				}else {
					result = fleetInfoService.updateCarInfo(carInfo.get(i));
				}
				if(result<0) {
					break;
				}
			}
			if(result>0) {
				info.setCode("200");
				info.setMessage("修改成功");
			}else {
				info.setCode("500");
				info.setMessage("修改失败");
			}
		return info;
	}
	
	@RequestMapping("/findFleetInfoByName.action")
	@ResponseBody
	public DataTablesResponseInfo findFleetInfoByName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		String fleetName = map.get("fleetName").toString();
		return fleetInfoService.findFleetInfoByName(fleetName);
	}
	
	@RequestMapping("/delCarInfoById.action")
	@ResponseBody
	public ResponseInfo delCarInfoById(HttpServletRequest request,@RequestParam int id) {
		return fleetInfoService.delCarInfoById(id);
	}
	
	//车辆删除校验查询
	@RequestMapping("/getAllCarName.action")
	@ResponseBody
	public List<Map<String, Object>> getAllCarName(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return fleetInfoService.getAllCarName(map);
	}
	
	//通过车辆id查询车辆
	@RequestMapping("/getCarInfoById.action")
	@ResponseBody
	public CarInfo getCarInfoById(HttpServletRequest request,@RequestParam Map<String,Object> map) {
		return fleetInfoService.getCarInfoById(map);
	}
	//通过车辆编号查询车辆
	@RequestMapping("/getCarInfoByCarNumber.action")
	@ResponseBody
	public List<CarInfo> getCarInfoByCarNumber(HttpServletRequest request,@RequestParam Map<String,Object> map) {
		return fleetInfoService.getCarInfoByCarNumber(map);
	}
	//通过车队id查询车辆信息
	@RequestMapping("/getAllCarInfo.action")
	@ResponseBody
	public List<Map<String, Object>> getAllCarInfo(HttpServletRequest request,@RequestParam Map<String,Object> map) {
		return fleetInfoService.getAllCarInfo(map);
	}
}
