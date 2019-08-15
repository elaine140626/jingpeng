package com.jingpeng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.Left;
import com.jingpeng.model.Tab;
import com.jingpeng.service.BigScreenService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
public class BigScreenController extends KDController<Object> {
	@Autowired
	private BigScreenService bigScreenService;

	@RequestMapping("index.html")
	public String index(HttpServletRequest request) {
		return "/bigScreen/index";
	}
	
	@RequestMapping("/BigScreen/getAll.html")
	public @ResponseBody Map<String, Object> getAll(HttpServletRequest request) {
		Map<String, Object> all = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap();
			List<Left> left = bigScreenService.getLeft(params);
			List<Tab> Tab = bigScreenService.getTab(params);
			List<Map<String, Object>> maps = bigScreenService.getMaps(params);
			for(int i = 0; i < maps.size(); i++) {
				if(maps.get(i).get("time") != null) {
					String strTime = maps.get(i).get("time").toString();
					Calendar curTime=Calendar.getInstance();
					Calendar time = new GregorianCalendar();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = sdf.parse(strTime);
					time.setTime(date);
					long timeOne = curTime.getTimeInMillis();
		    		long timeTwo = time.getTimeInMillis();
		    		long minute = (timeOne-timeTwo)/(1000*60);
		    		if(minute > 3) {
		    			maps.get(i).put("isProduce", 0);
		    		} else {
		    			maps.get(i).put("isProduce", 1);
		    		}
				} else {
					maps.get(i).put("isProduce", 0);
				}
			}
			all.put("left", left);
			all.put("Tab", Tab);
			all.put("maps", maps);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}
	
	@RequestMapping("/BigScreen/getTab.html")
	public @ResponseBody List<Tab> getTab(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		try {
			return bigScreenService.getTab(params);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/BigScreen/getPie.html")
	public @ResponseBody Tab getPie(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		try {
			return bigScreenService.getPie(params).get(0);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/BigScreen/getBar.html")
	public @ResponseBody List getBar(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		try {
			if("0".equals(params.get("type").toString())){
				return bigScreenService.getLqBar(params);
			}
			if("1".equals(params.get("type").toString())){
				return bigScreenService.getSnBar(params);
			}
			if("2".equals(params.get("type").toString())){
				return bigScreenService.getSwBar(params);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/BigScreen/getAxis.html")
	public @ResponseBody List getAxis(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		try {
			if("0".equals(params.get("type").toString())){
				return bigScreenService.getLqAxis(params);
			} else {
				return bigScreenService.getSnSwAxis(params);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
