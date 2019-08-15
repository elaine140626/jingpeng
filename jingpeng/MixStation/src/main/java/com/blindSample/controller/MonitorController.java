package com.blindSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Monitor")
public class MonitorController {
	
	@RequestMapping("/Jqindex.html")
	public String getIndexMaintenance1() {
		return "/BlindnessTest/Jqindex";
	}

}
