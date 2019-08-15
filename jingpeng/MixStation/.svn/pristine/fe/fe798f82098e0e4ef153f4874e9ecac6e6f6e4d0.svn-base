package com.jingpeng.controller;

import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.service.PlatformIndexService;
import com.jingpeng.util.JDBCUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/Platform")
public class PlatformIndexController extends KDController<Object>{
	@Autowired
	private PlatformIndexService platformIndexService;
	
	@RequestMapping("/index.html")
	public String gotoIndex() {
		return "/lq/platform_index";
	}
	
	@RequestMapping("/getInfo.html")
	public @ResponseBody Map<String, Object> getAll(HttpServletRequest request) {
		List list = new ArrayList();
		Map<String, Object> map = new HashMap();
		Map<String, Object> lq = new HashMap();
		Map<String, Object> sn = new HashMap();
		Map<String, Object> sw = new HashMap();
		Core_User_Info user = (Core_User_Info) request.getSession().getAttribute("user");
		String org_id = user.getI_power_Org_Id().substring(1);
		Date now=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endtime = df.format(now);
		Date start=new Date();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String starttime = df1.format(start)+" 00:00:00";
		String sql ="exec proc_Statistics"+"'"+starttime+"'"+","+"'"+endtime+"'"+","+"'"+org_id+"'"+","+0+","+0;
		List<Map<String, Object>> lqlist = JDBCUtil.execute(sql);
		sql ="exec proc_Statistics"+"'"+starttime+"'"+","+"'"+endtime+"'"+","+"'"+org_id+"'"+","+1+","+0;
		List<Map<String, Object>> snlist = JDBCUtil.execute(sql);
		sql ="exec proc_Statistics"+"'"+starttime+"'"+","+"'"+endtime+"'"+","+"'"+org_id+"'"+","+2+","+0;
		List<Map<String, Object>> swlist = JDBCUtil.execute(sql);
		int totalCount = 0;
		double mix = 0.00;
		double yongLiang = 0.00;
		for(int i = 0; i < lqlist.size(); i++) {
			if(lqlist.get(i).get("ZongChanLiang") == null || lqlist.get(i).get("ZongChanLiang").toString().equals("")) {
				lqlist.get(i).put("ZongChanLiang", 0);
			}
			if(lqlist.get(i).get("LQYongLiang") == null || lqlist.get(i).get("LQYongLiang").toString().equals("")) {
				lqlist.get(i).put("LQYongLiang", 0);
			}
			if(lqlist.get(i).get("TotalCount") == null || lqlist.get(i).get("TotalCount").toString().equals("")) {
				lqlist.get(i).put("TotalCount", 0);
			}
			if(lqlist.get(i).get("HeGePanShu") == null || lqlist.get(i).get("HeGePanShu").toString().equals("")) {
				lqlist.get(i).put("HeGePanShu", 0);
			}
			totalCount += Integer.parseInt(lqlist.get(i).get("TotalCount").toString());
			mix += Double.parseDouble(lqlist.get(i).get("ZongChanLiang").toString());
			yongLiang += Double.parseDouble(lqlist.get(i).get("LQYongLiang").toString());
			list.add(lqlist.get(i));
		}
		lq.put("totalCount", totalCount);
		lq.put("mix", mix);
		lq.put("yongLiang", yongLiang);
		totalCount = 0;
		mix = 0.00;
		yongLiang = 0.00;
		for(int i = 0; i < snlist.size(); i++) {
			if(snlist.get(i).get("ZongChanLiang") == null || snlist.get(i).get("ZongChanLiang").toString().equals("")) {
				snlist.get(i).put("ZongChanLiang", 0);
			}
			if(snlist.get(i).get("SNYongLiang") == null || snlist.get(i).get("SNYongLiang").toString().equals("")) {
				snlist.get(i).put("SNYongLiang", 0);
			}
			if(snlist.get(i).get("TotalCount") == null || snlist.get(i).get("TotalCount").toString().equals("")) {
				snlist.get(i).put("TotalCount", 0);
			}
			if(snlist.get(i).get("HeGePanShu") == null || snlist.get(i).get("HeGePanShu").toString().equals("")) {
				snlist.get(i).put("HeGePanShu", 0);
			}
			totalCount += Integer.parseInt(snlist.get(i).get("TotalCount").toString());
			mix += Double.parseDouble(snlist.get(i).get("ZongChanLiang").toString());
			yongLiang += Double.parseDouble(snlist.get(i).get("SNYongLiang").toString());
			list.add(snlist.get(i));
		}
		sn.put("totalCount", totalCount);
		sn.put("mix", mix);
		sn.put("yongLiang", yongLiang);
		totalCount = 0;
		mix = 0.00;
		yongLiang = 0.00;
		for(int i = 0; i < swlist.size(); i++) {
			if(swlist.get(i).get("ZongChanLiang") == null || swlist.get(i).get("ZongChanLiang").toString().equals("")) {
				swlist.get(i).put("ZongChanLiang", 0);
			}
			if(swlist.get(i).get("SNYongLiang") == null || swlist.get(i).get("SNYongLiang").toString().equals("")) {
				swlist.get(i).put("SNYongLiang", 0);
			}
			if(swlist.get(i).get("TotalCount") == null || swlist.get(i).get("TotalCount").toString().equals("")) {
				swlist.get(i).put("TotalCount", 0);
			}
			if(swlist.get(i).get("HeGePanShu") == null || swlist.get(i).get("HeGePanShu").toString().equals("")) {
				swlist.get(i).put("HeGePanShu", 0);
			}
			totalCount += Integer.parseInt(swlist.get(i).get("TotalCount").toString());
			mix += Double.parseDouble(swlist.get(i).get("ZongChanLiang").toString());
			yongLiang += Double.parseDouble(swlist.get(i).get("SNYongLiang").toString());
			list.add(swlist.get(i));
		}
		sw.put("totalCount", totalCount);
		sw.put("mix", mix);
		sw.put("yongLiang", yongLiang);
		
		map.put("list", list);
		map.put("sn", sn);
		map.put("sw", sw);
		map.put("lq", lq);
		return map;
	}
	
	@RequestMapping("/vedio.html")
	public String vedio(HttpServletRequest request) {
		Runtime rn = Runtime.getRuntime();
		Process p = null;
		try {
		p = rn.exec("\"D:/Program Files (x86)/Ezviz Studio/EzvizProtect.exe\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Platform/index.html";
	}
	
}
