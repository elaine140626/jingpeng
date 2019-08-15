package com.oil.controller.app;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oil.dao.dispath.OutboundDao;
import com.oil.model.system.Prefix;
import com.oil.service.dispath.InstroeService;
import com.oil.service.dispath.OutboundService;
import com.oil.service.repertory.RepertoryService;
import com.oil.service.system.SerialNumberService;

@Controller
@RequestMapping("/commonApp")
public class TestApp {
	
	@Autowired
	OutboundService outboundService;
	
	@Autowired
	InstroeService instroeService;
	
	@Autowired
	RepertoryService repertoryService;
	
	@Autowired
	private SerialNumberService serialNumberService;
	
	@Autowired
	OutboundDao outboundDao;

	
	// 前缀编号获取
    @RequestMapping(value = { "/testAPPList.json" })
    public ResponseEntity<Map<String, Object>> getContractInfoPrefix(@RequestParam(value="types")String types){
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "2222222");
        map.put("addess", "3333333");
        map.put("id", "66666");
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    
}

