package com.oil.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oil.dao.carjournal.CarjournalDao;
import com.oil.dao.dispath.RoadgateDao;
import com.oil.model.Carjournal;
import com.oil.model.Roadgate;

@Controller
@RequestMapping("/roadgateApp")
public class RoadgateApp {
	
	@Autowired
	RoadgateDao roadgateDao;
	
	@Autowired
	CarjournalDao carjournalDao;
	

	// 按扫描车辆的车牌号码查询
    @RequestMapping(value = { "/getRoadgateList.json" })
    public ResponseEntity<Map<String, Object>> getRoadgateList(@RequestParam(value="carNumber")String carNumber){
    	Roadgate roadgate = new Roadgate();
    	roadgate.setCarNumber(carNumber);
    	List<Roadgate> roadgateList = roadgateDao.getRoadgateList(roadgate);
    	Map<String, Object> map = new HashMap<String, Object>();
    	Carjournal carjournal = new Carjournal();
    	if(roadgateList.size() > 0 && roadgateList != null) {
    		map.put("type", 0);
    		carjournal.setVehicleCondition(0);
    	}else {
    		map.put("type", 1);
    		carjournal.setVehicleCondition(1);
    	}
    	carjournal.setCarNumber(carNumber);
    	carjournalDao.addCarjournal(carjournal);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
	// 按扫描车辆的车牌号码查询
    @RequestMapping(value = { "/getOutRoadgateList.json" })
    public ResponseEntity<Map<String, Object>> getOutRoadgateList(@RequestParam(value="carNumber")String carNumber){
    	Roadgate roadgate = new Roadgate();
    	roadgate.setCarNumber(carNumber);
    	Map<String, Object> map = new HashMap<String, Object>();
    	Carjournal carjournal = new Carjournal();
    	map.put("type", 0);
    	map.put("plateNumber", carNumber);
    	carjournal.setVehicleCondition(0);
    	carjournal.setCarNumber(carNumber);
    	carjournalDao.addCarjournal(carjournal);
    	//查找皮重和净重不为空并且出厂时间为空
    	List<Map<String,Object>> exportmeasureList = carjournalDao.getExportmeasureFactoryTime(map);
    	for (int i = 0; i < exportmeasureList.size(); i++) {
    		Map<String, Object> mapId = new HashMap<String, Object>();
    		if(exportmeasureList.get(i).get("id") != null ) {
    			mapId.put("id", exportmeasureList.get(i).get("id"));
    			//添加出厂时间
    			carjournalDao.addExportmeasureFactoryTime(mapId);
    		}
		}
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
}
